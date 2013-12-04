package glassLine.test.Mock;

import glassLine.data.Glass;
import glassLine.data.Glass.GlassType;
import glassLine.interfaces.GlassRobotAgentInterface;

public class MockGlassRobot extends MockAgent implements
		GlassRobotAgentInterface {

	public MockGlassRobot(String name) {
		super(name);
		log.add(new LoggedEvent("MockGlassRobot Constructor activated"));
	}

	public EventLog log = new EventLog();

	@Override
	public void msgIAmReadyForNewGlass() {
		log.add(new LoggedEvent("Received message IAmReadyForNewGlass"));

	}

	@Override
	public void msgScheduleNewGlass(GlassType t) {
		log.add(new LoggedEvent("Received message ScheduleNewGlass"));

	}

	@Override
	public void msgInPosition() {
		log.add(new LoggedEvent("Received message InPosition"));

	}

	@Override
	public void msgPickedUp() {
		log.add(new LoggedEvent("Received message PickedUp"));

	}

	@Override
	public void prepGlass(Glass g) {
		log.add(new LoggedEvent("Received message prepGlass"));

	}

	@Override
	public void getGlassToPosition(Glass g) {
		log.add(new LoggedEvent("Received message GlassToPosition"));

	}

	@Override
	public void loadUpGlass(Glass g) {
		log.add(new LoggedEvent("Received message loadUpGlass"));

	}

}
