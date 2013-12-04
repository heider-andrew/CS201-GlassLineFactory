package glassLine;

import glassLine.interfaces.ConveyorAgentInterface;
import glassLine.interfaces.PopupAgentInterface;
import glassLine.interfaces.SensorAgentInterface;
import agent.Agent;

public class SensorAgent extends Agent implements SensorAgentInterface {
	SensorAgentInterface prevSensor;
	ConveyorAgentInterface conveyor;
	PopupAgentInterface popup;
	State state = State.NO_GLASS;
	public boolean expectingGlassOnConv;
	public boolean expectingGlassonPopup;
	boolean convReadyForEndingGlass;
	boolean alreadyInformed;
	boolean nextConvReady = true; // Hack for giving glass to next conveyor
									// (which will be different than this
									// version.)

	enum State {
		NEW_GLASS, TREATING_GLASS, TREATED_GLASS, NO_GLASS
	};

	String name;

	public SensorAgent(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void addConveyor(ConveyorAgentInterface c) {
		this.conveyor = c;
	}

	public void addPopup(PopupAgentInterface p) {
		this.popup = p;
	}

	public void addPrevSensor(SensorAgentInterface s) {
		this.prevSensor = s;
	}

	// MESSAGES ==================================
	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.SensorAgentInterface#msgAmIReady(glassLine.interfaces.
	 * ConveyorAgentInterface)
	 */
	@Override
	public void msgAmIReady(ConveyorAgentInterface c) { // from conveyor to
														// entry
		print("Received msgAmIReady from conveyor");
		expectingGlassOnConv = true;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.SensorAgentInterface#msgAmIReady(glassLine.interfaces.
	 * PopupAgentInterface)
	 */
	@Override
	public void msgAmIReady(PopupAgentInterface p) { // from popup to pre
		print("Received msgAmIReady from popup");
		expectingGlassonPopup = true;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.SensorAgentInterface#msgIHaveGlass(glassLine.interfaces.
	 * ConveyorAgentInterface)
	 */
	@Override
	public void msgIHaveGlass(ConveyorAgentInterface c) { // from conveyor to
															// entry
		print("Received msgIHaveGlass from conveyor");
		state = State.NEW_GLASS;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.SensorAgentInterface#msgIHaveGlass(glassLine.interfaces.
	 * PopupAgentInterface)
	 */
	@Override
	public void msgIHaveGlass(PopupAgentInterface p) { // from popup to pre
		print("Received msgIHaveGlass from popup");
		state = State.TREATING_GLASS;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.SensorAgentInterface#msgIsConvReady()
	 */
	@Override
	public void msgIsConvReady() { // from popup to post
		print("Received msgIsConvReady");
		expectingGlassOnConv = true;
		state = State.TREATED_GLASS;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.SensorAgentInterface#msgGlassReadyToLeave()
	 */
	@Override
	public void msgGlassReadyToLeave() { // from conveyor to exit
		print("Received msgGlassReadyToLeave");
		convReadyForEndingGlass = true;
		alreadyInformed = false;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.SensorAgentInterface#msgDoneWithGlass()
	 */
	@Override
	public void msgDoneWithGlass() { // from conveyor/popup to exit/post
		print("Received msgDoneWithGlass");
		state = State.NO_GLASS;
		stateChanged();
	}

	@Override
	public boolean pickAndExecuteAnAction() {
		// print("State of glass: " + state.toString());
		if (expectingGlassOnConv) {
			print("EXPECTING GLASS ON CONVEYOR");

			if (state == State.NO_GLASS) {
				informConveyorReady();
				expectingGlassOnConv = false;
				state = State.NEW_GLASS;
				return true;
			}
			if (state == State.TREATED_GLASS) {
				informPopupConvReady();
				expectingGlassOnConv = false;
				return true;
			}
		}
		if (expectingGlassonPopup) {
			print("EXPECTING GLASS ON POPUP");
			if (state == State.NO_GLASS) {
				informPopupReady();
				expectingGlassonPopup = false;
				state = State.TREATING_GLASS;
				return true;
			}
		}

		if (convReadyForEndingGlass && nextConvReady && !alreadyInformed) {
			if (state == State.NO_GLASS || state == State.TREATED_GLASS) {
				informConvClear();
				return true;
			}
		}

		return false;
	}

	// ACTIONS ==========================================
	private void informConveyorReady() {
		print("About to inform conveyor that it is ready to accept glass");
		conveyor.msgYouAreReady();
	}

	private void informPopupReady() {
		print("About to inform popup that it is ready to accept glass");
		popup.msgYouAreReady();
		stateChanged();
	}

	private void informPopupConvReady() {
		print("About to inform popup that conveyor is ready");
		popup.msgConvIsReady();
		stateChanged();
	}

	private void informConvClear() {
		print("About to inform conveyor that it is clear");
		conveyor.msgClear();
		alreadyInformed = true;
		stateChanged();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		state = State.NO_GLASS;
	}
}