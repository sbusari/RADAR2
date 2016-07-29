package radar.model;

import java.util.Map;

class Identifier extends Expression {
	private String id_;
	public void setID (String id){
		id_ = id;
	}
	public String  getID (){
		return id_;
	}
	@Override
	public double[] simulate(Alternative s) {
		Map<String, QualityVariable> qvList = s.getSemanticModel().getQualityVariables();
		QualityVariable qv = qvList.get(id_);
		double [] result = qv.simulate(s);
		return result;
	}
}
