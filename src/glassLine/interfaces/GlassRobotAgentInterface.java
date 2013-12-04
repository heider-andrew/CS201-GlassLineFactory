package glassLine.interfaces;

import glassLine.data.Glass;
import glassLine.data.Glass.GlassType;

public interface GlassRobotAgentInterface {

	// MESSAGES
	public abstract void msgIAmReadyForNewGlass();

	public abstract void msgScheduleNewGlass(GlassType t);

	public abstract void msgInPosition();

	public abstract void msgPickedUp();

	// ACTIONS
	public abstract void prepGlass(Glass g);

	public abstract void getGlassToPosition(Glass g);

	public abstract void loadUpGlass(Glass g);

}