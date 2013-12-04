package glassLine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import glassLine.ConveyorAgent;
import glassLine.data.Glass;
import glassLine.data.Glass.GlassType;
import glassLine.test.Mock.MockConveyor;
import glassLine.test.Mock.MockGlassRobot;
import glassLine.test.Mock.MockPopup;
import glassLine.test.Mock.MockSensor;

import org.junit.Test;

public class ConveyorTest {

	@Test
	public void testMsgStartConveyor() {
		ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
		MockConveyor nextConveyor = new MockConveyor("next");
		MockSensor entry = new MockSensor("entry"), exit = new MockSensor(
				"exit");
		MockGlassRobot robot = new MockGlassRobot("robot");

		conveyor.addEntrySensor(entry);
		conveyor.addExitSensor(exit);
		conveyor.addNextConveyor(nextConveyor);
		conveyor.addPrevRobot(robot);

		conveyor.msgStartConveyor();
		assertEquals(
				"The next conveyor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The entry sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the entry sensor's event log reads: "
						+ entry.log.toString(), 0, entry.log.size());
		assertEquals(
				"The exit sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ exit.log.toString(), 0, exit.log.size());
		assertEquals(
				"The glass robot should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ robot.log.toString(), 0, robot.log.size());

		conveyor.pickAndExecuteAnAction();
		assertEquals(
				"The next conveyor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The entry sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the entry sensor's event log reads: "
						+ entry.log.toString(), 0, entry.log.size());
		assertEquals(
				"The exit sensor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ exit.log.toString(), 0, exit.log.size());
		assertEquals(
				"The glass robot should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ robot.log.toString(), 0, robot.log.size());

	}

	@Test
	public void testMsgStopConveyor() {
		ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
		MockConveyor nextConveyor = new MockConveyor("next");
		MockSensor entry = new MockSensor("entry"), exit = new MockSensor(
				"exit");
		MockGlassRobot robot = new MockGlassRobot("robot");

		conveyor.addEntrySensor(entry);
		conveyor.addExitSensor(exit);
		conveyor.addNextConveyor(nextConveyor);
		conveyor.addPrevRobot(robot);

		conveyor.msgStopConveyor();
		assertEquals(
				"The next conveyor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The entry sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the entry sensor's event log reads: "
						+ entry.log.toString(), 0, nextConveyor.log.size());
		assertEquals(
				"The exit sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ exit.log.toString(), 0, nextConveyor.log.size());
		assertEquals(
				"The glass robot should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ robot.log.toString(), 0, nextConveyor.log.size());

		conveyor.pickAndExecuteAnAction();
		assertEquals(
				"The next conveyor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The entry sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the entry sensor's event log reads: "
						+ entry.log.toString(), 0, nextConveyor.log.size());
		assertEquals(
				"The exit sensor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ exit.log.toString(), 0, nextConveyor.log.size());
		assertEquals(
				"The glass robot should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ robot.log.toString(), 0, nextConveyor.log.size());
	}

	@Test
	public void testMsgAreYouReady() {
		ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
		MockConveyor nextConveyor = new MockConveyor("next");
		MockSensor entry = new MockSensor("entry"), exit = new MockSensor(
				"exit");
		MockGlassRobot robot = new MockGlassRobot("robot");

		conveyor.addEntrySensor(entry);
		conveyor.addExitSensor(exit);
		conveyor.addNextConveyor(nextConveyor);
		conveyor.addPrevRobot(robot);

		conveyor.msgAreYouReady();
		assertEquals(
				"The next conveyor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The entry sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the entry sensor's event log reads: "
						+ entry.log.toString(), 0, entry.log.size());
		assertEquals(
				"The exit sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ exit.log.toString(), 0, exit.log.size());
		assertEquals(
				"The glass robot should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ robot.log.toString(), 0, robot.log.size());

		conveyor.pickAndExecuteAnAction();
		assertEquals(
				"The next conveyor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertTrue(
				"The entry sensor should have reveived the message amIReady",
				entry.log.containsString("Received message amIReady"));
		assertEquals(
				"The exit sensor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ exit.log.toString(), 0, exit.log.size());
		assertEquals(
				"The glass robot should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ robot.log.toString(), 0, robot.log.size());

	}

	@Test
	public void testMsgYouAreReady() {
		ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
		MockConveyor nextConveyor = new MockConveyor("next");
		MockSensor entry = new MockSensor("entry"), exit = new MockSensor(
				"exit");
		MockGlassRobot robot = new MockGlassRobot("robot");

		conveyor.addEntrySensor(entry);
		conveyor.addExitSensor(exit);
		conveyor.addNextConveyor(nextConveyor);
		conveyor.addPrevRobot(robot);

		conveyor.msgYouAreReady();
		assertEquals(
				"The next conveyor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The entry sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the entry sensor's event log reads: "
						+ entry.log.toString(), 0, entry.log.size());
		assertEquals(
				"The exit sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ exit.log.toString(), 0, exit.log.size());
		assertEquals(
				"The glass robot should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ robot.log.toString(), 0, robot.log.size());

		conveyor.pickAndExecuteAnAction();
		assertEquals(
				"The next conveyor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertTrue("Robot sensor have received msgIAmReadyForNewGlass",
				robot.log
						.containsString("Received message IAmReadyForNewGlass"));
		assertEquals(
				"The exit sensor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ exit.log.toString(), 0, exit.log.size());
		assertEquals(
				"The entry sensor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the sensor's event log reads: "
						+ entry.log.toString(), 0, entry.log.size());
	}

	@Test
	public void testMsgHereIsGlass() {
		ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
		MockConveyor nextConveyor = new MockConveyor("next");
		MockSensor entry = new MockSensor("entry"), exit = new MockSensor(
				"exit");
		MockGlassRobot robot = new MockGlassRobot("robot");

		conveyor.addEntrySensor(entry);
		conveyor.addExitSensor(exit);
		conveyor.addNextConveyor(nextConveyor);
		conveyor.addPrevRobot(robot);

		conveyor.msgHereIsGlass(new Glass(GlassType.Type1));
		assertEquals(
				"The next conveyor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The entry sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the entry sensor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The exit sensor should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The glass robot should have an empty event log before the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());

		conveyor.pickAndExecuteAnAction();
		assertEquals(
				"The next conveyor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the next conveyor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertTrue(
				"Entry sensor should have received msgIHaveGlass from the conveyor",
				entry.log
						.containsString("Received message iHaveGlass from conveyor"));
		assertEquals(
				"The exit sensor should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the exit sensor's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
		assertEquals(
				"The glass robot should have an empty event log after the conveyor's scheduler is called."
						+ "Instead, the robot's event log reads: "
						+ nextConveyor.log.toString(), 0,
				nextConveyor.log.size());
	}

	@Test
	public void testMsgIAmReady() {
		ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
		MockConveyor nextConveyor = new MockConveyor("next");
		MockSensor entry = new MockSensor("entry"), exit = new MockSensor(
				"exit");
		MockGlassRobot robot = new MockGlassRobot("robot");
		MockPopup popup = new MockPopup("popup");

		conveyor.addEntrySensor(entry);
		conveyor.addExitSensor(exit);
		conveyor.addNextConveyor(nextConveyor);
		conveyor.addPrevRobot(robot);
		conveyor.addPopup(popup);
		conveyor.msgHereIsGlass(new Glass(GlassType.Type1));
		conveyor.pickAndExecuteAnAction();
		conveyor.msgIAmReady();

		conveyor.pickAndExecuteAnAction();
		assertTrue("Popup should have received isPopupReady ",
				popup.log.containsString("Received message isPopupReady"));
	}

	@Test
	public void testMsgHereIsTreatedGlass() {
		ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
		MockConveyor nextConveyor = new MockConveyor("next");
		MockSensor entry = new MockSensor("entry"), exit = new MockSensor(
				"exit");
		MockGlassRobot robot = new MockGlassRobot("robot");
		MockPopup popup = new MockPopup("popup");

		conveyor.addEntrySensor(entry);
		conveyor.addExitSensor(exit);
		conveyor.addNextConveyor(nextConveyor);
		conveyor.addPrevRobot(robot);
		conveyor.addPopup(popup);
		conveyor.msgHereIsGlass(new Glass(GlassType.Type1));
		conveyor.pickAndExecuteAnAction();
		conveyor.msgIAmReady();
		conveyor.pickAndExecuteAnAction();

		conveyor.msgHereIsTreatedGlass(new Glass(GlassType.Type1));
		conveyor.pickAndExecuteAnAction();
		assertTrue("Exit sensor should have received msgGlassReadyToLeave",
				exit.log.containsString("Received message glassReadyToLeave"));
	}

	@Test
	public void testMsgClear() {

	}

	@Test
	public void testMsgIAmReadyForNewGlass() {

	}

}
