package glassLine;

import glassLine.data.Glass;
import glassLine.data.Glass.GlassType;
import glassLine.interfaces.ConveyorAgentInterface;
import glassLine.interfaces.GlassRobotAgentInterface;

import java.util.ArrayList;
import java.util.List;

import agent.Agent;

public class GlassRobotAgent extends Agent implements GlassRobotAgentInterface {
	String name;

	public List<Glass> glassQueue = new ArrayList<Glass>();
	public Glass glassInHand;
	public myConveyor loadIn;

	public class myConveyor {
		ConveyorAgentInterface c;
		public ConveyorStatus s;

		// constructor
		private myConveyor(ConveyorAgentInterface c) {
			this.c = c;
			s = ConveyorStatus.NOTREADY;
		}
	}

	public enum ConveyorStatus {
		READY, NOTREADY
	}

	public GlassRobotAgent(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void addConveyor(ConveyorAgentInterface ca) {
		loadIn = new myConveyor(ca);
	}

	// MESSAGES
	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.GlassRobotAgentInterface#msgIAmReadyForNewGlass()
	 */
	@Override
	public void msgIAmReadyForNewGlass() {
		print("Received message IAmReadyForNewGlass");
		loadIn.s = ConveyorStatus.READY;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * glassLine.GlassRobotAgentInterface#msgScheduleNewGlass(glassLine.data
	 * .Glass.GlassType)
	 */
	@Override
	public void msgScheduleNewGlass(GlassType t) {
		print("Received new glass order of glass type " + t);
		Glass newOrder = new Glass(t);
		glassQueue.add(newOrder);
		print("Factory queue contains " + glassQueue.size());
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.GlassRobotAgentInterface#msgInPosition()
	 */
	@Override
	public void msgInPosition() {
		print("Received message InPosition");
		glassInHand.myStatus = Glass.GlassStatus.InPosition;
		stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.GlassRobotAgentInterface#msgPickedUp()
	 */
	@Override
	public void msgPickedUp() {
		print("Received message PickedUp");
		glassInHand.myStatus = Glass.GlassStatus.Loaded;
		stateChanged();
	}

	// SCHEDULER

	@Override
	public boolean pickAndExecuteAnAction() {
		for (Glass g : glassQueue) {
			if (g.myStatus == Glass.GlassStatus.InPosition
					&& loadIn.s == ConveyorStatus.READY) {
				loadUpGlass(g);
				return true;
			}
		}
		for (Glass g : glassQueue) {
			if (g.myStatus == Glass.GlassStatus.Loaded
					&& loadIn.s == ConveyorStatus.READY) {
				getGlassToPosition(g);
				return true;
			}
		}
		for (Glass g : glassQueue) {
			if (g.myStatus == Glass.GlassStatus.Ordered && glassInHand == null) {
				prepGlass(g);
				return true;
			}
		}
		return false;
	}

	// ACTIONS
	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.GlassRobotAgentInterface#prepGlass(glassLine.data.Glass)
	 */
	@Override
	public void prepGlass(Glass g) {
		print("Picking up Glass " + g);
		glassInHand = g;
		// transducer.fireEvent(channel.GUI, TEvent.Get_Glass, args);
		glassInHand.myStatus = Glass.GlassStatus.Loading;
		loadIn.c.msgAreYouReady();
		// TODO: hack until transducer is set up
		this.msgPickedUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * glassLine.GlassRobotAgentInterface#getGlassToPosition(glassLine.data.
	 * Glass)
	 */
	@Override
	public void getGlassToPosition(Glass g) {
		print("Moving glass into position");
		// transducer.fireEvent(channel.GUI, TEvent.Position_Glass, args);
		glassInHand.myStatus = Glass.GlassStatus.MovingToPosition;
		// TODO: hack until transducer is set up
		this.msgInPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see glassLine.GlassRobotAgentInterface#loadUpGlass(glassLine.data.Glass)
	 */
	@Override
	public void loadUpGlass(Glass g) {
		print("Glass " + g + " is loaded up, giving it to " + loadIn.c);
		// transducer.fireEvent(channel.GUI, TEvent.Load_Glass, args);
		loadIn.c.msgHereIsGlass(g);
		loadIn.s = ConveyorStatus.NOTREADY;
		glassInHand.myStatus = Glass.GlassStatus.InProduction;
		glassInHand = null;
	}

}
