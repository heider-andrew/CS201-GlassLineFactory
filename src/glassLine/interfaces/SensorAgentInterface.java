package glassLine.interfaces;

public interface SensorAgentInterface {

	// MESSAGES ==================================
	public abstract void msgAmIReady(ConveyorAgentInterface c);

	public abstract void msgAmIReady(PopupAgentInterface p);

	public abstract void msgIHaveGlass(ConveyorAgentInterface c);

	public abstract void msgIHaveGlass(PopupAgentInterface p);

	public abstract void msgIsConvReady();

	public abstract void msgGlassReadyToLeave();

	public abstract void msgDoneWithGlass();

	public abstract void reset();

}