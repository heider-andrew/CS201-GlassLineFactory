package glassLine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import glassLine.SensorAgent;
import glassLine.test.Mock.MockConveyor;
import glassLine.test.Mock.MockPopup;

import org.junit.Test;

public class SensorTest {

	@Test
	public void testMsgAmIReadyConveyorAgentInterface() {
		SensorAgent pre = new SensorAgent("pre"), post = new SensorAgent("post");
		MockPopup popup = new MockPopup("popup");
		MockConveyor conv = new MockConveyor("conveyor");
		pre.addPopup(popup);
		pre.addConveyor(conv);
		post.addPopup(popup);
		pre.addConveyor(conv);

		pre.msgAmIReady(conv);
		assertEquals(
				"The popup should have an empty event log before the presensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log before the presensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertTrue("Should be expecting a glass on the conveyor",
				pre.expectingGlassOnConv);

		pre.pickAndExecuteAnAction();
		assertTrue("conveyor should be told that it is ready",
				conv.log.containsString("Received message YouAreReady"));
	}

	@Test
	public void testMsgAmIReadyPopupAgentInterface() {
		SensorAgent pre = new SensorAgent("pre"), post = new SensorAgent("post");
		MockPopup popup = new MockPopup("popup");
		MockConveyor conv = new MockConveyor("conveyor");
		pre.addPopup(popup);
		pre.addConveyor(conv);
		post.addPopup(popup);
		pre.addConveyor(conv);

		pre.msgAmIReady(popup);
		assertEquals(
				"The popup should have an empty event log before the presensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log before the presensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertTrue("Should be expecting a glass on the popup",
				pre.expectingGlassonPopup);

		pre.pickAndExecuteAnAction();
		assertTrue("popup should be told that it is ready.",
				popup.log.containsString("Received message youAreReady"));

	}

	@Test
	public void testMsgIHaveGlassConveyorAgentInterface() {
		SensorAgent pre = new SensorAgent("pre"), post = new SensorAgent("post");
		MockPopup popup = new MockPopup("popup");
		MockConveyor conv = new MockConveyor("conveyor");
		pre.addPopup(popup);
		pre.addConveyor(conv);
		post.addPopup(popup);
		pre.addConveyor(conv);

		pre.msgIHaveGlass(conv);
		assertEquals(
				"The popup should have an empty event log before the presensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log before the presensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		pre.pickAndExecuteAnAction();
		assertEquals(
				"The popup should have an empty event log after the presensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log after the presensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());

	}

	@Test
	public void testMsgIHaveGlassPopupAgentInterface() {
		SensorAgent pre = new SensorAgent("pre"), post = new SensorAgent("post");
		MockPopup popup = new MockPopup("popup");
		MockConveyor conv = new MockConveyor("conveyor");
		pre.addPopup(popup);
		pre.addConveyor(conv);
		post.addPopup(popup);
		pre.addConveyor(conv);

		pre.msgIHaveGlass(popup);
		assertEquals(
				"The popup should have an empty event log before the presensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log before the presensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		pre.pickAndExecuteAnAction();
		assertEquals(
				"The popup should have an empty event log after the presensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log after the presensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());

	}

	@Test
	public void testMsgIsConvReady() {
		SensorAgent pre = new SensorAgent("pre"), post = new SensorAgent("post");
		MockPopup popup = new MockPopup("popup");
		MockConveyor conv = new MockConveyor("conveyor");
		pre.addPopup(popup);
		pre.addConveyor(conv);
		post.addPopup(popup);
		post.addConveyor(conv);

		post.msgAmIReady(conv);
		assertEquals(
				"The popup should have an empty event log before the postsensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log before the postsensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertTrue("Should be expecting a glass on the conveyor",
				post.expectingGlassOnConv);

		post.pickAndExecuteAnAction();
		assertTrue("conveyor should be told that it is ready.",
				conv.log.containsString("Received message YouAreReady"));

	}

	@Test
	public void testMsgGlassReadyToLeave() {
		SensorAgent pre = new SensorAgent("pre"), post = new SensorAgent("post");
		MockPopup popup = new MockPopup("popup");
		MockConveyor conv = new MockConveyor("conveyor");
		pre.addPopup(popup);
		pre.addConveyor(conv);
		post.addPopup(popup);
		post.addConveyor(conv);

		post.msgGlassReadyToLeave();
		assertEquals(
				"The popup should have an empty event log before the postsensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log before the postsensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		post.pickAndExecuteAnAction();
		assertTrue(
				"conveyor should be told told to clear." + conv.log.toString(),
				conv.log.containsString("Received message Clear"));
	}

	@Test
	public void testMsgDoneWithGlass() {
		SensorAgent pre = new SensorAgent("pre"), post = new SensorAgent("post");
		MockPopup popup = new MockPopup("popup");
		MockConveyor conv = new MockConveyor("conveyor");
		pre.addPopup(popup);
		pre.addConveyor(conv);
		post.addPopup(popup);
		post.addConveyor(conv);

		pre.msgDoneWithGlass();
		assertEquals(
				"The popup should have an empty event log before the postsensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log before the postsensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());

		pre.pickAndExecuteAnAction();
		assertEquals(
				"The popup should have an empty event log after the presensor's scheduler is called."
						+ "Instead, the popup's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
		assertEquals(
				"The conveyor should have an empty event log after the presensor's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ popup.log.toString(), 0, popup.log.size());
	}

}
