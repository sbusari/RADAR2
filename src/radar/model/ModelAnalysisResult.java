package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.enumeration.OptimisationType;
import radar.information.analysis.InformationValueAnalysis;
import radar.optimisation.algorithm.Algorithm;
import radar.optimisation.algorithm.ExhaustiveSearch;


public class ModelAnalysisResult {

	List<Objective> obejctives_;
	List<Alternative> solutions_;
	double evtpi_;
	Map<String, Double> evppi_;
	Model semanticModel_;
	OptimisationType optimisationType_;
	Algorithm alg_;
	public ModelAnalysisResult(Model semanticModel, OptimisationType optimisationType){
		semanticModel_ = semanticModel;
		optimisationType_ =optimisationType;
		alg_ = getAlgorithm(optimisationType);
		solutions_ = new ArrayList<Alternative>();
		evppi_ = new LinkedHashMap<String, Double>();
	}
	Algorithm getAlgorithm (OptimisationType optimisationType){
		Algorithm alg = null;
		if (optimisationType.equals(OptimisationType.EXACT)){
			alg = new ExhaustiveSearch (semanticModel_);
			alg.setSolveAll(true);
		}
		return alg;
	}
	public void anlyseByExhaustiveSearch(){
		// compute objective values
		List<SolutionValues> solutionValues =  alg_.solveAll();
		for (int i =0; i < solutionValues.size(); i ++){
			Alternative s = solutionValues.get(i).getSolution();
			solutions_.add(s);
		}
		
		// perform information analsysis
		InformationValueAnalysis infoValueAnalysis = new InformationValueAnalysis (semanticModel_.getSimulationNumber());
		
		// compute evpi
		evtpi_ = infoValueAnalysis.computeEVTPI(semanticModel_.getInfoValueObjective(), solutions_);
		
		// compute evppi for all parameters
		List<String> params = semanticModel_.getParameters();
		if (params != null){
			for (int i =0; i < params.size(); i ++){
				QualityVariable qvSim= semanticModel_.getQualityVariables().get(params.get(i));
				Map<String, double[]> paramSimData = new LinkedHashMap<String, double[]>();
				paramSimData.putAll(qvSim.getParameterSimData());
				for (Map.Entry<String, double[]> entry: paramSimData.entrySet()){
					double evppi = infoValueAnalysis.computeEVPPI(semanticModel_.getInfoValueObjective(), solutions_, entry.getValue());
					evppi_.put(params.get(i), evppi);
					System.out.println("evppi for "+ entry.getKey()+ " is "+ evppi );
				}
			}
		}
		
		
		// shortlist solution to string.
		
		
	}



	
 }
