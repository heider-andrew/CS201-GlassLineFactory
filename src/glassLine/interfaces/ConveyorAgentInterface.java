package glassLine.interfaces;

import glassLine.data.Glass;

public interface ConveyorAgentInterface {

	// MESSAGES =============================================
	public abstract void msgAreYouReady();

	public abstract void msgStartConveyor();

	public abstract void msgHereIsGlass(Glass g);

	// ACTIONS ========================================
	public abstract void checkIfReady();

	public abstract void informReady();

	public abstract void informNewGlass();

	public abstract void bypass();

	public abstract void startProcess();

	public abstract void giveGlassToPopup();

	public abstract void startDoneProcess();

	public abstract void doneWithGlass();

	public abstract void msgIAmReady();

	public abstract void msgHereIsTreatedGlass(Glass glass);

	public abstract void msgClear();

	public abstract void msgYouAreReady();

}