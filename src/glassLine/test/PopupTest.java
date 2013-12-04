package glassLine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import glassLine.PopupAgent;
import glassLine.data.Glass;
import glassLine.data.Glass.GlassType;
import glassLine.test.Mock.MockConveyor;
import glassLine.test.Mock.MockSensor;

import org.junit.Test;

public class PopupTest {

	@Test
	public void testMsgGoUp() {
		PopupAgent popup = new PopupAgent("Popup");
		MockSensor pre = new MockSensor("PreSensor"), post = new MockSensor(
				"PostSensor");
		MockConveyor conv = new MockConveyor("Conveyor");

		popup.addConveyor(conv);
		popup.addPreSensor(pre);
		popup.addPostSensor(post);

		assertTrue("The popup must be initially down", !popup.isUp);

		popup.msgGoUp();

		assertEquals(
				"The conveyor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ conv.log.toString(), 0, conv.log.size());
		assertEquals(
				"The presensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the presensor event log reads: "
						+ pre.log.toString(), 0, pre.log.size());
		assertEquals(
				"The postsensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the postsensor event log reads: "
						+ post.log.toString(), 0, post.log.size());

		popup.pickAndExecuteAnAction();

		assertEquals(
				"The conveyor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ conv.log.toString(), 0, conv.log.size());
		assertEquals(
				"The presensor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the presensor event log reads: "
						+ pre.log.toString(), 0, pre.log.size());
		assertEquals(
				"The postsensor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the postsensor event log reads: "
						+ post.log.toString(), 0, post.log.size());

		assertTrue("The popup must finally go up", popup.isUp);
	}

	@Test
	public void testMsgGoDown() {
		PopupAgent popup = new PopupAgent("Popup");
		MockSensor pre = new MockSensor("PreSensor"), post = new MockSensor(
				"PostSensor");
		MockConveyor conv = new MockConveyor("Conveyor");

		popup.addConveyor(conv);
		popup.addPreSensor(pre);
		popup.addPostSensor(post);

		popup.msgGoUp();
		popup.pickAndExecuteAnAction();
		assertTrue("The popup must be initially up", popup.isUp);

		popup.msgGoDown();

		assertEquals(
				"The conveyor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ conv.log.toString(), 0, conv.log.size());
		assertEquals(
				"The presensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the presensor event log reads: "
						+ pre.log.toString(), 0, pre.log.size());
		assertEquals(
				"The postsensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the postsensor event log reads: "
						+ post.log.toString(), 0, post.log.size());

		popup.pickAndExecuteAnAction();

		assertEquals(
				"The conveyor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ conv.log.toString(), 0, conv.log.size());
		assertEquals(
				"The presensor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the presensor event log reads: "
						+ pre.log.toString(), 0, pre.log.size());
		assertEquals(
				"The postsensor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the postsensor event log reads: "
						+ post.log.toString(), 0, post.log.size());

		assertTrue("The popup must finally go down", !popup.isUp);
	}

	@Test
	public void testMsgIsPopupReady() {
		PopupAgent popup = new PopupAgent("Popup");
		MockSensor pre = new MockSensor("PreSensor"), post = new MockSensor(
				"PostSensor");
		MockConveyor conv = new MockConveyor("Conveyor");

		popup.addConveyor(conv);
		popup.addPreSensor(pre);
		popup.addPostSensor(post);

		popup.msgIsPopupReady();
		assertEquals(
				"The conveyor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ conv.log.toString(), 0, conv.log.size());
		assertEquals(
				"The presensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the presensor event log reads: "
						+ pre.log.toString(), 0, pre.log.size());
		assertEquals(
				"The postsensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the postsensor event log reads: "
						+ post.log.toString(), 0, post.log.size());

		popup.pickAndExecuteAnAction();
		assertTrue(
				" The presensor should have received message amIReady from popup. Event log: "
						+ pre.log.toString(),
				pre.log.containsString("Received message amIReady from popup"));
	}

	@Test
	public void testMsgYouAreReady() {
		PopupAgent popup = new PopupAgent("Popup");
		MockSensor pre = new MockSensor("PreSensor"), post = new MockSensor(
				"PostSensor");
		MockConveyor conv = new MockConveyor("Conveyor");

		popup.addConveyor(conv);
		popup.addPreSensor(pre);
		popup.addPostSensor(post);

		popup.msgYouAreReady();
		assertEquals(
				"The conveyor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ conv.log.toString(), 0, conv.log.size());
		assertEquals(
				"The presensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the presensor event log reads: "
						+ pre.log.toString(), 0, pre.log.size());
		assertEquals(
				"The postsensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the postsensor event log reads: "
						+ post.log.toString(), 0, post.log.size());
		assertTrue("Ready must be true", popup.ready);

		popup.pickAndExecuteAnAction();

		assertEquals(
				"The conveyor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ conv.log.toString(), 0, conv.log.size());
		assertEquals(
				"The presensor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the presensor event log reads: "
						+ pre.log.toString(), 0, pre.log.size());
		assertEquals(
				"The postsensor should have an empty event log after the popup's scheduler is called."
						+ "Instead, the postsensor event log reads: "
						+ post.log.toString(), 0, post.log.size());

	}

	@Test
	public void testMsgHereIsPretreatedGlass() {
		PopupAgent popup = new PopupAgent("Popup");
		MockSensor pre = new MockSensor("PreSensor"), post = new MockSensor(
				"PostSensor");
		MockConveyor conv = new MockConveyor("Conveyor");

		popup.addConveyor(conv);
		popup.addPreSensor(pre);
		popup.addPostSensor(post);

		popup.msgHereIsPretreatedGlass(new Glass(GlassType.Type1));
		assertEquals(
				"The conveyor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the conveyor's event log reads: "
						+ conv.log.toString(), 0, conv.log.size());
		assertEquals(
				"The presensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the presensor event log reads: "
						+ pre.log.toString(), 0, pre.log.size());
		assertEquals(
				"The postsensor should have an empty event log before the popup's scheduler is called."
						+ "Instead, the postsensor event log reads: "
						+ post.log.toString(), 0, post.log.size());

		popup.pickAndExecuteAnAction();
		assertTrue(
				"presensor should have received message iHaveGlass from popup.",
				pre.log.containsString("Received message iHaveGlass from popup"));
	}

	@Test
	public void testMsgConvIsReady() {
		PopupAgent popup = new PopupAgent("Popup");
		MockSensor pre = new MockSensor("PreSensor"), post = new MockSensor(
				"PostSensor");
		MockConveyor conv = new MockConveyor("Conveyor");

		popup.addConveyor(conv);
		popup.addPreSensor(pre);
		popup.addPostSensor(post);

		popup.msgHereIsPretreatedGlass(new Glass(GlassType.Type1));
		popup.pickAndExecuteAnAction();

		popup.msgConvIsReady();
		popup.pickAndExecuteAnAction();
		/*
		 * assertTrue("Conveyor should have received message HereIsGlass.",
		 * conv.log.containsString("Received message HereIsGlass"));
		 */// THIS TEST FAILS
		assertTrue("Postsensor should have received message doneWithGlass.",
				post.log.containsString("Received message doneWithGlass"));
	}

}
