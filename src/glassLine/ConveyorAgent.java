package glassLine;

import glassLine.data.Glass;
import glassLine.interfaces.ConveyorAgentInterface;
import glassLine.interfaces.GlassRobotAgentInterface;
import glassLine.interfaces.PopupAgentInterface;
import glassLine.interfaces.SensorAgentInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import agent.Agent;

public class ConveyorAgent extends Agent implements ConveyorAgentInterface {

	// DATA ===============================================
	GlassRobotAgentInterface previous;
	ConveyorAgentInterface next;

	SensorAgentInterface entry;
	SensorAgentInterface exit;

	PopupAgentInterface popup;
	boolean popupUp;

	boolean startRequested = false;
	boolean stopRequested = false;
	boolean moving = false;
	boolean newGlassRequested = false;
	boolean acceptNewGlass = false;
	boolean newGlass = false;
	boolean popupReady = false;
	boolean startDoneProcess = false;
	boolean clear = false;
	boolean nextReady = false;
	boolean processStarted = false;
	boolean glassGiven = false;
	boolean doneStarted = false;

	Queue<Glass> pieces;
	String name;
	List<Glass> doneGlassPieces;

	public ConveyorAgent(String name) {
		super();
		this.name = name;
		this.pieces = new LinkedList<Glass>();
		this.doneGlassPieces = new ArrayList<Glass>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void resetSensors() {
		this.entry.reset();
		this.exit.reset();
		this.popup.reset();
	}

	public void addEntrySensor(SensorAgentInterface entry) {
		this.entry = entry;
	}

	public void addExitSensor(SensorAgentInterface exit) {
		this.exit = exit;
	}

	public void addPrevRobot(GlassRobotAgentInterface prev) {
		this.previous = prev;
	}

	public void addNextConveyor(ConveyorAgentInterface next) {
		this.next = next;
	}

	public void addPopup(PopupAgentInterface popup) {
		this.popup = popup;
	}

	// MESSAGES =============================================
	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#msgStartConveyor()
	 */
	@Override
	public void msgStartConveyor() {
		print("Received message startConveyor");
		startRequested = true;
		stateChanged();
	}

	public void msgStopConveyor() {
		print("Received message stopConveyor");

		stopRequested = true;
		stateChanged();
	}

	@Override
	public void msgAreYouReady() { // from previous robot
		print("Received message areYouReady");

		newGlassRequested = true;
		stateChanged();
	}

	@Override
	public void msgYouAreReady() { // from entry sensor
		print("Received message msgYouAreReady");
		acceptNewGlass = true;
		stateChanged();
	}

	@Override
	public void msgHereIsGlass(Glass g) { // from previous robot
		print("Received message hereIsGlass");

		pieces.add(g);
		newGlass = true;
		stateChanged();
	}

	@Override
	public void msgIAmReady() { // from pop-up
		print("Received message iAmReady");
		popupReady = true;
		stateChanged();
	}

	@Override
	public void msgHereIsTreatedGlass(Glass g) { // from pop-up
		print("Received message hereIsTreatedGlass");
		startDoneProcess = true;
		glassGiven = false;
		stateChanged();
	}

	@Override
	public void msgClear() { // from exit sensor
		print("Received message clear");
		clear = true;
		stateChanged();
	}

	/*
	 * void msgIAmReadyForNewGlass() { // from next conveyor
	 * print("Received message iAmReadyForNewGlass"); nextReady = true;
	 * stateChanged(); }
	 */
	// SCHEDULER =====================================
	@Override
	public boolean pickAndExecuteAnAction() {
		if (stopRequested && moving) {
			moving = false;
			stopRequested = false;
			return true;
		}
		if (startRequested && !moving) {
			moving = true;
			startRequested = false;
			return true;
		}

		if (acceptNewGlass) {
			informReady();
			acceptNewGlass = false;
			return true;
		}
		if (newGlass) {
			informNewGlass();
			newGlass = false;
			return true;
		}

		if (!pieces.isEmpty() && !processStarted) {
			if (!(pieces.peek().isPopupNecessary(0)))
				bypass();
			else {
				startProcess();
				processStarted = true;
			}
			return true;
		}

		if (popupReady && !glassGiven && !startDoneProcess && !doneStarted) {
			giveGlassToPopup();
			if (newGlassRequested)
				informReady();
			return true;
		}

		if (startDoneProcess) {
			startDoneProcess();
			startDoneProcess = false;
		}

		// if (clear && nextReady) {
		if (clear) {
			doneWithGlass();
			clear = false;

			startRequested = false;
			stopRequested = false;
			moving = false;
			newGlassRequested = false;
			acceptNewGlass = false;
			newGlass = false;
			popupReady = false;
			startDoneProcess = false;
			clear = false;
			nextReady = false;
			processStarted = false;
			glassGiven = false;
			doneStarted = false;
		}
		if (newGlassRequested) {
			checkIfReady();
			return true;
		}
		return false;
	}

	// ACTIONS ========================================
	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#checkIfReady()
	 */
	@Override
	public void checkIfReady() {
		print("Checking if ready");
		entry.msgAmIReady(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#informReady()
	 */
	@Override
	public void informReady() {
		print("Informing conveyor that it is ready to accept new glass");
		previous.msgIAmReadyForNewGlass();
		newGlassRequested = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#informNewGlass()
	 */
	@Override
	public void informNewGlass() {
		print("Informing new glass");

		entry.msgIHaveGlass(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#bypass()
	 */
	@Override
	public void bypass() {
		print("Bypassing popup");

		if (popup.isEmpty()) {
			startDoneProcess = true;
			stateChanged();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#startProcess()
	 */
	@Override
	public void startProcess() {
		print("Starting popup process");

		msgStopConveyor();
		popup.msgIsPopupReady();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#giveGlassToPopup()
	 */
	@Override
	public void giveGlassToPopup() {
		popup.msgHereIsPretreatedGlass(pieces.peek());
		glassGiven = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#startDone()
	 */
	@Override
	public void startDoneProcess() {
		print("Starting done process");
		doneStarted = true;
		exit.msgGlassReadyToLeave();
		// next.msgAreYouReady();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.ConveyorAgentInterface#doneWithGlass()
	 */
	@Override
	public void doneWithGlass() {
		print("Done with glass");
		Glass g = pieces.remove();
		doneGlassPieces.add(g);
		// next.msgHereIsGlass(g);
		exit.msgDoneWithGlass();
		processStarted = false;
		doneStarted = false;
		resetSensors();
		System.out.println("\n");
	}
}