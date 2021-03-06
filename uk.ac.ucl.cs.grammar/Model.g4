grammar Model;

model 						:'Model' var_name  ';' NEWLINE* (model_element)*
							;

model_element				: objective_decl+ 								#modelObjectiveList
							| quality_var_decl+								#modelQualityVariableList
							;
																
objective_decl				:'Objective' optimisationDirection  var_name ('=' objective_def)? ('with' number 'margin')? ';' NEWLINE* ;			

objective_def				:'EV'SINGLESPACE* '(' var_name ')' 													#objectiveExpectation
							|'Pr'SINGLESPACE* '(' var_name ')' 													#objectiveBooleanProbability
							|'Pr'SINGLESPACE* '(' comparision ')' 												#objectiveProbability
							|'percentile' SINGLESPACE* '(' (op = ('-'| '+'))? var_name ',' integerLiteral ')'				#objectivePercentile
							; 
													
quality_var_decl			:var_name  '='  quality_var_def  NEWLINE* ;

quality_var_def				: decision_def 															#qualityVariableDecision
							| arithmetic_expr ';'													#qualityVariableArithmetic
							| parameter_def ';'														#qualityVariableParameter
							;


decision_def				:'decision'  decision_body 				#decisionXOR
							;
									
decision_body				: '(' decision_Name= StringLiteral ')' '{' (option_name ':' option_def ';' NEWLINE?)+'}'
							;
							

option_name					: (NEWLINE|WS|SINGLESPACE)* StringLiteral (NEWLINE|WS|SINGLESPACE)*
							;

option_def					:(NEWLINE|WS|SINGLESPACE)*  arithmetic_expr  (NEWLINE|WS|SINGLESPACE)* 	#optionExpression
 							|(NEWLINE|WS|SINGLESPACE)* parameter_def  (NEWLINE|WS|SINGLESPACE)*							#optionParameter
 							;

							
parameter_def				: distribution  '(' (distribution_arg ( ',' distribution_arg ) *)? ')'																																			
							;
		
											
distribution				: distributionValue=( 'deterministic'
							|'normal'
							|'normalCI'
							|'geometric'
							|'exponential'
							|'random'
							|'uniform'
							|'triangular')
							;
							
distribution_args 			: distribution_arg ( ',' distribution_arg ) *  ;

// used expessio to cater for e.g. normal(10^6*5, 50)
distribution_arg 			: arithmetic_expr
							;	
																							
arithmetic_expr				: number 																															#exprNumber
							|<assoc=right> arithmetic_expr '^' arithmetic_expr																					#exprPower
							| '(' arithmetic_expr ')'																											#exprBracket						
							| arithmetic_expr (NEWLINE|WS|SINGLESPACE)* '/' (NEWLINE|WS|SINGLESPACE)* arithmetic_expr 																	#exprDiv
							| arithmetic_expr (NEWLINE|WS|SINGLESPACE)* '*' (NEWLINE|WS|SINGLESPACE)* arithmetic_expr 																	#exprMult
							| arithmetic_expr (NEWLINE|WS|SINGLESPACE)* '+' (NEWLINE|WS|SINGLESPACE)* arithmetic_expr 																	#exprAdd
							| arithmetic_expr (NEWLINE|WS|SINGLESPACE)* '-' (NEWLINE|WS|SINGLESPACE)* arithmetic_expr 																	#exprSub
							| (NEWLINE|WS|SINGLESPACE)* op=('-'|'+') (NEWLINE|WS|SINGLESPACE)* arithmetic_expr			 																#exprPreOperator	
							| arithmetic_expr (NEWLINE|WS|SINGLESPACE)* AND (NEWLINE|WS|SINGLESPACE)* arithmetic_expr 																	#exprAnd
							| arithmetic_expr (NEWLINE|WS|SINGLESPACE)* OR (NEWLINE|WS|SINGLESPACE)* arithmetic_expr 																	#exprOr
							| (NEWLINE|WS|SINGLESPACE)* NOT (NEWLINE|WS|SINGLESPACE)* arithmetic_expr																					#exprNot						
							| arithmetic_expr (NEWLINE|WS|SINGLESPACE)* '%'																									#exprPercent
							| comparision																														#exprCompare	
							| var_name																															#exprIdentifier																							
							;
	
	
comparision					:var_name relationalOp=comparator var_name 											#compareVariables
							|var_name relationalOp=comparator arithmetic_expr									#compareExpression
							;																					

							
number						: integerLiteral												    #atomicInteger
    						| FloatingPointLiteral												#atomicFloat
							| DecimalLiteral													#atomicDecimal
    						;
   						
integerLiteral				:intValue=(HexLiteral
    						| OctalLiteral
    						| DecimalLiteral)
    						;

optimisationDirection		: max='Max'
							| min='Min'
							;


comparator 					: logicalOperatorValue= ('>'|'>='|'<'|'<='|'=='| '!=');

var_name					: Identifier
							;
							
NOT 						: 'not' | '!'
							;
AND 						: 'and' | '&&'
							;
OR 							: 'or' | '||'
							;

HexLiteral : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OctalLiteral : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix : ('l'|'L') ;

FloatingPointLiteral
    :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    |   ('0'..'9')+ Exponent FloatTypeSuffix?
    |   ('0'..'9')+ FloatTypeSuffix
    |   ('0x' | '0X') (HexDigit )*
        ('.' (HexDigit)*)?
        ( 'p' | 'P' )
        ( '+' | '-' )?
        ( '0' .. '9' )+
        FloatTypeSuffix?
    ;

fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

CharacterLiteral
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;

StringLiteral
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;
    
fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UnicodeEscape
    |   OctalEscape
    ;
    
fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

Identifier
    :   Letter (Letter|JavaIDDigit)*
    ; 

fragment
Letter
    :  '\u0024' |
       '\u0041'..'\u005a' |
       '\u005f' |
       '\u0061'..'\u007a' |
       '\u00c0'..'\u00d6' |
       '\u00d8'..'\u00f6' |
       '\u00f8'..'\u00ff' |
       '\u0100'..'\u1fff' |
       '\u3040'..'\u318f' |
       '\u3300'..'\u337f' |
       '\u3400'..'\u3d2d' |
       '\u4e00'..'\u9fff' |
       '\uf900'..'\ufaff'
    ;

fragment
JavaIDDigit
    :  '\u0030'..'\u0039' |
       '\u0660'..'\u0669' |
       '\u06f0'..'\u06f9' |
       '\u0966'..'\u096f' |
       '\u09e6'..'\u09ef' |
       '\u0a66'..'\u0a6f' |
       '\u0ae6'..'\u0aef' |
       '\u0b66'..'\u0b6f' |
       '\u0be7'..'\u0bef' |
       '\u0c66'..'\u0c6f' |
       '\u0ce6'..'\u0cef' |
       '\u0d66'..'\u0d6f' |
       '\u0e50'..'\u0e59' |
       '\u0ed0'..'\u0ed9' |
       '\u1040'..'\u1049'
   ;

COMMENT
    :   '/*' .*? '*/'    -> channel(HIDDEN) // match anything between /* and */
    ;

LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN)
    ;

// Match both UNIX and Windows newlines
NEWLINE      		:  '\r'? '\n'  -> channel(HIDDEN);

SINGLESPACE      	:  ' '+  -> channel(HIDDEN);

WS      :   [ \t]+ -> channel(HIDDEN) ;

