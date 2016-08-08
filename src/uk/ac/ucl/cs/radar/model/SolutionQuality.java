package radar.model;

public class SolutionQuality {

	private double hypervolume_;
	private double igd_;
	private double gd_;
	private double epsilon_;
	private double spread_;
	private double coverage_;
	public SolutionQuality(){
		
	}
	public void setHypervolume (double hypervolume){
		hypervolume_ = hypervolume;
	}
	public double getHypervolume (){
		return hypervolume_;
	}
	public void setIGD (double igd){
		igd_ = igd;
	}
	public double getIGD (){
		return igd_;
	}
	public void setGD (double gd){
		gd_ = gd;
	}
	public double getGD (){
		return gd_;
	}
	public void setSpread (double spread){
		spread_ = spread;
	}
	public double getSpread (){
		return spread_;
	}
	public void setEpsilon (double epsilon){
		epsilon_ = epsilon;
	}
	public double getEpsilon (){
		return epsilon_;
	}
	public void setCoverage (double coverage){
		coverage_ = coverage;
	}
	public double getCoverage (){
		return coverage_;
	}
	
}
