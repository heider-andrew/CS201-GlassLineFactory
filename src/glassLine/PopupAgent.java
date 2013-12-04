package glassLine;

import glassLine.data.Glass;
import glassLine.data.PopupGUI;
import glassLine.interfaces.ConveyorAgentInterface;
import glassLine.interfaces.PopupAgentInterface;
import glassLine.interfaces.SensorAgentInterface;

import java.util.Timer;
import java.util.TimerTask;

import agent.Agent;

public class PopupAgent extends Agent implements PopupAgentInterface {
	ConveyorAgentInterface conveyor;
	SensorAgentInterface pre;
	SensorAgentInterface post;
	Glass glass = null;
	PopupGUI popupGUI;

	boolean wantToPutGlassOn = false;
	boolean requestUp = false;
	boolean requestDown = false;
	public boolean isUp = false;
	public boolean ready = false;
	boolean newGlass = false;
	boolean convReady = false;
	boolean askedAlready = false;

	Timer timer = new Timer();

	String name;

	public PopupAgent(String name) {
		super();
		this.name = name;
		this.popupGUI = new PopupGUI();
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void addConveyor(ConveyorAgentInterface c) {
		this.conveyor = c;
	}

	public void addPreSensor(SensorAgentInterface pre) {
		this.pre = pre;
	}

	public void addPostSensor(SensorAgentInterface post) {
		this.post = post;
	}

	// MESSAGES ==========================
	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.PopupAgentInterface#msgGoUp()
	 */
	@Override
	public void msgGoUp() { // from conveyor
		print("Received message goUp");
		requestUp = true;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.PopupAgentInterface#msgGoDown()
	 */
	@Override
	public void msgGoDown() { // from conveyor
		print("Received message goDown");
		requestDown = true;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.PopupAgentInterface#msgIsPopupReady()
	 */
	@Override
	public void msgIsPopupReady() { // from conveyor
		print("Received message isPopupReady");
		wantToPutGlassOn = true;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.PopupAgentInterface#msgYouAreReady()
	 */
	@Override
	public void msgYouAreReady() { // from pre
		print("Received message youAreReady");
		ready = true;
		askedAlready = false;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * glassLine.PopupAgentInterface#msgHereIsPretreatedGlass(glassLine.data
	 * .Glass)
	 */
	@Override
	public void msgHereIsPretreatedGlass(Glass g) { // from conveyor
		print("Received message hereIsPretreatedGlass");
		glass = g;
		newGlass = true;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.PopupAgentInterface#msgConvIsReady()
	 */
	@Override
	public void msgConvIsReady() { // from post
		print("Received message convIsReady");
		convReady = true;
		stateChanged();
	}

	// SCHEDULER =========================================
	@Override
	public boolean pickAndExecuteAnAction() {
		if (requestUp && !isUp) {
			goUp();
			return true;
		}
		if (requestDown && isUp) {
			goDown();
			return true;
		}
		if (convReady && !isUp && glass != null && !newGlass) {
			removeGlass();
			return true;
		}
		if (wantToPutGlassOn) {
			if (!ready && !askedAlready) {
				checkReady();
				askedAlready = true;
				return true;
			}
			if (!isUp && ready) {
				informReady();
				wantToPutGlassOn = false;
				ready = false;
				return true;
			}
		}
		if (newGlass) {
			treatGlass();
			newGlass = false;
			return true;
		}
		return false;
	}

	// ACTIONS ==================================================
	private void goUp() {
		print("Going up");
		popupGUI.goUp();
		isUp = true;
		stateChanged();
	}

	private void goDown() {
		print("Going down");
		popupGUI.goDown();
		isUp = false;
		stateChanged();
	}

	private void checkReady() {
		print("Checking if ready");
		pre.msgAmIReady(this);
		stateChanged();
	}

	private void informReady() {
		print("Informing ready");
		conveyor.msgIAmReady();
		stateChanged();
	}

	private void treatGlass() {
		print("Treating glass");
		pre.msgIHaveGlass(this);
		popupGUI.treatGlass();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				post.msgIsConvReady();
			}
		}, 3000);
	}

	private void removeGlass() {
		print("Removing glass");
		conveyor.msgHereIsTreatedGlass(glass);
		post.msgDoneWithGlass();
		glass.doneWithPopup(0);
		glass = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.PopupAgentInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (glass == null);
	}

	@Override
	public void reset() {
		goDown();
		convReady = false;
		this.post.reset();
		this.pre.reset();

	}
}