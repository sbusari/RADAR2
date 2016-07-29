package radar.experiment.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import radar.jmetal.core.SolutionSet;
import radar.model.Parser;
import radar.utilities.SolutionDetail;

public class UIResult {
	private Parser goalParserEngine_;
	private SolutionSet exactSolutions_;
	private SolutionSet approximateSolution_;
	String nonDominatedSolutionPlotPath_;
	String dominatedSolutionPlotPath_;
	String expBaseDirectory_;
	String[] algorithmList_;
	List<ArrayList<String>> shortlist_;
	LinkedHashMap<String, List<ArrayList<String>>> shortlistAndOtherSolutions_;
	String shortlistHeader_;
	LinkedHashMap<Integer, LinkedHashMap<String,SolutionDetail>> allAlgorithmShortLists_;
	LinkedHashMap<Integer, LinkedHashMap<String, LinkedHashMap<String,Double>>> allAlgorithmEVPPI_;
	LinkedHashMap<Integer, LinkedHashMap<String, LinkedHashMap<String,Double>>> allAlgorithmEVTPI_;
	public Parser getGoalModelParserEngine (){
		return goalParserEngine_;
	}
	public void setGoalModelParserEngine (Parser goalParserEngine){
		 goalParserEngine_ = goalParserEngine;
	}
	public SolutionSet getExactSolutions (){
		return exactSolutions_;
	}
	public void setExactSolutions (SolutionSet exactSolutions){
		exactSolutions_ = exactSolutions;
	}
	public String[] getAlgorithmList(){
		return algorithmList_;
	}
	public void setAlgorithmList (String[] algorithmList){
		algorithmList_ = algorithmList;
	}
	public void setShortlistHeader (String shortlistHeader){
		shortlistHeader_ = shortlistHeader;
	}
	public String getShortlistHeader (){
		return shortlistHeader_;
	}
	public void setNonDominatedSolutionPlotPath(String nonDominatedSolutionPlotPath){
		nonDominatedSolutionPlotPath_ = nonDominatedSolutionPlotPath;
	}
	public String getNonDominatedSolutionPlotPath(){
		return nonDominatedSolutionPlotPath_;
	}
	public void setDominatedSolutionPlotPath(String dominatedSolutionPlotPath){
		dominatedSolutionPlotPath_ = dominatedSolutionPlotPath;
	}
	public String getDominatedSolutionPlotPath(){
		return dominatedSolutionPlotPath_;
	}
	
	public void setExpBaseDirectory(String expBaseDirectory){
		expBaseDirectory_ = expBaseDirectory;
	}
	public String getExpBaseDirectory(){
		return expBaseDirectory_;
	}
	
	public SolutionSet getApproximateSolution (){
		return approximateSolution_;
	}
	public void setApproximateSolution (SolutionSet approximateSolution){
		approximateSolution_ = approximateSolution;
	}
	public List<ArrayList<String>> getShortlist (){
		return shortlist_;
	}
	public void setShortlist (List<ArrayList<String>> shortlist){
			shortlist_ = shortlist;
		
	}
	public LinkedHashMap<String, List<ArrayList<String>>> getShortlistAndOtherSolutions (){
		return shortlistAndOtherSolutions_;
	}
	public void setShortlistAndOtherSolutions(LinkedHashMap<String, List<ArrayList<String>>> shortlistAndOtherSolutions){
		shortlistAndOtherSolutions_ = shortlistAndOtherSolutions;
	}
	public LinkedHashMap<Integer, LinkedHashMap<String,SolutionDetail>> getAllAlgorithmShortLists (){
		
		return allAlgorithmShortLists_;
	}
	public void setAllAlgorithmShortLists (LinkedHashMap<Integer, LinkedHashMap<String,SolutionDetail>> allAlgorithmShortLists){
		allAlgorithmShortLists_ = allAlgorithmShortLists;
	}
	public LinkedHashMap<Integer, LinkedHashMap<String, LinkedHashMap<String,Double>>> getAllAlgorithmEVPPI (){
		return allAlgorithmEVPPI_;
	}
	public void setAllAlgorithmEVPPI(LinkedHashMap<Integer, LinkedHashMap<String, LinkedHashMap<String,Double>>> allAlgorithmEVPPI){
		allAlgorithmEVPPI_ = allAlgorithmEVPPI;
	}
	public LinkedHashMap<Integer, LinkedHashMap<String, LinkedHashMap<String,Double>>> getAllAlgorithmEVTPI (){
		return allAlgorithmEVTPI_;
	}
	public void setAllAlgorithmEVTPI(LinkedHashMap<Integer, LinkedHashMap<String, LinkedHashMap<String,Double>>> allAlgorithmEVTPI){
		allAlgorithmEVTPI_ = allAlgorithmEVTPI;
	}
	

}
