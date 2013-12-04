package glassLine.test.Mock;

import glassLine.data.Glass;
import glassLine.interfaces.PopupAgentInterface;

public class MockPopup extends MockAgent implements PopupAgentInterface {
	public EventLog log = new EventLog();

	public MockPopup(String name) {
		super(name);
	}

	@Override
	public void msgGoUp() {
		log.add(new LoggedEvent("Received message goUp"));

	}

	@Override
	public void msgGoDown() {
		log.add(new LoggedEvent("Received message goDown"));

	}

	@Override
	public void msgIsPopupReady() {
		log.add(new LoggedEvent("Received message isPopupReady"));

	}

	@Override
	public void msgYouAreReady() {
		log.add(new LoggedEvent("Received message youAreReady"));

	}

	@Override
	public void msgHereIsPretreatedGlass(Glass g) {
		log.add(new LoggedEvent("Received message hereIsPretreatedGlass"));

	}

	@Override
	public void msgConvIsReady() {
		log.add(new LoggedEvent("Received message conveyorIsReady"));

	}

	@Override
	public boolean isEmpty() {
		log.add(new LoggedEvent("isEmpty called"));
		return false;
	}

	@Override
	public void reset() {
		log.add(new LoggedEvent("Reset popup activated"));

	}
}
