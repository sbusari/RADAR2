package radar.enumeration;

public enum ModelType {
	
	DETERMINISTIC (0),
	STOCHASTIC (1),
	UNCERTAINTY (2);
	private int modelTypeValue;
	ModelType(int modelTypeVal) {
        this.modelTypeValue = modelTypeVal;
    }
    public int getModelTypeValue() {
        return modelTypeValue;
    }
}
