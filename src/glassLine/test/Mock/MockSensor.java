package glassLine.test.Mock;

import glassLine.interfaces.ConveyorAgentInterface;
import glassLine.interfaces.PopupAgentInterface;
import glassLine.interfaces.SensorAgentInterface;

public class MockSensor extends MockAgent implements SensorAgentInterface {

	public EventLog log = new EventLog();

	public MockSensor(String name) {
		super(name);
	}

	@Override
	public void msgAmIReady(ConveyorAgentInterface c) {
		log.add(new LoggedEvent("Received message amIReady from conveyor"));

	}

	@Override
	public void msgAmIReady(PopupAgentInterface p) {
		log.add(new LoggedEvent("Received message amIReady from popup"));

	}

	@Override
	public void msgIHaveGlass(ConveyorAgentInterface c) {
		log.add(new LoggedEvent("Received message iHaveGlass from conveyor"));

	}

	@Override
	public void msgIHaveGlass(PopupAgentInterface p) {
		log.add(new LoggedEvent("Received message iHaveGlass from popup"));

	}

	@Override
	public void msgIsConvReady() {
		log.add(new LoggedEvent("Received message isConvReady"));

	}

	@Override
	public void msgGlassReadyToLeave() {
		log.add(new LoggedEvent("Received message glassReadyToLeave"));

	}

	@Override
	public void msgDoneWithGlass() {
		log.add(new LoggedEvent("Received message doneWithGlass"));

	}

	@Override
	public void reset() {
		log.add(new LoggedEvent("Reset sensors activated"));

	}
}