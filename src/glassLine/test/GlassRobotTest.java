package glassLine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import glassLine.GlassRobotAgent;
import glassLine.data.Glass.GlassType;
import glassLine.interfaces.ConveyorAgentInterface;
import glassLine.test.Mock.MockConveyor;

import org.junit.Test;

public class GlassRobotTest {

	public GlassRobotAgent glassRobot; // Unit under test

	@Test
	public void testMsgIAmReadyForNewGlass() {

		GlassRobotAgent robot = new GlassRobotAgent("GlassRobot");
		ConveyorAgentInterface conveyor = new MockConveyor("MockConveyor");
		robot.addConveyor(conveyor);

		robot.msgIAmReadyForNewGlass();

	}

	@Test
	public void testMsgScheduleNewGlass() {
		GlassRobotAgent robot = new GlassRobotAgent("GlassRobot");
		MockConveyor conveyor = new MockConveyor("MockConveyor");
		robot.addConveyor(conveyor);

		robot.msgScheduleNewGlass(GlassType.Type1);
		assertEquals(
				"The Mock Conveyor should have an empty event log before the GlassRobot's scheduler is called."
						+ "Instead, the Mock Conveyor's event log reads: "
						+ conveyor.log.toString(), 0, conveyor.log.size());

		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgAreYouReady. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Received message AreYouReady"));

		assertEquals(
				"Only 1 message should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 1, conveyor.log.size());

	}

	@Test
	public void testOneGlassCycle() {
		GlassRobotAgent robot = new GlassRobotAgent("GlassRobot");
		MockConveyor conveyor = new MockConveyor("MockConveyor");

		robot.addConveyor(conveyor);

		robot.msgScheduleNewGlass(GlassType.Type1);
		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgAreYouReady. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Received message AreYouReady"));

		assertEquals(
				"Only 1 message should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 1, conveyor.log.size());

		robot.msgIAmReadyForNewGlass();
		robot.pickAndExecuteAnAction();

		// robot.msgInPosition();
		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgHereIsGlass. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Recieved message HereIsGlass"));

		assertEquals(
				"2 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 2, conveyor.log.size());
	}

	@Test
	public void testTwoGlassCycle() {
		GlassRobotAgent robot = new GlassRobotAgent("GlassRobot");
		MockConveyor conveyor = new MockConveyor("MockConveyor");

		robot.addConveyor(conveyor);

		// Glass 1
		robot.msgScheduleNewGlass(GlassType.Type1);
		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgAreYouReady. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Received message AreYouReady"));

		assertEquals(
				"Only 1 message should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 1, conveyor.log.size());

		robot.msgIAmReadyForNewGlass();
		robot.pickAndExecuteAnAction();

		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgHereIsGlass. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Recieved message HereIsGlass"));

		assertEquals(
				"2 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 2, conveyor.log.size());

		// Glass 2
		robot.msgScheduleNewGlass(GlassType.Type2);
		robot.pickAndExecuteAnAction();

		assertEquals(
				"3 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 3, conveyor.log.size());

		robot.msgIAmReadyForNewGlass();
		robot.pickAndExecuteAnAction();

		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgHereIsGlass. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Recieved message HereIsGlass"));

		assertEquals(
				"4 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 4, conveyor.log.size());

	}

	@Test
	public void testTwoGlassCycleWithNotReady() {
		GlassRobotAgent robot = new GlassRobotAgent("GlassRobot");
		MockConveyor conveyor = new MockConveyor("MockConveyor");

		robot.addConveyor(conveyor);

		// Glass 1
		robot.msgScheduleNewGlass(GlassType.Type1);
		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgAreYouReady. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Received message AreYouReady"));

		assertEquals(
				"Only 1 message should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 1, conveyor.log.size());

		robot.msgIAmReadyForNewGlass();
		robot.pickAndExecuteAnAction();

		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgHereIsGlass. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Recieved message HereIsGlass"));

		assertEquals(
				"2 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 2, conveyor.log.size());

		// Glass 2
		robot.msgScheduleNewGlass(GlassType.Type2);
		robot.pickAndExecuteAnAction();

		assertEquals(
				"3 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 3, conveyor.log.size());

		// pretend not ready
		robot.pickAndExecuteAnAction();

		assertEquals(
				"3 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 3, conveyor.log.size());

		// now ready
		robot.msgIAmReadyForNewGlass();
		robot.pickAndExecuteAnAction();

		// robot.msgInPosition();
		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgHereIsGlass. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Recieved message HereIsGlass"));

		assertEquals(
				"4 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 4, conveyor.log.size());

	}

	@Test
	public void testDoubleGlassCycle() {
		GlassRobotAgent robot = new GlassRobotAgent("GlassRobot");
		MockConveyor conveyor = new MockConveyor("MockConveyor");

		robot.addConveyor(conveyor);

		robot.msgScheduleNewGlass(GlassType.Type1);
		robot.msgScheduleNewGlass(GlassType.Type2);
		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgAreYouReady. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Received message AreYouReady"));

		assertEquals(
				"Only 1 message should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 1, conveyor.log.size());

		robot.msgIAmReadyForNewGlass();
		robot.pickAndExecuteAnAction();

		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgHereIsGlass. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Recieved message HereIsGlass"));

		assertEquals(
				"2 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 2, conveyor.log.size());

		// Glass 2
		robot.pickAndExecuteAnAction();

		assertEquals(
				"3 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 3, conveyor.log.size());

		robot.msgIAmReadyForNewGlass();
		robot.pickAndExecuteAnAction();

		robot.pickAndExecuteAnAction();

		assertTrue(
				" The Mock Conveyor should have received msgHereIsGlass. Event log: "
						+ conveyor.log.toString(),
				conveyor.log.containsString("Recieved message HereIsGlass"));

		assertEquals(
				"4 messages should have been sent to the conveyor. Event log: "
						+ conveyor.log.toString(), 4, conveyor.log.size());

	}

}
