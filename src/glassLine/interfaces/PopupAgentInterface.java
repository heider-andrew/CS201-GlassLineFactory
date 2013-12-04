package glassLine.interfaces;

import glassLine.data.Glass;

public interface PopupAgentInterface {

	// MESSAGES ==========================
	public abstract void msgGoUp();

	public abstract void msgGoDown();

	public abstract void msgYouAreReady();

	public abstract void msgIsPopupReady();

	public abstract void msgHereIsPretreatedGlass(Glass g);

	public abstract void msgConvIsReady();

	public abstract boolean isEmpty();

	public abstract void reset();

}