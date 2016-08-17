package radar.userinterface;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

import radar.model.AnalysisResult;
import radar.model.Decision;
import radar.model.Model;
import radar.model.ModelSolver;
import radar.model.Parser;
import radar.model.ScatterPlot3D;
import radar.model.TwoDPlotter;
import radar.utilities.Helper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class RADAR_GUI {

	private JFrame frame;
	private Model semanticModel;
	private int nbr_Simulation;
	private String infoValueObjective;
	private String subGraphObjective;
	private DefaultTableModel decisionTableModel;
	private JPanel modelBoard;
	private JMenuItem itemWriteModel;
	private JMenuItem itemParseModel;
	private JMenuItem itemSolveModel;
	private JMenuItem itemAbout;
	private JMenuItem itemTutorial;
	private JMenuItem itemExit;
	private JMenuItem itemOpen;
	private JMenuItem itemSave;
	private JMenuItem itemExport;
	private JMenuItem itemPrint;
	private JMenuItem itemSetModel;
	private JMenuItem itemSetAlg;
	private JTextArea textModelArea;
	private JScrollPane textAreaScroll;
	private JPanel analysisPanel;
	private JPanel decisionPanel;
	private JScrollPane decisionTableScrollPane;
	private JTextField textNbrSimulation;
	private JTextField textInfoValueObj;
	private JTextField textSubgraphObj;
	private JTextField textOutputDirectory;
	private JButton btnFindDirectory;
	private JCheckBox chckbxVariable;
	private JCheckBox chckbxDecision;
	private JCheckBox chckbxPareto;
	private JTable decisionsTable;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RADAR_GUI window = new RADAR_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RADAR_GUI() {
		initialize();
		createEvents();
	}
	
	private void createEvents (){
		visualiseModelBoard();
		openExistingModel ();
		parseModel();
		findOutPutDirectory();
		
	}
	private String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	void openExistingModel (){
		final JFileChooser  fileDialog = new JFileChooser();
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialog.showOpenDialog(frame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               java.io.File file = fileDialog.getSelectedFile();
	               String fileExtension = getFileExtension(file);
	               if (!fileExtension.equals("rdr")){
	            	   JOptionPane.showMessageDialog(null, "Radar files must end with  the (rdr) extensions");
	            	   return;
	               }
	               //check the extension of the file.
	               loadExistingModel(file.getPath());
	               if (modelBoard.isVisible() == false){
	            	   modelBoard.setVisible(true);
	            	   decisionPanel.setVisible(true);
	   					analysisPanel.setVisible(true);
	               }
	               
	            }
			}
		});
	}
	void visualiseModelBoard(){
		
		itemWriteModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelBoard.setVisible(true);
				decisionPanel.setVisible(true);
				analysisPanel.setVisible(true);
			}
		});
	}
	void parseModel (){
		itemParseModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textModelArea.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "You need to write a new decision model or select from existing decision models.");
					return;
				}
				validateAnalysisSettings();
				parse();
				populateDecisionTable();
				int selection = JOptionPane.showConfirmDialog( null , "Model has been parsed successfully. Do you want to continue with analysis?" , "Confirmation "
	                    , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
				if (selection == JOptionPane.OK_OPTION)
                {
					solveModel();
                }
			}
		});
	}
	private void validateAnalysisSettings (){
		String errorMessage = "";
		String emptyValidationMessage = EmptyInputValidation();
		if (!emptyValidationMessage.isEmpty()){
			errorMessage += emptyValidationMessage;
			JOptionPane.showMessageDialog(null, errorMessage);
			return;
		}
	}
	private String EmptyInputValidation (){
		String message = "";
		message += new InputValidator().verifyEmptyField (textNbrSimulation, "number of simulation", "Integer");
		message += new InputValidator().verifyEmptyField (textOutputDirectory, "output directory", "String");
		return message;
	}
	private void populateDecisionTable (){
		decisionTableModel.setNumRows(0);
		List<Decision> decisions = semanticModel.getDecisions();
		setDecisionTableHeader(decisionTableModel,decisionsTable);
		populateDecisionOptionTable(decisionTableModel, decisions);
	}
	private void setDecisionTableHeader(DefaultTableModel decisionTableModel, JTable tableDecisionOptions){
		ArrayList<String> decisionOption = new ArrayList<String>();
		decisionOption.add("Decision");
		decisionOption.add("Option");
		String[]header =  decisionOption.toArray(new String[decisionOption.size()]);
		decisionTableModel.setColumnIdentifiers(header);
		tableDecisionOptions.setModel(decisionTableModel);
	}
	private void fillDecisionTable (Map<String, List<String>> decisionsEntry){
		if (decisionsEntry != null){
			for (Map.Entry<String, List<String>> entry: decisionsEntry.entrySet()){
				Vector<Object> data = new Vector<Object>();
				data.add(entry.getKey());
				String options ="[";
				for (String option : entry.getValue()){
					options += option + ", ";
				}
				options = options.trim(); // remove last white space.
				options = options.substring(0, options.length()-1);
				options +="]";
				data.add(options);
				decisionTableModel.addRow(data);
			}
		}
	}
	private void populateDecisionOptionTable (DefaultTableModel decisionTableModel,List<Decision> decisions){
		
		Map<String, List<String>> decisionsEntry = new LinkedHashMap<String, List<String>>();
		for (Decision d  : decisions){
			decisionsEntry.put(d.getDecisionLabel(), d.getOptions());
		}
		fillDecisionTable(decisionsEntry);		
	}
	void parse (){
		try {
			semanticModel = loadModel();
		} catch (Exception e) {
			String err = e.getMessage();
			JOptionPane.showMessageDialog(null, err);
		}
	}
	void solveModel (){
		// analyse model
		try {
			
			AnalysisResult result = ModelSolver.solve(semanticModel);
			String analysisResult = result.analysisToString();
			Helper.printResults (textOutputDirectory + semanticModel.getModelName()+ "/" , analysisResult, semanticModel.getModelName() +".out", false);
			
			// generate graphs
			if (chckbxVariable.isSelected()){
				String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, result.getSubGraphObjective());
				Helper.printResults (textOutputDirectory + semanticModel.getModelName()+ "/graph/", variableGraph, "vgraph.dot", false);
				
			}
			if (chckbxDecision.isSelected()){
				String decisionGraph = semanticModel.generateDecisionDiagram();
				Helper.printResults (textOutputDirectory+ semanticModel.getModelName()+ "/graph/", decisionGraph, "dgraph.dot", false);
				
			}
			if (chckbxPareto.isSelected()){
				String imageOutput = textOutputDirectory + semanticModel.getModelName() + "/";
				if (result.getShortListObjectives().get(0).length == 2){
					TwoDPlotter twoDPlot = new TwoDPlotter();
					twoDPlot.plot(semanticModel,imageOutput, result);
				}else if (result.getShortListObjectives().get(0).length == 3){
					ScatterPlot3D sc3D2= new ScatterPlot3D( );
					sc3D2.plot(semanticModel, imageOutput, result);
				}
			}
			System.out.println("Finished!");
		
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was a problem writing the results to directory. Details: "+ e.getMessage());
			return;
		}
		catch (NullPointerException e){
			JOptionPane.showMessageDialog(null,"There was a problem during model analysis. Details:" + e.getMessage());
			return;
		}
		catch ( RuntimeException e){
			JOptionPane.showMessageDialog(null, "There was a problem during model analysis. Details:" + e.getMessage());
			return;
		}
		
	}
	private Model loadModel () throws Exception{
		Model semanticModel =null;
		try {
			semanticModel = new Parser().parseUIModel(textModelArea.getText(), nbr_Simulation, infoValueObjective,subGraphObjective);
		}catch (RuntimeException re){
			String err = re.getMessage();
			JOptionPane.showMessageDialog(null, err);
		}
		return semanticModel;
	}
	private boolean loadExistingModel ( String modelPath){
		boolean done = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(modelPath)))) {
			textModelArea.read(reader, "File");
        	done = true;
        } catch (IOException exp) {
        	String err = exp.getMessage();
			JOptionPane.showMessageDialog(null, err);
        }
		return done;
	}
	private void findOutPutDirectory(){
		final JFileChooser  fileDialog = new JFileChooser();
		btnFindDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileDialog.setCurrentDirectory(new java.io.File("."));
				fileDialog.setDialogTitle("Output Directory");
				fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileDialog.setAcceptAllFileFilterUsed(false);
				int returnVal = fileDialog.showOpenDialog(frame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               textOutputDirectory.setText(fileDialog.getSelectedFile().getAbsolutePath().toString());
	            }
	            else{
	            	textOutputDirectory.setText("--Select--" );           
	            } 
			}
		});
	}
	/*private void populateDecisionTable(){
		decisionTableModel = new DefaultTableModel();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("RADAR- Requirements engineering And Architecture Decisions Analyser");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu radarMenu = new JMenu("Radar");
		menuBar.add(radarMenu);
		
		itemAbout = new JMenuItem("About");
		radarMenu.add(itemAbout);
		
		JSeparator separator_2 = new JSeparator();
		radarMenu.add(separator_2);
		
		itemTutorial = new JMenuItem("Tutorial");
		radarMenu.add(itemTutorial);
		
		JSeparator separator_3 = new JSeparator();
		radarMenu.add(separator_3);
		
		itemExit = new JMenuItem("Exit");
		radarMenu.add(itemExit);
		
		JMenu mnNewMenu_2 = new JMenu("File");
		menuBar.add(mnNewMenu_2);
		
		itemOpen = new JMenuItem("Open");
		
		mnNewMenu_2.add(itemOpen);
		
		JSeparator separator_4 = new JSeparator();
		mnNewMenu_2.add(separator_4);
		
		itemSave = new JMenuItem("Save");
		mnNewMenu_2.add(itemSave);
		
		JSeparator separator_5 = new JSeparator();
		mnNewMenu_2.add(separator_5);
		
		itemExport = new JMenuItem("Export");
		mnNewMenu_2.add(itemExport);
		
		JSeparator separator_6 = new JSeparator();
		mnNewMenu_2.add(separator_6);
		
		itemPrint = new JMenuItem("Print");
		mnNewMenu_2.add(itemPrint);
		
		JMenu fileMenu = new JMenu("Action");
		menuBar.add(fileMenu);
		
		itemWriteModel = new JMenuItem("Write Model");
		
		
		
		fileMenu.add(itemWriteModel);
		
		JSeparator separator = new JSeparator();
		fileMenu.add(separator);
		
		itemParseModel = new JMenuItem("Parse Model");
		
		
		fileMenu.add(itemParseModel);
		
		JSeparator separator_1 = new JSeparator();
		fileMenu.add(separator_1);
		
		itemSolveModel = new JMenuItem("Solve Model");
		fileMenu.add(itemSolveModel);
		
		JMenu settingMenu = new JMenu("Settings");
		menuBar.add(settingMenu);
		
		itemSetModel = new JMenuItem("Model Settings");
		settingMenu.add(itemSetModel);
		
		JSeparator separator_7 = new JSeparator();
		settingMenu.add(separator_7);
		
		itemSetAlg = new JMenuItem("Algorithm Settings");
		settingMenu.add(itemSetAlg);
		
		modelBoard = new JPanel();
		modelBoard.setVisible(false);
		modelBoard.setBorder(new TitledBorder(null, "Model Board", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		analysisPanel = new JPanel();
		analysisPanel.setVisible(false);
		analysisPanel.setBorder(new TitledBorder(null, "Analysis Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		decisionPanel = new JPanel();
		decisionPanel.setVisible(false);
		
		//populateDecisionTable();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(modelBoard, GroupLayout.PREFERRED_SIZE, 715, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(analysisPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addComponent(decisionPanel, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(analysisPanel, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(decisionPanel, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(modelBoard, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		decisionTableScrollPane = new JScrollPane();
		decisionTableScrollPane.setBorder(new TitledBorder(null, "Model decisions and options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		decisionTableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_decisionPanel = new GroupLayout(decisionPanel);
		gl_decisionPanel.setHorizontalGroup(
			gl_decisionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_decisionPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(decisionTableScrollPane, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_decisionPanel.setVerticalGroup(
			gl_decisionPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_decisionPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(decisionTableScrollPane, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		decisionTableModel = new DefaultTableModel();
		decisionsTable = new JTable (){
			private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		decisionsTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		decisionTableScrollPane.setViewportView(decisionsTable);
		
		
		decisionPanel.setLayout(gl_decisionPanel);
		setDecisionTableHeader(decisionTableModel,decisionsTable);
		
		JLabel lblNbrSimulation = new JLabel("Nbr. Simulation:");
		
		textNbrSimulation = new JTextField();
		textNbrSimulation.setText("10000");
		textNbrSimulation.setColumns(10);
		
		JLabel lblInfoValueObj = new JLabel("Info. Value Objective:");
		
		textInfoValueObj = new JTextField();
		textInfoValueObj.setColumns(10);
		
		JLabel lblSUbGraphObj = new JLabel("SubGraph Objective:");
		
		textSubgraphObj = new JTextField();
		textSubgraphObj.setColumns(10);
		
		JLabel lblOutputDir = new JLabel("Output Directory:");
		
		textOutputDirectory = new JTextField();
		textOutputDirectory.setText("--Select--");
		textOutputDirectory.setColumns(10);
		
		JLabel lblDOTGraph = new JLabel("DOT Graph:");
		
		chckbxVariable = new JCheckBox("Variable");
		chckbxVariable.setSelected(true);
		
		chckbxDecision = new JCheckBox("Decision");
		chckbxDecision.setSelected(true);
		
		JLabel lblPlot = new JLabel("Plot:");
		
		chckbxPareto = new JCheckBox("Pareto Optimal");
		chckbxPareto.setSelected(true);
		
		btnFindDirectory = new JButton("Find");
		
		GroupLayout gl_analysisPanel = new GroupLayout(analysisPanel);
		gl_analysisPanel.setHorizontalGroup(
			gl_analysisPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_analysisPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblNbrSimulation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(32))
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblSUbGraphObj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblOutputDir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(24))
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblDOTGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(61))
						.addComponent(lblInfoValueObj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblPlot, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addGap(51)))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addGroup(gl_analysisPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(chckbxPareto)
								.addGroup(gl_analysisPanel.createSequentialGroup()
									.addComponent(chckbxVariable, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(chckbxDecision))
								.addComponent(textOutputDirectory))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnFindDirectory)
							.addContainerGap())
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addGroup(gl_analysisPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textNbrSimulation, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(textSubgraphObj, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(textInfoValueObj, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
							.addGap(142))))
		);
		gl_analysisPanel.setVerticalGroup(
			gl_analysisPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_analysisPanel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNbrSimulation)
						.addComponent(textNbrSimulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInfoValueObj)
						.addComponent(textInfoValueObj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSUbGraphObj)
						.addComponent(textSubgraphObj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOutputDir)
						.addComponent(textOutputDirectory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFindDirectory))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxVariable)
						.addComponent(chckbxDecision)
						.addComponent(lblDOTGraph))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxPareto)
						.addComponent(lblPlot))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		analysisPanel.setLayout(gl_analysisPanel);
		
		textAreaScroll = new JScrollPane();
		GroupLayout gl_modelBoard = new GroupLayout(modelBoard);
		gl_modelBoard.setHorizontalGroup(
			gl_modelBoard.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_modelBoard.createSequentialGroup()
					.addContainerGap()
					.addComponent(textAreaScroll, GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_modelBoard.setVerticalGroup(
			gl_modelBoard.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modelBoard.createSequentialGroup()
					.addComponent(textAreaScroll, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		textModelArea = new JTextArea();
		textAreaScroll.setViewportView(textModelArea);
		modelBoard.setLayout(gl_modelBoard);
		frame.getContentPane().setLayout(groupLayout);
	}
}
