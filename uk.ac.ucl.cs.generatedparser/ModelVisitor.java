// Generated from Model.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ModelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ModelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code qualityVariableDecision}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualityVariableDecision(@NotNull ModelParser.QualityVariableDecisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#decision_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecision_body(@NotNull ModelParser.Decision_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#parameter_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_def(@NotNull ModelParser.Parameter_defContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMult}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMult(@NotNull ModelParser.ExprMultContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#option_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_name(@NotNull ModelParser.Option_nameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNumber}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumber(@NotNull ModelParser.ExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#distribution}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistribution(@NotNull ModelParser.DistributionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprCompare}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCompare(@NotNull ModelParser.ExprCompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modelQualityVariableList}
	 * labeled alternative in {@link ModelParser#model_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModelQualityVariableList(@NotNull ModelParser.ModelQualityVariableListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#quality_var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuality_var_decl(@NotNull ModelParser.Quality_var_declContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPercent}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPercent(@NotNull ModelParser.ExprPercentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectiveBooleanProbability}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectiveBooleanProbability(@NotNull ModelParser.ObjectiveBooleanProbabilityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(@NotNull ModelParser.ExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPreOperator}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPreOperator(@NotNull ModelParser.ExprPreOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decisionXOR}
	 * labeled alternative in {@link ModelParser#decision_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecisionXOR(@NotNull ModelParser.DecisionXORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAdd}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAdd(@NotNull ModelParser.ExprAddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomicFloat}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicFloat(@NotNull ModelParser.AtomicFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code qualityVariableArithmetic}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualityVariableArithmetic(@NotNull ModelParser.QualityVariableArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#distribution_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistribution_args(@NotNull ModelParser.Distribution_argsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(@NotNull ModelParser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(@NotNull ModelParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#var_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_name(@NotNull ModelParser.Var_nameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNot}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNot(@NotNull ModelParser.ExprNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareVariables}
	 * labeled alternative in {@link ModelParser#comparision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareVariables(@NotNull ModelParser.CompareVariablesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprDiv}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprDiv(@NotNull ModelParser.ExprDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code qualityVariableParameter}
	 * labeled alternative in {@link ModelParser#quality_var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualityVariableParameter(@NotNull ModelParser.QualityVariableParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprIdentifier}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIdentifier(@NotNull ModelParser.ExprIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectiveProbability}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectiveProbability(@NotNull ModelParser.ObjectiveProbabilityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareExpression}
	 * labeled alternative in {@link ModelParser#comparision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpression(@NotNull ModelParser.CompareExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSub}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSub(@NotNull ModelParser.ExprSubContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#comparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparator(@NotNull ModelParser.ComparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#objective_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjective_decl(@NotNull ModelParser.Objective_declContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBracket}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBracket(@NotNull ModelParser.ExprBracketContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomicInteger}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicInteger(@NotNull ModelParser.AtomicIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modelObjectiveList}
	 * labeled alternative in {@link ModelParser#model_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModelObjectiveList(@NotNull ModelParser.ModelObjectiveListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomicDecimal}
	 * labeled alternative in {@link ModelParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicDecimal(@NotNull ModelParser.AtomicDecimalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectiveExpectation}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectiveExpectation(@NotNull ModelParser.ObjectiveExpectationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectivePercentile}
	 * labeled alternative in {@link ModelParser#objective_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectivePercentile(@NotNull ModelParser.ObjectivePercentileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#distribution_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistribution_arg(@NotNull ModelParser.Distribution_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(@NotNull ModelParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPower}
	 * labeled alternative in {@link ModelParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPower(@NotNull ModelParser.ExprPowerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ModelParser#optimisationDirection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptimisationDirection(@NotNull ModelParser.OptimisationDirectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code optionParameter}
	 * labeled alternative in {@link ModelParser#option_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionParameter(@NotNull ModelParser.OptionParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code optionExpression}
	 * labeled alternative in {@link ModelParser#option_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionExpression(@NotNull ModelParser.OptionExpressionContext ctx);
}