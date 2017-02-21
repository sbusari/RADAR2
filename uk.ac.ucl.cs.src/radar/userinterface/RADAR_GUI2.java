package radar.userinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JEditorPane;
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
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
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
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.lang3.StringUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;

import java.awt.Dimension;

import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;

import java.awt.Color;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class RADAR_GUI2 {

	private JFrame frame;
	private Model semanticModel;
	private AnalysisResult result;
	private boolean modelParsed;
	private boolean modelSolved;
	private int nbr_Simulation;
	private String infoValueObjective;
	private String subGraphObjective;
	private boolean isBoardEnabled;
	private boolean parsed;
	private String openedFilePath;
	private String solvedModel;
	private DefaultTableModel decisionTableModel;
	private ModelResultFrame modelResultFrame;
	private TutorialFrame tutorialFrame;
	private JMenuItem itemWriteModel;
	private JMenuItem itemParseModel;
	private JMenuItem itemSolveModel;
	private JMenuItem itemAbout;
	private JMenuItem itemExit;
	private JMenuItem itemOpen;
	private JMenuItem itemSave;
	private JMenuItem itemPrint;
	private JMenuItem itemEnableBoard;
	private JSeparator separator_8;
	private JMenuItem itemTutorial;
	private JSeparator separator_2;
	private JMenuItem newMenu;
	private JSeparator separator_5;
	private JSeparator separator_7;
	private JMenuItem mntmNewMenuItem;
	private JSeparator separator_9;
	private JMenuItem itemSaveAs;
	JPanel editModel;
	JPanel analysisResult; 
	JTabbedPane tabbedPane;
	JPanel console;
	JScrollPane scrollPaneEditModel;
	JScrollPane scrollPaneAnalysisResult;
	ModelTextPane modelTextPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RADAR_GUI2 window = new RADAR_GUI2();
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
	public RADAR_GUI2() {
		initialize();
		createEvents();
	}
	
	private void createEvents (){
		visualiseModelBoard();
		openExistingModel ();
		saveFile();
		parseModel();
		findOutPutDirectory();
		solveModel();
		enableDiableBoard();
		exitRadar();
		printModel();
		viewTutorial();
		aboutRadar();
		
	}
	private void  enableDiableBoard (){
		itemEnableBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBoardEnabled != true){
					activateModelWriting();
					itemEnableBoard.setText("Disable Model Board");
					
				}else{
					deactivateModelWriting();
					itemEnableBoard.setText("Enable Model Board");
				}
				openedFilePath = "";
			}
		});
	}
	private void viewTutorial (){
		itemTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TutorialFrame();
			}
		});
	}
	private void aboutRadar(){
		itemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutRadar();
			}
		});
	}
	private void exitRadar(){
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = JOptionPane.showConfirmDialog( null , "Are you sure you want to close this window?" , "Confirmation "
	                    , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
				if (selection == JOptionPane.OK_OPTION)
                {
					frame.dispose();
                }
				
			}
		});
	}
	private String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	void activateModelWriting(){
		/*textModelArea.setEnabled(true);
		textAreaScroll.setEnabled(true);
		analysisPanel.setEnabled(true);
		decisionPanel.setEnabled(true);
		decisionTableScrollPane.setEnabled(true);
		textNbrSimulation.setEnabled(true);
		textInfoValueObj.setEnabled(true);
		textSubgraphObj.setEnabled(true);
		textOutputDirectory.setEnabled(true);
		btnFindDirectory.setEnabled(true);
		chckbxVariable.setEnabled(true);
		chckbxDecision.setEnabled(true);
		chckbxPareto.setEnabled(true);
		decisionsTable.setEnabled(true);*/
		isBoardEnabled = true;
	}
	void deactivateModelWriting(){
		/*textModelArea.setEnabled(false);
		textAreaScroll.setEnabled(false);
		analysisPanel.setEnabled(false);
		decisionPanel.setEnabled(false);
		decisionTableScrollPane.setEnabled(false);
		textNbrSimulation.setEnabled(false);
		textInfoValueObj.setEnabled(false);
		textSubgraphObj.setEnabled(false);
		textOutputDirectory.setEnabled(false);
		btnFindDirectory.setEnabled(false);
		chckbxVariable.setEnabled(false);
		chckbxDecision.setEnabled(false);
		chckbxPareto.setEnabled(false);
		decisionsTable.setEnabled(false);*/
		isBoardEnabled = false;
	}
	private void printModel(){
		itemPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*try{
					if (!StringUtils.isEmpty( "" )){ //textModelArea.getText())){
						boolean complete = textModelArea.print();
			            if(complete){
			                JOptionPane.showMessageDialog(null,  "Print Completed!", "Model",JOptionPane.INFORMATION_MESSAGE);
			            }else{
			                JOptionPane.showMessageDialog(null, "Printing", "Printer", JOptionPane.ERROR_MESSAGE);
			            }
					}
		            
		        }
				catch(PrinterException ex){
					JOptionPane.showMessageDialog(null, ex);
					return;
		        }*/
		        
			}
		});
	}
	private void saveFile (){
		final JFileChooser  fileChooser = new JFileChooser();
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String savedPath ="";
					if (!StringUtils.isEmpty( "")){ //textModelArea.getText())){
						if (openedFilePath != null && !StringUtils.isEmpty(openedFilePath)){
							Helper.writeToAFile(openedFilePath, ""); //textModelArea.getText());
							savedPath = openedFilePath;
							JOptionPane.showMessageDialog(null, "File succesfully saved in the path: " + savedPath);
			            	return;
						}else{
							fileChooser.setDialogTitle("Save file"); 
							fileChooser.setAcceptAllFileFilterUsed(true);
							int userSelection = fileChooser.showSaveDialog(frame);
							if (userSelection == JFileChooser.APPROVE_OPTION) {
							    File fileToSave = fileChooser.getSelectedFile();
							    String fileExtension = getFileExtension(fileToSave);
							    if (!fileExtension.equals("rdr")){
				            	   JOptionPane.showMessageDialog(null, "Radar files must end with  the (rdr) extensions");
				            	   return;
							    }
							    Helper.writeToAFile(fileToSave.getAbsolutePath(), ""); //textModelArea.getText());
							    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
							    savedPath = fileToSave.getAbsolutePath();
							    JOptionPane.showMessageDialog(null, "File succesfully saved in the path: " + savedPath);
				            	return;
							}
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "You cannot save an empty board.");
		            	return;
					}
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "There was problem saving the file. Ensure the path stille exist.");
	            	return;
				}
				
			}
		});
	}
	void openExistingModel (){
		final JFileChooser  fileDialog = new JFileChooser();
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBoardEnabled == false){
					JOptionPane.showMessageDialog(null, "Model board must be enabled before any action.");
	            	   return;
				}
				
				int returnVal = fileDialog.showOpenDialog(frame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               java.io.File file = fileDialog.getSelectedFile();
	               String fileExtension = getFileExtension(file);
	               if (!fileExtension.equals("rdr")){
	            	   JOptionPane.showMessageDialog(null, "Radar files must end with  the (rdr) extensions");
	            	   return;
	               }
	               openedFilePath = file.getPath();
	               loadExistingModel(file.getPath());
	               /*if (modelBoard.isVisible() == false){
	            	   modelBoard.setEnabled(true);
	            	   decisionPanel.setEnabled(true);
	   					analysisPanel.setEnabled(true);
	               }*/
	               
	            }
			}
		});
	}
	void visualiseModelBoard(){
		
		itemWriteModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (true){//!textModelArea.getText().isEmpty()){
					int selection = JOptionPane.showConfirmDialog( null , "The content in the model board will be removed. Do you want to continue?" , "Confirmation "
		                    , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
					if (selection == JOptionPane.OK_OPTION)
	                {
						//textModelArea.setText("");
						openedFilePath = "";
	                }
				}
				/*modelBoard.setEnabled(true);
				decisionPanel.setEnabled(true);
				analysisPanel.setEnabled(true);*/
				activateModelWriting();
				isBoardEnabled = true;
				itemEnableBoard.setText("Disable Model Board");
				
			}
		});
	}
	void parseModel (){
		itemParseModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBoardEnabled == false){
					JOptionPane.showMessageDialog(null, "Model board must be enabled before any action.");
					return;
				}
				if (true){//textModelArea.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "You need to write a new decision model or select from existing decision models.");
					return;
				}
				String analysisMsg = allAnalysisSettingValid();
				if (!analysisMsg.isEmpty()){
					JOptionPane.showMessageDialog(null, analysisMsg);
					return;
				}
				if (!doesOutputDirectoryExist()){
					JOptionPane.showMessageDialog(null, "Specified output directory does not exist.");
					return;
				}
				
				parse();
				if (parsed ==true){
					populateDecisionTable();
					int selection = JOptionPane.showConfirmDialog( null , "Model has been parsed successfully. Do you want to continue with analysis?" , "Confirmation "
		                    , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
					if (selection == JOptionPane.OK_OPTION)
	                {
						solve();
						if (modelSolved){
							loadResultInFrame();
							solvedModel = ""; // textModelArea.getText();
						}
						
						
	                }
					modelParsed= true;
				}
				
			}
		});

	}
	private void solveModel (){
		itemSolveModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBoardEnabled == false){
					JOptionPane.showMessageDialog(null, "Model board must be enabled before any action.");
	            	   return;
				}
				if (true){//textModelArea.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "You need to write a new decision model or select from existing decision models.");
					return;
				}
				// also check that there is no changes to the model, otherwise, we parse again.
				if (solvedModel != null){
					if ( !solvedModel.equals("")){//textModelArea.getText())){
						parse();
					}
				}else{
					parse();
				}
				if (semanticModel != null && modelParsed == true) {
					solve();
				}else{
					String analysisMsg = allAnalysisSettingValid();
					if (!analysisMsg.isEmpty()){
						JOptionPane.showMessageDialog(null, analysisMsg);
						return;
					}
					if (!doesOutputDirectoryExist()){
						JOptionPane.showMessageDialog(null, "Specified output director does not exist.");
						return;
					}
					parseModel();
					if (modelParsed == true){
						solve();
					}
					
				}
				if (modelSolved){
					populateDecisionTable();
					loadResultInFrame();
				}
			}
		});
	}
	private String allAnalysisSettingValid (){
		String validationMessage = inputValidation();
		return validationMessage;

	}
	private boolean doesOutputDirectoryExist(){
		boolean result =true;
		try{
			InputValidator.validateOutputPath(""); //textOutputDirectory.getText());
		}catch (Exception e){
			result = false;
			
		}
		return result;
	}
	private String inputValidation (){
		String message = "";
		/*message += InputValidator.verifyEmptyField (textNbrSimulation, "number of simulation", "Integer");
		message += InputValidator.verifyEmptyField (textOutputDirectory, "output directory", "String");
		message += InputValidator.verifyFieldDataType (textNbrSimulation.getText(), "Nbr. Simulation", "Integer");
		message += InputValidator.verifyFieldNonNegativeValue(textNbrSimulation.getText(), "Nbr. Simulation", "Integer");
		*///message += validateOutputDirectoryPath();
		return message;
	}
	private String validateOutputDirectoryPath (){
		String message ="";
		if(true){// !textOutputDirectory.getText().isEmpty()){
			if (true){//textOutputDirectory.getText().equals("--Select--")){
				message += "You must specify the directory to store results.";
				return message;
			}else{
				Pattern pattern = Pattern.compile("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_-]+)+\\\\?");
				Matcher matcher = pattern.matcher(""); //textOutputDirectory.getText());
				if (!matcher.matches()){
					message += "The path specified for the output directory is not valid.";
				}
			}
		}else{
			message+= "You must specify the directory to store results.";
		}
		return message;
	}
	private void populateDecisionTable (){
		if (semanticModel != null){
			decisionTableModel.setNumRows(0);
			List<Decision> decisions = semanticModel.getDecisions();
			//setDecisionTableHeader(decisionTableModel,decisionsTable);
			populateDecisionOptionTable(decisionTableModel, decisions);
		}
	}
	private void setDecisionTableHeader(DefaultTableModel decisionTableModel, JTable tableDecisionOptions){
		ArrayList<String> decisionOption = new ArrayList<String>();
		decisionOption.add("Decision");
		decisionOption.add("Option");
		String[]header =  decisionOption.toArray(new String[decisionOption.size()]);
		decisionTableModel.setColumnIdentifiers(header);
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
			if (semanticModel != null){
				parsed =true;
				modelParsed = true;
			}
		} catch (Exception e) {
			parsed =false;
			String err = e.getMessage();
			JOptionPane.showMessageDialog(null, err);
		}
	}
	
	void loadSolutionTable (DefaultTableModel solutionTableModel){
		List<String> solutions= result.solutionTable();
		for (int i =0; i < solutions.size(); i ++){
			String [] entry = solutions.get(i).split(",");
			Vector<Object> data = new Vector<Object>();
			for (String value: entry){
				data.add(value);
			}
			solutionTableModel.addRow(data);
		}
	}
	void loadOptimisationTable (DefaultTableModel optimiastionTableModel){
		List<String> optimisationDetail= result.optimisationAnalysisDetails();
		for (int i =0; i < optimisationDetail.size(); i ++){
			String [] entry = optimisationDetail.get(i).split(",");
			Vector<Object> data = new Vector<Object>();
			for (String value: entry){
				data.add(value);
			}
			optimiastionTableModel.addRow(data);
		}
	}
	void loadInfoValueTable (DefaultTableModel infoValueTableModel){
		List<String> infoValueDetail= result.infoValueDetails();
		for (int i =0; i < infoValueDetail.size(); i ++){
			String [] entry = infoValueDetail.get(i).split(",");
			Vector<Object> data = new Vector<Object>();
			for (String value: entry){
				data.add(value);
			}
			infoValueTableModel.addRow(data);
		}
	}
	void populateTable (JTable table, String detail){
		DefaultTableModel tableModel =  new DefaultTableModel( );
		if (detail.equals("solution")){
			tableModel.setColumnIdentifiers(result.getSolutionTableColumnIdentifier().split(","));
			loadSolutionTable(tableModel);
		}else if (detail.equals("optimisation")){
			tableModel.setColumnIdentifiers(new String[]{"Optimisation Analysis", " "});
			loadOptimisationTable(tableModel);
		}else if (detail.equals("infoValue")){
			tableModel.setColumnIdentifiers(new String[]{"Information Value Analysis", " "});
			loadInfoValueTable(tableModel);
		}
		table.setModel(tableModel);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	
	}
	void loadResultInFrame (){
		if (result == null  || result.getShortListObjectives().size() <= 0){
			JOptionPane.showMessageDialog(null, "No results found!");
			return;
		}
		if (semanticModel == null){
			JOptionPane.showMessageDialog(null, "Model needs to be parsed and analysed for results to show!");
			return;
		}
		modelResultFrame  = new ModelResultFrame();
		
		JTable solutionTable = modelResultFrame.getSolutionTable();
		populateTable(solutionTable, "solution");
		JTable optimisationTable = modelResultFrame.getOptimisationAnalysisTable();
		populateTable(optimisationTable, "optimisation");
		JTable infoValueTable = modelResultFrame.getInfoValueTable();
		populateTable(infoValueTable, "infoValue");
		
		modelResultFrame.setVisible(true);
		modelResultFrame.setTitle("Analysis Results" );
		modelResultFrame.pack();
		modelResultFrame.setLocationRelativeTo(null);
		modelResultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	void solve (){
		
		try {
			// analyse model
			result = ModelSolver.solve(semanticModel);
			String analysisResult = result.analysisToString();
			String analysisResultToCSV = result.analysisResultToCSV();
			String modelResultPath = ""; //textOutputDirectory.getText() +"/" + semanticModel.getModelName() + "/ICSE/AnalysisResult/" ;
			
			Helper.printResults (modelResultPath , analysisResult, semanticModel.getModelName() +".out", false);
			Helper.printResults (modelResultPath , analysisResultToCSV, semanticModel.getModelName() +".csv", false);
			
			// generate graphs
			/*if (chckbxVariable.isSelected()){
				String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, result.getSubGraphObjective());
				Helper.printResults (modelResultPath + "graph/", variableGraph, semanticModel.getModelName() +"vgraph.dot", false);
				
			}
			if (chckbxDecision.isSelected()){
				String decisionGraph = semanticModel.generateDecisionDiagram(result.getAllSolutions());
				Helper.printResults (modelResultPath + "graph/", decisionGraph, semanticModel.getModelName() + "dgraph.dot", false);
				
			}
			if (chckbxPareto.isSelected()){
				String imageOutput = modelResultPath + "/";
				if (result.getShortListObjectives().get(0).length == 2){
					TwoDPlotter twoDPlot = new TwoDPlotter();
					twoDPlot.plot(semanticModel,imageOutput, result);
				}else if (result.getShortListObjectives().get(0).length == 3){
					ScatterPlot3D sc3D2= new ScatterPlot3D( );
					sc3D2.plot(semanticModel, imageOutput, result);
				}
			}*/
			System.out.println("Finished!");
			modelSolved =true;
			solvedModel = ""; // textModelArea.getText();
		
		} 
		catch (IOException e) {
			modelSolved = false;
			JOptionPane.showMessageDialog(null, "There was a problem writing the results to directory. Check that the path exist: ");
			return;
		}
		catch (NullPointerException e){
			modelSolved = false;
			JOptionPane.showMessageDialog(null,"There was a problem during model analysis. Details:" + e.getMessage());
			return;
		}
		catch ( RuntimeException e){
			modelSolved = false;
			JOptionPane.showMessageDialog(null, "There was a problem during model analysis. Details:" + e.getMessage());
			return;
		}
		
	}
	private boolean infoValueObjectiveExist(){
		boolean result = true;
		try{
			if (infoValueObjective != null){
				InputValidator.objectiveExist(semanticModel, infoValueObjective);
			}
		}catch(Exception e){
			result = false;
		}
		return result;
	}
	private boolean subGraphObjectiveExist(){
		boolean result = true;
		try{
			if(subGraphObjective != null){
				InputValidator.objectiveExist(semanticModel, subGraphObjective);
			}
		}catch (Exception e){
			result = false;
		}
		return result;
	}
	private Model loadModel () throws Exception{
		Model semanticModel =null;
		try {
			/*nbr_Simulation = Integer.parseInt(textNbrSimulation.getText());
			subGraphObjective = textSubgraphObj.getText();
			infoValueObjective = textInfoValueObj.getText();
			semanticModel = new Parser().parseUIModel(textModelArea.getText(), nbr_Simulation, infoValueObjective,subGraphObjective);
*/		}catch (RuntimeException re){
			parsed = false;
			String err = re.getMessage();
			JOptionPane.showMessageDialog(null, err);
		}
		return semanticModel;
	}
	private boolean loadExistingModel ( String modelPath){
		boolean done = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(modelPath)))) {
			//textModelArea.read(reader, "File");
        	done = true;
        } catch (IOException exp) {
        	String err = exp.getMessage();
			JOptionPane.showMessageDialog(null, err);
        }
		return done;
	}

	private void findOutPutDirectory(){
		final JFileChooser  fileDialog = new JFileChooser();
	}
	/*private void populateDecisionTable(){
		decisionTableModel = new DefaultTableModel();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("RADAR- Requirements engineering And Architecture Decisions Analyser");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		newMenu = new JMenuItem("New");
		newMenu.setPreferredSize(new Dimension(55, 19));
		fileMenu.add(newMenu);
		
		separator_5 = new JSeparator();
		fileMenu.add(separator_5);
		
		itemOpen = new JMenuItem("Open");
		
		fileMenu.add(itemOpen);
		
		JSeparator separator_4 = new JSeparator();
		fileMenu.add(separator_4);
		
		itemSave = new JMenuItem("Save");
		
		fileMenu.add(itemSave);
		
		JSeparator separator_6 = new JSeparator();
		fileMenu.add(separator_6);
		
		itemSaveAs = new JMenuItem("Save As");
		fileMenu.add(itemSaveAs);
		
		JSeparator separator_10 = new JSeparator();
		fileMenu.add(separator_10);
		
		itemPrint = new JMenuItem("Print");
		
		fileMenu.add(itemPrint);
		
		separator_9 = new JSeparator();
		fileMenu.add(separator_9);
		
		mntmNewMenuItem = new JMenuItem("Export");
		fileMenu.add(mntmNewMenuItem);
		
		JSeparator separator_19 = new JSeparator();
		fileMenu.add(separator_19);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Examples");
		fileMenu.add(mntmNewMenuItem_2);
		
		JSeparator separator_20 = new JSeparator();
		fileMenu.add(separator_20);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		fileMenu.add(mntmNewMenuItem_3);
		
		separator_7 = new JSeparator();
		fileMenu.add(separator_7);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mnEdit.add(mntmCut);
		
		JSeparator separator_15 = new JSeparator();
		mnEdit.add(separator_15);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEdit.add(mntmCopy);
		
		JSeparator separator_16 = new JSeparator();
		mnEdit.add(separator_16);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);
		
		JSeparator separator_17 = new JSeparator();
		mnEdit.add(separator_17);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnEdit.add(mntmUndo);
		
		JSeparator separator_18 = new JSeparator();
		mnEdit.add(separator_18);
		
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mnEdit.add(mntmRedo);
		
		JMenu actionMenu = new JMenu("Action");
		menuBar.add(actionMenu);
		
		itemWriteModel = new JMenuItem("Write Model");
		
		
		
		actionMenu.add(itemWriteModel);
		
		JSeparator separator = new JSeparator();
		actionMenu.add(separator);
		
		itemParseModel = new JMenuItem("Parse");
		
		
		actionMenu.add(itemParseModel);
		
		JSeparator separator_1 = new JSeparator();
		actionMenu.add(separator_1);
		
		itemSolveModel = new JMenuItem("Solve");
		
		actionMenu.add(itemSolveModel);
		
		JMenu mnWindow = new JMenu("Analysis Results");
		menuBar.add(mnWindow);
		
		JCheckBoxMenuItem chckbxmntmOptimisationAnalysis = new JCheckBoxMenuItem("Optimisation Analysis");
		chckbxmntmOptimisationAnalysis.setSelected(true);
		mnWindow.add(chckbxmntmOptimisationAnalysis);
		
		JSeparator separator_11 = new JSeparator();
		mnWindow.add(separator_11);
		
		JCheckBoxMenuItem chckbxmntmInformationValueAnalysis = new JCheckBoxMenuItem("Information Value Analysis");
		chckbxmntmInformationValueAnalysis.setSelected(true);
		mnWindow.add(chckbxmntmInformationValueAnalysis);
		
		JSeparator separator_12 = new JSeparator();
		mnWindow.add(separator_12);
		
		JCheckBoxMenuItem chckbxmntmVariableAndorGraph = new JCheckBoxMenuItem("Variable AND/OR Graph");
		mnWindow.add(chckbxmntmVariableAndorGraph);
		
		JSeparator separator_13 = new JSeparator();
		mnWindow.add(separator_13);
		
		JCheckBoxMenuItem chckbxmntmDecisionDependencyGraph = new JCheckBoxMenuItem("Decision Dependency Graph");
		mnWindow.add(chckbxmntmDecisionDependencyGraph);
		
		JSeparator separator_14 = new JSeparator();
		mnWindow.add(separator_14);
		
		JCheckBoxMenuItem chckbxmntmParetoFront = new JCheckBoxMenuItem("Pareto Front");
		mnWindow.add(chckbxmntmParetoFront);
		
		JMenu mnNewMenu = new JMenu("Optimiser Algorithm");
		menuBar.add(mnNewMenu);
		
		JCheckBoxMenuItem chckbxmntmExhaustiveSearch = new JCheckBoxMenuItem("Exhaustive Search");
		chckbxmntmExhaustiveSearch.setSelected(true);
		mnNewMenu.add(chckbxmntmExhaustiveSearch);
		
		JCheckBoxMenuItem chckbxmntmNsgaii = new JCheckBoxMenuItem("NSGAII");
		mnNewMenu.add(chckbxmntmNsgaii);
		
		JCheckBoxMenuItem chckbxmntmSpeaii = new JCheckBoxMenuItem("SPEAII");
		mnNewMenu.add(chckbxmntmSpeaii);
		
		JCheckBoxMenuItem chckbxmntmMoga = new JCheckBoxMenuItem("MOGA");
		mnNewMenu.add(chckbxmntmMoga);
		
		JCheckBoxMenuItem chckbxmntmIbea = new JCheckBoxMenuItem("IBEA");
		mnNewMenu.add(chckbxmntmIbea);
		
		JMenu mnAnalysisSettings = new JMenu("Options");
		menuBar.add(mnAnalysisSettings);
		
		JMenu radarMenu = new JMenu("Help");
		menuBar.add(radarMenu);
		
		itemEnableBoard = new JMenuItem("Enable Model Board");
		
		radarMenu.add(itemEnableBoard);
		
		separator_8 = new JSeparator();
		radarMenu.add(separator_8);
		
		itemAbout = new JMenuItem("About Radar");
		
			radarMenu.add(itemAbout);
			
			separator_2 = new JSeparator();
			radarMenu.add(separator_2);
			
			itemTutorial = new JMenuItem("How to Use");
			
				radarMenu.add(itemTutorial);
				
				JSeparator separator_3 = new JSeparator();
				radarMenu.add(separator_3);
				
				itemExit = new JMenuItem("Exit");
				
				radarMenu.add(itemExit);
		
		JToolBar toolFile = new JToolBar();
		toolFile.setFloatable(false);
		toolFile.setToolTipText("New File");
		
		JToolBar toolBar = new JToolBar();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		analysisResult = new JPanel();
		analysisResult.setForeground(Color.LIGHT_GRAY);
		
		editModel = new JPanel();
		editModel.setForeground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Edit Model",editModel);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		
		modelTextPane = new ModelTextPane();
		
		scrollPaneEditModel = new JScrollPane(modelTextPane);
		scrollPaneEditModel.setPreferredSize(new Dimension(850, 450));
		editModel.add(scrollPaneEditModel);
		
		
		
		
		tabbedPane.addTab("Analysis Result",analysisResult);
		
		scrollPaneAnalysisResult = new JScrollPane();
		scrollPaneAnalysisResult.setPreferredSize(new Dimension(850, 450));
		analysisResult.add(scrollPaneAnalysisResult);
		tabbedPane.setBackgroundAt(1, Color.GRAY);
		
		
		//populateDecisionTable();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(toolFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(toolFile, GroupLayout.DEFAULT_SIZE, 5, Short.MAX_VALUE)
							.addGap(677))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		console = new JPanel();
		tabbedPane.addTab("Console", null, console, null);
		console.setForeground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPaneConsole = new JScrollPane();
		scrollPaneConsole.setPreferredSize(new Dimension(850, 450));
		console.add(scrollPaneConsole);
		console.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{scrollPaneConsole}));
		
		JButton btnNewFile = new JButton("");
		btnNewFile.setToolTipText("New File");
		btnNewFile.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/NewFileIcon.png"));
		toolBar.add(btnNewFile);
		
		JButton btnOpenFile = new JButton("");
		btnOpenFile.setToolTipText("Open File");
		btnOpenFile.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/OpenFileIcon.png"));
		toolBar.add(btnOpenFile);
		
		JButton btnSaveFile = new JButton("");
		btnSaveFile.setToolTipText("Save File");
		btnSaveFile.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/SaveFileIcon.png"));
		toolBar.add(btnSaveFile);
		
		JButton btnExportFile = new JButton("");
		btnExportFile.setToolTipText("Export File");
		btnExportFile.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/ExportIcon.png"));
		toolBar.add(btnExportFile);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar.add(toolBar_1);
		
		JButton btnCut = new JButton("");
		btnCut.setToolTipText("Cut");
		btnCut.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/CutFileIcon.png"));
		toolBar_1.add(btnCut);
		
		JButton btnCopy = new JButton("");
		btnCopy.setToolTipText("Copy");
		btnCopy.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/CopyFileIcon.png"));
		toolBar_1.add(btnCopy);
		
		JButton btnPaste = new JButton("");
		btnPaste.setToolTipText("Paste");
		btnPaste.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/PasteFileIcon.png"));
		toolBar_1.add(btnPaste);
		
		JButton btnUndo = new JButton("");
		btnUndo.setToolTipText("Undo");
		btnUndo.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/UndoIcon.png"));
		toolBar_1.add(btnUndo);
		
		JButton btnRedo = new JButton("");
		btnRedo.setToolTipText("Redo");
		btnRedo.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/RedoIcon.png"));
		toolBar_1.add(btnRedo);
		
		JToolBar toolBar_2 = new JToolBar();
		toolBar_1.add(toolBar_2);
		
		JButton btnParse = new JButton("");
		btnParse.setToolTipText("Parse Model");
		btnParse.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/ParseIcon.png"));
		toolBar_2.add(btnParse);
		
		JButton btnSolve = new JButton("");
		btnSolve.setToolTipText("Solve");
		btnSolve.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/SolveIcon.png"));
		toolBar_2.add(btnSolve);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DEFAULT-Exhaustive Search", "NSGA-II", "SPEA-II", "MOGA", "IBEA"}));
		comboBox.setPreferredSize(new Dimension(400, 27));
		toolBar_2.add(comboBox);
		
		JButton btnStop = new JButton("");
		btnStop.setToolTipText("Stop Analysis");
		btnStop.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/StopIcon2.png"));
		toolBar_2.add(btnStop);
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_2.add(toolBar_3);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Optimisation Analysis");
		btnNewButton.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/OptimisationIcon.png"));
		toolBar_3.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setToolTipText("Information value Analysis");
		btnNewButton_2.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/InfoValueIcon.png"));
		toolBar_3.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Pareto Front");
		btnNewButton_1.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/ParetoFrontIcon.png"));
		toolBar_3.add(btnNewButton_1);
		
		JSeparator separator_21 = new JSeparator();
		toolFile.add(separator_21);
		
		JSeparator separator_22 = new JSeparator();
		toolFile.add(separator_22);
		
		decisionTableModel = new DefaultTableModel();
		frame.getContentPane().setLayout(groupLayout);
	}
}
