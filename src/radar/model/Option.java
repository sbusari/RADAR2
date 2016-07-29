package radar.model;

class Option {
	private String optionLabel_;
	private Expression definition;
	public Option(){}
	public String getOptionLabel(){
		return optionLabel_;
	}
	public void setOptionLabel (String optionLabel){
		optionLabel_ =optionLabel;
	}
	public Expression getDefinition (){
		return definition;
	}
	public void setDefinition (Expression  def){
		definition =def;
	}
}
