package driver;

import glassLine.ConveyorAgent;
import glassLine.GlassRobotAgent;
import glassLine.PopupAgent;
import glassLine.SensorAgent;
import glassLine.data.Glass.GlassType;

public class Driver extends javax.swing.JFrame {

	public static Driver theDriver;

	/** Creates new form Driver */
	private Driver() {
		initComponents();
		initAgents();
	}

	private void initAgents() {

		// Handshakes
		NCCutter.addPreSensor(NCCutterPre);
		NCCutter.addPostSensor(NCCutterPost);
		NCCutter.addConveyor(NCCutConveyor);

		NCCutConveyorEntry.addConveyor(NCCutConveyor);
		NCCutConveyorExit.addConveyor(NCCutConveyor);

		NCCutterPre.addPopup(NCCutter);
		NCCutterPost.addPopup(NCCutter);
		NCCutterPost.addConveyor(NCCutConveyor);

		NCCutConveyor.addEntrySensor(NCCutConveyorEntry);
		NCCutConveyor.addExitSensor(NCCutConveyorExit);
		NCCutConveyor.addNextConveyor(NextConveyor);
		NCCutConveyor.addPrevRobot(GlassRobot);
		NCCutConveyor.addPopup(NCCutter);

		GlassRobot.addConveyor(NCCutConveyor);

		NCCutConveyor.msgStartConveyor();

	}

	private void initComponents() {

		controlPanel = new javax.swing.JPanel();
		startButton = new javax.swing.JToggleButton();
		addGlassButton = new javax.swing.JButton();
		agentTracePanel = new javax.swing.JPanel();
		conveyorAgentCheckBox = new javax.swing.JCheckBox();
		sensorAgentCheckBox = new javax.swing.JCheckBox();
		popupAgentCheckBox = new javax.swing.JCheckBox();
		glassRobotAgentCheckBox = new javax.swing.JCheckBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		agentTraceBox = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		controlPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Factory Controls"));

		startButton.setIcon(new javax.swing.ImageIcon("images/play.png")); // NOI18N
		startButton.setContentAreaFilled(false);
		startButton.setRolloverIcon(new javax.swing.ImageIcon(
				"images/playR.png")); // NOI18N
		startButton.setRolloverSelectedIcon(new javax.swing.ImageIcon(
				"images/pauseR.png")); // NOI18N
		startButton.setSelectedIcon(new javax.swing.ImageIcon(
				"images/pause.png")); // NOI18N
		startButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startButtonActionPerformed(evt);
			}
		});

		addGlassButton.setText("Add glass!"); // NOI18N
		addGlassButton.setEnabled(false);
		addGlassButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addGlassButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(
				controlPanel);
		controlPanel.setLayout(controlPanelLayout);
		controlPanelLayout
				.setHorizontalGroup(controlPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								controlPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												startButton,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												53,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(addGlassButton)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		controlPanelLayout
				.setVerticalGroup(controlPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								controlPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												controlPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																addGlassButton)
														.addComponent(
																startButton))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		controlPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { addGlassButton, startButton });

		agentTracePanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Agent Trace"));

		conveyorAgentCheckBox.setText("Conveyor");
		conveyorAgentCheckBox.setSelected(true);

		sensorAgentCheckBox.setText("Sensor");
		sensorAgentCheckBox.setSelected(true);

		popupAgentCheckBox.setText("Popup");
		popupAgentCheckBox.setSelected(true);

		glassRobotAgentCheckBox.setText("GlassRobot");
		glassRobotAgentCheckBox.setSelected(true);

		agentTraceBox.setColumns(20);
		agentTraceBox.setRows(5);
		agentTraceBox.setEditable(false);
		jScrollPane1.setViewportView(agentTraceBox);

		javax.swing.GroupLayout agentTracePanelLayout = new javax.swing.GroupLayout(
				agentTracePanel);
		agentTracePanel.setLayout(agentTracePanelLayout);
		agentTracePanelLayout
				.setHorizontalGroup(agentTracePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								agentTracePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												agentTracePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																801,
																Short.MAX_VALUE)
														.addGroup(
																agentTracePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				conveyorAgentCheckBox)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				sensorAgentCheckBox)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				popupAgentCheckBox)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				glassRobotAgentCheckBox)))
										.addContainerGap()));

		agentTracePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { conveyorAgentCheckBox,
						glassRobotAgentCheckBox, popupAgentCheckBox,
						sensorAgentCheckBox });

		agentTracePanelLayout
				.setVerticalGroup(agentTracePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								agentTracePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												agentTracePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																conveyorAgentCheckBox)
														.addComponent(
																sensorAgentCheckBox)
														.addComponent(
																popupAgentCheckBox)
														.addComponent(
																glassRobotAgentCheckBox))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												173,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														controlPanel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														agentTracePanel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(controlPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(agentTracePanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}

	private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
		NCCutConveyor.startThread();
		NextConveyor.startThread();
		NCCutter.startThread();
		NCCutConveyorEntry.startThread();
		NCCutConveyorExit.startThread();
		NCCutterPre.startThread();
		NCCutterPost.startThread();
		GlassRobot.startThread();
		startButton.setEnabled(false);
		addGlassButton.setEnabled(true);
	}

	private void addGlassButtonActionPerformed(java.awt.event.ActionEvent evt) {
		print("ADD", "", "");
		GlassRobot.msgScheduleNewGlass(GlassType.Type1);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Driver.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Driver.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Driver.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Driver.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				(theDriver = new Driver()).setVisible(true);
			}
		});
	}

	// Variables declaration
	private javax.swing.JButton addGlassButton;
	private javax.swing.JTextArea agentTraceBox;
	private javax.swing.JPanel agentTracePanel;
	private javax.swing.JPanel controlPanel;
	private javax.swing.JCheckBox conveyorAgentCheckBox;
	private javax.swing.JCheckBox glassRobotAgentCheckBox;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JCheckBox popupAgentCheckBox;
	private javax.swing.JCheckBox sensorAgentCheckBox;
	private javax.swing.JToggleButton startButton;
	// End of variables declaration

	// Agents
	private ConveyorAgent NCCutConveyor = new ConveyorAgent("NCCutConveyor"),
			NextConveyor = new ConveyorAgent("NextConveyor");
	private PopupAgent NCCutter = new PopupAgent("NCCutter");
	private SensorAgent NCCutConveyorEntry = new SensorAgent(
			"NCCutConveyorEntry"), NCCutConveyorExit = new SensorAgent(
			"NCCutConveyorExit"), NCCutterPre = new SensorAgent("NCCutterPre"),
			NCCutterPost = new SensorAgent("NCCutterPost");
	private GlassRobotAgent GlassRobot = new GlassRobotAgent("GlassRobot");

	public static void print(String message, String agentType, String agentName) {
		// if(theDriver.ctr++ > 10) System.exit(0); //DEBUG

		// Filter the output as needed
		if (agentType.contains("ConveyorAgent")
				&& !theDriver.conveyorAgentCheckBox.isSelected())
			return;
		if (agentType.contains("SensorAgent")
				&& !theDriver.sensorAgentCheckBox.isSelected())
			return;
		if (agentType.contains("PopupAgent")
				&& !theDriver.popupAgentCheckBox.isSelected())
			return;
		if (agentType.contains("GlassRobotAgent")
				&& !theDriver.glassRobotAgentCheckBox.isSelected())
			return;

		theDriver.agentTraceBox.append("\n" + agentName + ": " + message);
		theDriver.agentTraceBox.setCaretPosition(theDriver.agentTraceBox
				.getText().length());
	}

	// int ctr=0; //DEBUG

}
