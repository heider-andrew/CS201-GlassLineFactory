package glassLine.test.Mock;

import glassLine.data.Glass;
import glassLine.interfaces.ConveyorAgentInterface;

public class MockConveyor extends MockAgent implements ConveyorAgentInterface {

	public EventLog log = new EventLog();

	public MockConveyor(String name) {
		super(name);
	}

	@Override
	public void msgStartConveyor() {
		log.add(new LoggedEvent("Received message StartConveyor"));

	}

	@Override
	public void checkIfReady() {
		log.add(new LoggedEvent("Received message CheckIfReady"));

	}

	@Override
	public void informReady() {
		log.add(new LoggedEvent("Received message informReady"));

	}

	@Override
	public void informNewGlass() {
		log.add(new LoggedEvent("informNewGlass called"));

	}

	@Override
	public void bypass() {
		log.add(new LoggedEvent("bypass called"));

	}

	@Override
	public void startProcess() {
		log.add(new LoggedEvent("startProcess called"));

	}

	@Override
	public void giveGlassToPopup() {
		log.add(new LoggedEvent("giveGlassToPopup called"));

	}

	@Override
	public void startDoneProcess() {
		log.add(new LoggedEvent("startDoneProcess called"));

	}

	@Override
	public void doneWithGlass() {
		log.add(new LoggedEvent("doneWithGlass called"));

	}

	@Override
	public void msgAreYouReady() {
		// System.out.println("WHAT IS WRONG!?!?!");
		log.add(new LoggedEvent("Received message AreYouReady"));

	}

	@Override
	public void msgHereIsGlass(Glass g) {
		log.add(new LoggedEvent("Recieved message HereIsGlass"));

	}

	@Override
	public void msgIAmReady() {
		log.add(new LoggedEvent("Received message IAmReady"));

	}

	@Override
	public void msgHereIsTreatedGlass(Glass glass) {
		log.add(new LoggedEvent("Received message HereIsTreatedGlass"));

	}

	@Override
	public void msgClear() {
		log.add(new LoggedEvent("Received message Clear"));

	}

	@Override
	public void msgYouAreReady() {
		log.add(new LoggedEvent("Received message YouAreReady"));

	}

}
