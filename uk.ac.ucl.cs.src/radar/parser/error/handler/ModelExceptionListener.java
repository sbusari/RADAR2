package radar.parser.error.handler;
import org.antlr.v4.runtime.*;

import java.util.*;

import org.antlr.v4.runtime.BaseErrorListener;

public class ModelExceptionListener extends BaseErrorListener {
	
	String errorMessage = "";
	@Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e)
    {
		/*Goal_Model.tokens token = (Goal_Model.tokens)offendingSymbol;
		System.err.println(token.filename +
						   " line " + line + ":" + charPositionInLine + " at " +
						   token.getText() + ": " + msg);*/
		
        List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        charPositionInLine = charPositionInLine +1;
       // errorMessage += "Goal model rule stack: "+stack +"\n";
        //errorMessage += "line "+line+", character position "+charPositionInLine+" at "+ offendingSymbol+": "+msg;
        errorMessage += "Syntax error at line "+line+", character position "+charPositionInLine + ". "+msg + " \n";
        
        //System.err.println("Goal model rule stack: "+stack);
        /*System.err.println("line "+line+":"+charPositionInLine+" at "+
                           offendingSymbol+": "+msg);*/
        
        //System.err.println(errorMessage);
    }
	public String getErrorMsg (){
		return errorMessage;
	}
}
