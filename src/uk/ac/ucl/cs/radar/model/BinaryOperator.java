package uk.ac.ucl.cs.radar.model;

enum BinaryOperator {
	ADD ("+"),
	SUB ("-"),
	MUL ("*"),
	DIV ("/"),
	AND ("&&"),
	OR ("||"),
	LESS ("<"),
	GRET (">"),
	LEQ ("<="),
	GEQ(">=");
	
	private String binaryOperatorValue;
	BinaryOperator(String binaryOperatorValue) {
        this.binaryOperatorValue = binaryOperatorValue;
    }
    public String getBinaryOperatorValue() {
        return binaryOperatorValue;
    }
}
