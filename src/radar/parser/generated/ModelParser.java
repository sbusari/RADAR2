// Generated from Model.g4 by ANTLR 4.5.1
package radar.parser.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ModelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, NOT=37, AND=38, OR=39, 
		HexLiteral=40, DecimalLiteral=41, OctalLiteral=42, FloatingPointLiteral=43, 
		CharacterLiteral=44, StringLiteral=45, Identifier=46, COMMENT=47, LINE_COMMENT=48, 
		NEWLINE=49, SINGLESPACE=50, WS=51;
	public static final int
		RULE_model = 0, RULE_model_element = 1, RULE_objective_decl = 2, RULE_objective_def = 3, 
		RULE_quality_var_decl = 4, RULE_quality_var_def = 5, RULE_decision_def = 6, 
		RULE_decision_body = 7, RULE_option_name = 8, RULE_option_def = 9, RULE_parameter_def = 10, 
		RULE_distribution = 11, RULE_distribution_args = 12, RULE_distribution_arg = 13, 
		RULE_arithmetic_expr = 14, RULE_comparision = 15, RULE_atomicExpression = 16, 
		RULE_integerLiteral = 17, RULE_optimisationDirection = 18, RULE_comparator = 19, 
		RULE_var_name = 20;
	public static final String[] ruleNames = {
		"model", "model_element", "objective_decl", "objective_def", "quality_var_decl", 
		"quality_var_def", "decision_def", "decision_body", "option_name", "option_def", 
		"parameter_def", "distribution", "distribution_args", "distribution_arg", 
		"arithmetic_expr", "comparision", "atomicExpression", "integerLiteral", 
		"optimisationDirection", "comparator", "var_name"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Model'", "';'", "'Objective'", "'='", "'E'", "'('", "')'", "'P'", 
		"'percentile'", "','", "'decision'", "'{'", "':'", "'}'", "'deterministic'", 
		"'normal'", "'normal_ci'", "'geometric'", "'exponential'", "'random'", 
		"'uniform'", "'triangular'", "'^'", "'/'", "'*'", "'+'", "'-'", "'%'", 
		"'Max'", "'Min'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "NOT", "AND", "OR", "HexLiteral", "DecimalLiteral", "OctalLiteral", 
		"FloatingPointLiteral", "CharacterLiteral", "StringLiteral", "Identifier", 
		"COMMENT", "LINE_COMMENT", "NEWLINE", "SINGLESPACE", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Model.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ModelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ModelContext extends ParserRuleContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<Model_elementContext> model_element() {
			return getRuleContexts(Model_elementContext.class);
		}
		public Model_elementContext model_element(int i) {
			return getRuleContext(Model_elementContext.class,i);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__0);
			setState(43);
			var_name();
			setState(44);
			match(T__1);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(45);
				match(NEWLINE);
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==Identifier) {
				{
				{
				setState(51);
				model_element();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Model_elementContext extends ParserRuleContext {
		public Model_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model_element; }
	 
		public Model_elementContext() { }
		public void copyFrom(Model_elementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ModelObjectiveListContext extends Model_elementContext {
		public List<Objective_declContext> objective_decl() {
			return getRuleContexts(Objective_declContext.class);
		}
		public Objective_declContext objective_decl(int i) {
			return getRuleContext(Objective_declContext.class,i);
		}
		public ModelObjectiveListContext(Model_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterModelObjectiveList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitModelObjectiveList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitModelObjectiveList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModelQualityVariableListContext extends Model_elementContext {
		public List<Quality_var_declContext> quality_var_decl() {
			return getRuleContexts(Quality_var_declContext.class);
		}
		public Quality_var_declContext quality_var_decl(int i) {
			return getRuleContext(Quality_var_declContext.class,i);
		}
		public ModelQualityVariableListContext(Model_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterModelQualityVariableList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitModelQualityVariableList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitModelQualityVariableList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Model_elementContext model_element() throws RecognitionException {
		Model_elementContext _localctx = new Model_elementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_model_element);
		try {
			int _alt;
			setState(67);
			switch (_input.LA(1)) {
			case T__2:
				_localctx = new ModelObjectiveListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(58); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(57);
						objective_decl();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(60); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case Identifier:
				_localctx = new ModelQualityVariableListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(63); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(62);
						quality_var_decl();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(65); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Objective_declContext extends ParserRuleContext {
		public OptimisationDirectionContext optimisationDirection() {
			return getRuleContext(OptimisationDirectionContext.class,0);
		}
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public Objective_defContext objective_def() {
			return getRuleContext(Objective_defContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public Objective_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objective_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjective_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjective_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjective_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Objective_declContext objective_decl() throws RecognitionException {
		Objective_declContext _localctx = new Objective_declContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_objective_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__2);
			setState(70);
			optimisationDirection();
			setState(71);
			var_name();
			setState(74);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(72);
				match(T__3);
				setState(73);
				objective_def();
				}
			}

			setState(76);
			match(T__1);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(77);
				match(NEWLINE);
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Objective_defContext extends ParserRuleContext {
		public Objective_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objective_def; }
	 
		public Objective_defContext() { }
		public void copyFrom(Objective_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ObjectiveBooleanProbabilityContext extends Objective_defContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ObjectiveBooleanProbabilityContext(Objective_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjectiveBooleanProbability(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjectiveBooleanProbability(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjectiveBooleanProbability(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectiveExpectationContext extends Objective_defContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ObjectiveExpectationContext(Objective_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjectiveExpectation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjectiveExpectation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjectiveExpectation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectivePercentileContext extends Objective_defContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ObjectivePercentileContext(Objective_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjectivePercentile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjectivePercentile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjectivePercentile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectiveProbabilityContext extends Objective_defContext {
		public ComparisionContext comparision() {
			return getRuleContext(ComparisionContext.class,0);
		}
		public List<TerminalNode> SINGLESPACE() { return getTokens(ModelParser.SINGLESPACE); }
		public TerminalNode SINGLESPACE(int i) {
			return getToken(ModelParser.SINGLESPACE, i);
		}
		public ObjectiveProbabilityContext(Objective_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterObjectiveProbability(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitObjectiveProbability(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitObjectiveProbability(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Objective_defContext objective_def() throws RecognitionException {
		Objective_defContext _localctx = new Objective_defContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_objective_def);
		int _la;
		try {
			setState(129);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new ObjectiveExpectationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				match(T__4);
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(84);
					match(SINGLESPACE);
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(90);
				match(T__5);
				setState(91);
				var_name();
				setState(92);
				match(T__6);
				}
				break;
			case 2:
				_localctx = new ObjectiveBooleanProbabilityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(T__7);
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(95);
					match(SINGLESPACE);
					}
					}
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(101);
				match(T__5);
				setState(102);
				var_name();
				setState(103);
				match(T__6);
				}
				break;
			case 3:
				_localctx = new ObjectiveProbabilityContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				match(T__7);
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(106);
					match(SINGLESPACE);
					}
					}
					setState(111);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(112);
				match(T__5);
				setState(113);
				comparision();
				setState(114);
				match(T__6);
				}
				break;
			case 4:
				_localctx = new ObjectivePercentileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
				match(T__8);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(117);
					match(SINGLESPACE);
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(123);
				match(T__5);
				setState(124);
				integerLiteral();
				setState(125);
				match(T__9);
				setState(126);
				var_name();
				setState(127);
				match(T__6);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quality_var_declContext extends ParserRuleContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public Quality_var_defContext quality_var_def() {
			return getRuleContext(Quality_var_defContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public Quality_var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quality_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterQuality_var_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitQuality_var_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitQuality_var_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quality_var_declContext quality_var_decl() throws RecognitionException {
		Quality_var_declContext _localctx = new Quality_var_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quality_var_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			var_name();
			setState(132);
			match(T__3);
			setState(133);
			quality_var_def();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(134);
				match(NEWLINE);
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quality_var_defContext extends ParserRuleContext {
		public Quality_var_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quality_var_def; }
	 
		public Quality_var_defContext() { }
		public void copyFrom(Quality_var_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QualityVariableDecisionContext extends Quality_var_defContext {
		public Decision_defContext decision_def() {
			return getRuleContext(Decision_defContext.class,0);
		}
		public QualityVariableDecisionContext(Quality_var_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterQualityVariableDecision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitQualityVariableDecision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitQualityVariableDecision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class QualityVariableArithmeticContext extends Quality_var_defContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public QualityVariableArithmeticContext(Quality_var_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterQualityVariableArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitQualityVariableArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitQualityVariableArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class QualityVariableParameterContext extends Quality_var_defContext {
		public Parameter_defContext parameter_def() {
			return getRuleContext(Parameter_defContext.class,0);
		}
		public QualityVariableParameterContext(Quality_var_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterQualityVariableParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitQualityVariableParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitQualityVariableParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quality_var_defContext quality_var_def() throws RecognitionException {
		Quality_var_defContext _localctx = new Quality_var_defContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_quality_var_def);
		try {
			setState(147);
			switch (_input.LA(1)) {
			case T__10:
				_localctx = new QualityVariableDecisionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				decision_def();
				}
				break;
			case T__5:
			case T__25:
			case T__26:
			case NOT:
			case HexLiteral:
			case DecimalLiteral:
			case OctalLiteral:
			case FloatingPointLiteral:
			case Identifier:
			case NEWLINE:
			case WS:
				_localctx = new QualityVariableArithmeticContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				arithmetic_expr(0);
				setState(142);
				match(T__1);
				}
				break;
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
				_localctx = new QualityVariableParameterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(144);
				parameter_def();
				setState(145);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decision_defContext extends ParserRuleContext {
		public Decision_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decision_def; }
	 
		public Decision_defContext() { }
		public void copyFrom(Decision_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DecisionXORContext extends Decision_defContext {
		public Decision_bodyContext decision_body() {
			return getRuleContext(Decision_bodyContext.class,0);
		}
		public DecisionXORContext(Decision_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDecisionXOR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDecisionXOR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDecisionXOR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decision_defContext decision_def() throws RecognitionException {
		Decision_defContext _localctx = new Decision_defContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_decision_def);
		try {
			_localctx = new DecisionXORContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(T__10);
			setState(150);
			decision_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decision_bodyContext extends ParserRuleContext {
		public Token decision_Name;
		public TerminalNode StringLiteral() { return getToken(ModelParser.StringLiteral, 0); }
		public List<Option_nameContext> option_name() {
			return getRuleContexts(Option_nameContext.class);
		}
		public Option_nameContext option_name(int i) {
			return getRuleContext(Option_nameContext.class,i);
		}
		public List<Option_defContext> option_def() {
			return getRuleContexts(Option_defContext.class);
		}
		public Option_defContext option_def(int i) {
			return getRuleContext(Option_defContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public Decision_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decision_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDecision_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDecision_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDecision_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decision_bodyContext decision_body() throws RecognitionException {
		Decision_bodyContext _localctx = new Decision_bodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_decision_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__5);
			setState(153);
			((Decision_bodyContext)_localctx).decision_Name = match(StringLiteral);
			setState(154);
			match(T__6);
			setState(155);
			match(T__11);
			setState(163); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(156);
				option_name();
				setState(157);
				match(T__12);
				setState(158);
				option_def();
				setState(159);
				match(T__1);
				setState(161);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(160);
					match(NEWLINE);
					}
				}

				}
				}
				setState(165); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==StringLiteral );
			setState(167);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Option_nameContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(ModelParser.StringLiteral, 0); }
		public Option_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterOption_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitOption_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitOption_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_nameContext option_name() throws RecognitionException {
		Option_nameContext _localctx = new Option_nameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_option_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Option_defContext extends ParserRuleContext {
		public Option_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_def; }
	 
		public Option_defContext() { }
		public void copyFrom(Option_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OptionParameterContext extends Option_defContext {
		public Parameter_defContext parameter_def() {
			return getRuleContext(Parameter_defContext.class,0);
		}
		public OptionParameterContext(Option_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterOptionParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitOptionParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitOptionParameter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OptionExpressionContext extends Option_defContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public OptionExpressionContext(Option_defContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterOptionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitOptionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitOptionExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_defContext option_def() throws RecognitionException {
		Option_defContext _localctx = new Option_defContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_option_def);
		try {
			setState(173);
			switch (_input.LA(1)) {
			case T__5:
			case T__25:
			case T__26:
			case NOT:
			case HexLiteral:
			case DecimalLiteral:
			case OctalLiteral:
			case FloatingPointLiteral:
			case Identifier:
			case NEWLINE:
			case WS:
				_localctx = new OptionExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				arithmetic_expr(0);
				}
				break;
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
				_localctx = new OptionParameterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(172);
				parameter_def();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_defContext extends ParserRuleContext {
		public DistributionContext distribution() {
			return getRuleContext(DistributionContext.class,0);
		}
		public List<Distribution_argContext> distribution_arg() {
			return getRuleContexts(Distribution_argContext.class);
		}
		public Distribution_argContext distribution_arg(int i) {
			return getRuleContext(Distribution_argContext.class,i);
		}
		public Parameter_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterParameter_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitParameter_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitParameter_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_defContext parameter_def() throws RecognitionException {
		Parameter_defContext _localctx = new Parameter_defContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parameter_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			distribution();
			setState(176);
			match(T__5);
			setState(185);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__25) | (1L << T__26) | (1L << NOT) | (1L << HexLiteral) | (1L << DecimalLiteral) | (1L << OctalLiteral) | (1L << FloatingPointLiteral) | (1L << Identifier) | (1L << NEWLINE) | (1L << WS))) != 0)) {
				{
				setState(177);
				distribution_arg();
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(178);
					match(T__9);
					setState(179);
					distribution_arg();
					}
					}
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(187);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DistributionContext extends ParserRuleContext {
		public Token distributionValue;
		public DistributionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distribution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDistribution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDistribution(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDistribution(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistributionContext distribution() throws RecognitionException {
		DistributionContext _localctx = new DistributionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_distribution);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			((DistributionContext)_localctx).distributionValue = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21))) != 0)) ) {
				((DistributionContext)_localctx).distributionValue = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Distribution_argsContext extends ParserRuleContext {
		public List<Distribution_argContext> distribution_arg() {
			return getRuleContexts(Distribution_argContext.class);
		}
		public Distribution_argContext distribution_arg(int i) {
			return getRuleContext(Distribution_argContext.class,i);
		}
		public Distribution_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distribution_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDistribution_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDistribution_args(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDistribution_args(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Distribution_argsContext distribution_args() throws RecognitionException {
		Distribution_argsContext _localctx = new Distribution_argsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_distribution_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			distribution_arg();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(192);
				match(T__9);
				setState(193);
				distribution_arg();
				}
				}
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Distribution_argContext extends ParserRuleContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public Distribution_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distribution_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterDistribution_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitDistribution_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitDistribution_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Distribution_argContext distribution_arg() throws RecognitionException {
		Distribution_argContext _localctx = new Distribution_argContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_distribution_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			arithmetic_expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arithmetic_exprContext extends ParserRuleContext {
		public Arithmetic_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_expr; }
	 
		public Arithmetic_exprContext() { }
		public void copyFrom(Arithmetic_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExprNotContext extends Arithmetic_exprContext {
		public TerminalNode NOT() { return getToken(ModelParser.NOT, 0); }
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprNotContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprDivContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprDivContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprIdentifierContext extends Arithmetic_exprContext {
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public ExprIdentifierContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprMultContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprMultContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprMult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprMult(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprMult(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprAtomicExpressionContext extends Arithmetic_exprContext {
		public AtomicExpressionContext atomicExpression() {
			return getRuleContext(AtomicExpressionContext.class,0);
		}
		public ExprAtomicExpressionContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprAtomicExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprAtomicExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprAtomicExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprCompareContext extends Arithmetic_exprContext {
		public ComparisionContext comparision() {
			return getRuleContext(ComparisionContext.class,0);
		}
		public ExprCompareContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprCompare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprCompare(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPercentContext extends Arithmetic_exprContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprPercentContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprPercent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprPercent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprPercent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprSubContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprSubContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprOrContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public TerminalNode OR() { return getToken(ModelParser.OR, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprOrContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPreOperatorContext extends Arithmetic_exprContext {
		public Token op;
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprPreOperatorContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprPreOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprPreOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprPreOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprBracketContext extends Arithmetic_exprContext {
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public ExprBracketContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprBracket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprBracket(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprAddContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprAddContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprAdd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprAndContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public TerminalNode AND() { return getToken(ModelParser.AND, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ModelParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(ModelParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ModelParser.WS, i);
		}
		public ExprAndContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPowerContext extends Arithmetic_exprContext {
		public List<Arithmetic_exprContext> arithmetic_expr() {
			return getRuleContexts(Arithmetic_exprContext.class);
		}
		public Arithmetic_exprContext arithmetic_expr(int i) {
			return getRuleContext(Arithmetic_exprContext.class,i);
		}
		public ExprPowerContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprPower(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprPower(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_exprContext arithmetic_expr() throws RecognitionException {
		return arithmetic_expr(0);
	}

	private Arithmetic_exprContext arithmetic_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Arithmetic_exprContext _localctx = new Arithmetic_exprContext(_ctx, _parentState);
		Arithmetic_exprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_arithmetic_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				_localctx = new ExprPreOperatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE || _la==WS) {
					{
					{
					setState(202);
					_la = _input.LA(1);
					if ( !(_la==NEWLINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(207);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(208);
				((ExprPreOperatorContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
					((ExprPreOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(209);
						_la = _input.LA(1);
						if ( !(_la==NEWLINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(214);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				setState(215);
				arithmetic_expr(7);
				}
				break;
			case 2:
				{
				_localctx = new ExprNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE || _la==WS) {
					{
					{
					setState(216);
					_la = _input.LA(1);
					if ( !(_la==NEWLINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(221);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(222);
				match(NOT);
				setState(226);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(223);
						_la = _input.LA(1);
						if ( !(_la==NEWLINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(228);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
				setState(229);
				arithmetic_expr(4);
				}
				break;
			case 3:
				{
				_localctx = new ExprAtomicExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(230);
				atomicExpression();
				}
				break;
			case 4:
				{
				_localctx = new ExprBracketContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(231);
				match(T__5);
				setState(232);
				arithmetic_expr(0);
				setState(233);
				match(T__6);
				}
				break;
			case 5:
				{
				_localctx = new ExprCompareContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(235);
				comparision();
				}
				break;
			case 6:
				{
				_localctx = new ExprIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(236);
				var_name();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(342);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(340);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						_localctx = new ExprPowerContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(239);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(240);
						match(T__22);
						setState(241);
						arithmetic_expr(13);
						}
						break;
					case 2:
						{
						_localctx = new ExprDivContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(242);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(246);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(243);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(248);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(249);
						match(T__23);
						setState(253);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(250);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(255);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
						}
						setState(256);
						arithmetic_expr(12);
						}
						break;
					case 3:
						{
						_localctx = new ExprMultContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(257);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(261);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(258);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(263);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(264);
						match(T__24);
						setState(268);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(265);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(270);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
						}
						setState(271);
						arithmetic_expr(11);
						}
						break;
					case 4:
						{
						_localctx = new ExprAddContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(272);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(276);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(273);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(278);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(279);
						match(T__25);
						setState(283);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(280);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(285);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
						}
						setState(286);
						arithmetic_expr(10);
						}
						break;
					case 5:
						{
						_localctx = new ExprSubContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(287);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(291);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(288);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(293);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(294);
						match(T__26);
						setState(298);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(295);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(300);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
						}
						setState(301);
						arithmetic_expr(9);
						}
						break;
					case 6:
						{
						_localctx = new ExprAndContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(302);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(306);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(303);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(308);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(309);
						match(AND);
						setState(313);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(310);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(315);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
						}
						setState(316);
						arithmetic_expr(7);
						}
						break;
					case 7:
						{
						_localctx = new ExprOrContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(317);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(321);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(318);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(323);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(324);
						match(OR);
						setState(328);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(325);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(330);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
						}
						setState(331);
						arithmetic_expr(6);
						}
						break;
					case 8:
						{
						_localctx = new ExprPercentContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(332);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(336);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(333);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(338);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(339);
						match(T__27);
						}
						break;
					}
					} 
				}
				setState(344);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ComparisionContext extends ParserRuleContext {
		public ComparisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparision; }
	 
		public ComparisionContext() { }
		public void copyFrom(ComparisionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CompareExpressionContext extends ComparisionContext {
		public ComparatorContext relationalOp;
		public Var_nameContext var_name() {
			return getRuleContext(Var_nameContext.class,0);
		}
		public Arithmetic_exprContext arithmetic_expr() {
			return getRuleContext(Arithmetic_exprContext.class,0);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public CompareExpressionContext(ComparisionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterCompareExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitCompareExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitCompareExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareVariablesContext extends ComparisionContext {
		public ComparatorContext relationalOp;
		public List<Var_nameContext> var_name() {
			return getRuleContexts(Var_nameContext.class);
		}
		public Var_nameContext var_name(int i) {
			return getRuleContext(Var_nameContext.class,i);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public CompareVariablesContext(ComparisionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterCompareVariables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitCompareVariables(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitCompareVariables(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisionContext comparision() throws RecognitionException {
		ComparisionContext _localctx = new ComparisionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_comparision);
		try {
			setState(353);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				_localctx = new CompareVariablesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(345);
				var_name();
				setState(346);
				((CompareVariablesContext)_localctx).relationalOp = comparator();
				setState(347);
				var_name();
				}
				break;
			case 2:
				_localctx = new CompareExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(349);
				var_name();
				setState(350);
				((CompareExpressionContext)_localctx).relationalOp = comparator();
				setState(351);
				arithmetic_expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomicExpressionContext extends ParserRuleContext {
		public AtomicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicExpression; }
	 
		public AtomicExpressionContext() { }
		public void copyFrom(AtomicExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AtomicIntegerContext extends AtomicExpressionContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public AtomicIntegerContext(AtomicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterAtomicInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitAtomicInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitAtomicInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomicFloatContext extends AtomicExpressionContext {
		public TerminalNode FloatingPointLiteral() { return getToken(ModelParser.FloatingPointLiteral, 0); }
		public AtomicFloatContext(AtomicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterAtomicFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitAtomicFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitAtomicFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomicDecimalContext extends AtomicExpressionContext {
		public TerminalNode DecimalLiteral() { return getToken(ModelParser.DecimalLiteral, 0); }
		public AtomicDecimalContext(AtomicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterAtomicDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitAtomicDecimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitAtomicDecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomicExpressionContext atomicExpression() throws RecognitionException {
		AtomicExpressionContext _localctx = new AtomicExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_atomicExpression);
		try {
			setState(358);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				_localctx = new AtomicIntegerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(355);
				integerLiteral();
				}
				break;
			case 2:
				_localctx = new AtomicFloatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
				match(FloatingPointLiteral);
				}
				break;
			case 3:
				_localctx = new AtomicDecimalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(357);
				match(DecimalLiteral);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public Token intValue;
		public TerminalNode HexLiteral() { return getToken(ModelParser.HexLiteral, 0); }
		public TerminalNode OctalLiteral() { return getToken(ModelParser.OctalLiteral, 0); }
		public TerminalNode DecimalLiteral() { return getToken(ModelParser.DecimalLiteral, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_integerLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			((IntegerLiteralContext)_localctx).intValue = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HexLiteral) | (1L << DecimalLiteral) | (1L << OctalLiteral))) != 0)) ) {
				((IntegerLiteralContext)_localctx).intValue = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptimisationDirectionContext extends ParserRuleContext {
		public Token max;
		public Token min;
		public OptimisationDirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optimisationDirection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterOptimisationDirection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitOptimisationDirection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitOptimisationDirection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptimisationDirectionContext optimisationDirection() throws RecognitionException {
		OptimisationDirectionContext _localctx = new OptimisationDirectionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_optimisationDirection);
		try {
			setState(364);
			switch (_input.LA(1)) {
			case T__28:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				((OptimisationDirectionContext)_localctx).max = match(T__28);
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(363);
				((OptimisationDirectionContext)_localctx).min = match(T__29);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparatorContext extends ParserRuleContext {
		public Token logicalOperatorValue;
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			((ComparatorContext)_localctx).logicalOperatorValue = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35))) != 0)) ) {
				((ComparatorContext)_localctx).logicalOperatorValue = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_nameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ModelParser.Identifier, 0); }
		public Var_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterVar_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitVar_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitVar_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_nameContext var_name() throws RecognitionException {
		Var_nameContext _localctx = new Var_nameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_var_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return arithmetic_expr_sempred((Arithmetic_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmetic_expr_sempred(Arithmetic_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\65\u0175\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\7\2\61\n\2\f"+
		"\2\16\2\64\13\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\3\6\3=\n\3\r\3\16\3>\3"+
		"\3\6\3B\n\3\r\3\16\3C\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\5\4M\n\4\3\4\3\4\7"+
		"\4Q\n\4\f\4\16\4T\13\4\3\5\3\5\7\5X\n\5\f\5\16\5[\13\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\7\5c\n\5\f\5\16\5f\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5n\n\5\f\5"+
		"\16\5q\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5y\n\5\f\5\16\5|\13\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5\u0084\n\5\3\6\3\6\3\6\3\6\7\6\u008a\n\6\f\6\16\6\u008d"+
		"\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0096\n\7\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a4\n\t\6\t\u00a6\n\t\r\t\16\t\u00a7"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\5\13\u00b0\n\13\3\f\3\f\3\f\3\f\3\f\7\f\u00b7"+
		"\n\f\f\f\16\f\u00ba\13\f\5\f\u00bc\n\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16"+
		"\7\16\u00c5\n\16\f\16\16\16\u00c8\13\16\3\17\3\17\3\20\3\20\7\20\u00ce"+
		"\n\20\f\20\16\20\u00d1\13\20\3\20\3\20\7\20\u00d5\n\20\f\20\16\20\u00d8"+
		"\13\20\3\20\3\20\7\20\u00dc\n\20\f\20\16\20\u00df\13\20\3\20\3\20\7\20"+
		"\u00e3\n\20\f\20\16\20\u00e6\13\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00f0\n\20\3\20\3\20\3\20\3\20\3\20\7\20\u00f7\n\20\f\20\16"+
		"\20\u00fa\13\20\3\20\3\20\7\20\u00fe\n\20\f\20\16\20\u0101\13\20\3\20"+
		"\3\20\3\20\7\20\u0106\n\20\f\20\16\20\u0109\13\20\3\20\3\20\7\20\u010d"+
		"\n\20\f\20\16\20\u0110\13\20\3\20\3\20\3\20\7\20\u0115\n\20\f\20\16\20"+
		"\u0118\13\20\3\20\3\20\7\20\u011c\n\20\f\20\16\20\u011f\13\20\3\20\3\20"+
		"\3\20\7\20\u0124\n\20\f\20\16\20\u0127\13\20\3\20\3\20\7\20\u012b\n\20"+
		"\f\20\16\20\u012e\13\20\3\20\3\20\3\20\7\20\u0133\n\20\f\20\16\20\u0136"+
		"\13\20\3\20\3\20\7\20\u013a\n\20\f\20\16\20\u013d\13\20\3\20\3\20\3\20"+
		"\7\20\u0142\n\20\f\20\16\20\u0145\13\20\3\20\3\20\7\20\u0149\n\20\f\20"+
		"\16\20\u014c\13\20\3\20\3\20\3\20\7\20\u0151\n\20\f\20\16\20\u0154\13"+
		"\20\3\20\7\20\u0157\n\20\f\20\16\20\u015a\13\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\5\21\u0164\n\21\3\22\3\22\3\22\5\22\u0169\n\22\3\23"+
		"\3\23\3\24\3\24\5\24\u016f\n\24\3\25\3\25\3\26\3\26\3\26\2\3\36\27\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\7\3\2\21\30\4\2\63\63\65"+
		"\65\3\2\34\35\3\2*,\3\2!&\u0198\2,\3\2\2\2\4E\3\2\2\2\6G\3\2\2\2\b\u0083"+
		"\3\2\2\2\n\u0085\3\2\2\2\f\u0095\3\2\2\2\16\u0097\3\2\2\2\20\u009a\3\2"+
		"\2\2\22\u00ab\3\2\2\2\24\u00af\3\2\2\2\26\u00b1\3\2\2\2\30\u00bf\3\2\2"+
		"\2\32\u00c1\3\2\2\2\34\u00c9\3\2\2\2\36\u00ef\3\2\2\2 \u0163\3\2\2\2\""+
		"\u0168\3\2\2\2$\u016a\3\2\2\2&\u016e\3\2\2\2(\u0170\3\2\2\2*\u0172\3\2"+
		"\2\2,-\7\3\2\2-.\5*\26\2.\62\7\4\2\2/\61\7\63\2\2\60/\3\2\2\2\61\64\3"+
		"\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\638\3\2\2\2\64\62\3\2\2\2\65\67\5\4"+
		"\3\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\3\3\2\2\2:8\3\2"+
		"\2\2;=\5\6\4\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?F\3\2\2\2@B\5\n"+
		"\6\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2E<\3\2\2\2EA\3\2"+
		"\2\2F\5\3\2\2\2GH\7\5\2\2HI\5&\24\2IL\5*\26\2JK\7\6\2\2KM\5\b\5\2LJ\3"+
		"\2\2\2LM\3\2\2\2MN\3\2\2\2NR\7\4\2\2OQ\7\63\2\2PO\3\2\2\2QT\3\2\2\2RP"+
		"\3\2\2\2RS\3\2\2\2S\7\3\2\2\2TR\3\2\2\2UY\7\7\2\2VX\7\64\2\2WV\3\2\2\2"+
		"X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\]\7\b\2\2]^\5*\26"+
		"\2^_\7\t\2\2_\u0084\3\2\2\2`d\7\n\2\2ac\7\64\2\2ba\3\2\2\2cf\3\2\2\2d"+
		"b\3\2\2\2de\3\2\2\2eg\3\2\2\2fd\3\2\2\2gh\7\b\2\2hi\5*\26\2ij\7\t\2\2"+
		"j\u0084\3\2\2\2ko\7\n\2\2ln\7\64\2\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3"+
		"\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7\b\2\2st\5 \21\2tu\7\t\2\2u\u0084\3\2\2"+
		"\2vz\7\13\2\2wy\7\64\2\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{}\3\2"+
		"\2\2|z\3\2\2\2}~\7\b\2\2~\177\5$\23\2\177\u0080\7\f\2\2\u0080\u0081\5"+
		"*\26\2\u0081\u0082\7\t\2\2\u0082\u0084\3\2\2\2\u0083U\3\2\2\2\u0083`\3"+
		"\2\2\2\u0083k\3\2\2\2\u0083v\3\2\2\2\u0084\t\3\2\2\2\u0085\u0086\5*\26"+
		"\2\u0086\u0087\7\6\2\2\u0087\u008b\5\f\7\2\u0088\u008a\7\63\2\2\u0089"+
		"\u0088\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\13\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u0096\5\16\b\2\u008f\u0090"+
		"\5\36\20\2\u0090\u0091\7\4\2\2\u0091\u0096\3\2\2\2\u0092\u0093\5\26\f"+
		"\2\u0093\u0094\7\4\2\2\u0094\u0096\3\2\2\2\u0095\u008e\3\2\2\2\u0095\u008f"+
		"\3\2\2\2\u0095\u0092\3\2\2\2\u0096\r\3\2\2\2\u0097\u0098\7\r\2\2\u0098"+
		"\u0099\5\20\t\2\u0099\17\3\2\2\2\u009a\u009b\7\b\2\2\u009b\u009c\7/\2"+
		"\2\u009c\u009d\7\t\2\2\u009d\u00a5\7\16\2\2\u009e\u009f\5\22\n\2\u009f"+
		"\u00a0\7\17\2\2\u00a0\u00a1\5\24\13\2\u00a1\u00a3\7\4\2\2\u00a2\u00a4"+
		"\7\63\2\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2"+
		"\u00a5\u009e\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\7\20\2\2\u00aa\21\3\2\2\2\u00ab"+
		"\u00ac\7/\2\2\u00ac\23\3\2\2\2\u00ad\u00b0\5\36\20\2\u00ae\u00b0\5\26"+
		"\f\2\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0\25\3\2\2\2\u00b1\u00b2"+
		"\5\30\r\2\u00b2\u00bb\7\b\2\2\u00b3\u00b8\5\34\17\2\u00b4\u00b5\7\f\2"+
		"\2\u00b5\u00b7\5\34\17\2\u00b6\u00b4\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2"+
		"\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"\u00be\7\t\2\2\u00be\27\3\2\2\2\u00bf\u00c0\t\2\2\2\u00c0\31\3\2\2\2\u00c1"+
		"\u00c6\5\34\17\2\u00c2\u00c3\7\f\2\2\u00c3\u00c5\5\34\17\2\u00c4\u00c2"+
		"\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\33\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\5\36\20\2\u00ca\35\3\2\2\2"+
		"\u00cb\u00cf\b\20\1\2\u00cc\u00ce\t\3\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d2\u00d6\t\4\2\2\u00d3\u00d5\t\3\2\2\u00d4\u00d3\3\2"+
		"\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00f0\5\36\20\t\u00da\u00dc\t"+
		"\3\2\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e4\7\'"+
		"\2\2\u00e1\u00e3\t\3\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\3\2\2\2\u00e6\u00e4\3\2"+
		"\2\2\u00e7\u00f0\5\36\20\6\u00e8\u00f0\5\"\22\2\u00e9\u00ea\7\b\2\2\u00ea"+
		"\u00eb\5\36\20\2\u00eb\u00ec\7\t\2\2\u00ec\u00f0\3\2\2\2\u00ed\u00f0\5"+
		" \21\2\u00ee\u00f0\5*\26\2\u00ef\u00cb\3\2\2\2\u00ef\u00dd\3\2\2\2\u00ef"+
		"\u00e8\3\2\2\2\u00ef\u00e9\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00ee\3\2"+
		"\2\2\u00f0\u0158\3\2\2\2\u00f1\u00f2\f\17\2\2\u00f2\u00f3\7\31\2\2\u00f3"+
		"\u0157\5\36\20\17\u00f4\u00f8\f\r\2\2\u00f5\u00f7\t\3\2\2\u00f6\u00f5"+
		"\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fb\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00ff\7\32\2\2\u00fc\u00fe\t"+
		"\3\2\2\u00fd\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0157\5\36"+
		"\20\16\u0103\u0107\f\f\2\2\u0104\u0106\t\3\2\2\u0105\u0104\3\2\2\2\u0106"+
		"\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010a\3\2"+
		"\2\2\u0109\u0107\3\2\2\2\u010a\u010e\7\33\2\2\u010b\u010d\t\3\2\2\u010c"+
		"\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0111\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0157\5\36\20\r\u0112"+
		"\u0116\f\13\2\2\u0113\u0115\t\3\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3"+
		"\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0119\3\2\2\2\u0118"+
		"\u0116\3\2\2\2\u0119\u011d\7\34\2\2\u011a\u011c\t\3\2\2\u011b\u011a\3"+
		"\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u0120\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0157\5\36\20\f\u0121\u0125\f"+
		"\n\2\2\u0122\u0124\t\3\2\2\u0123\u0122\3\2\2\2\u0124\u0127\3\2\2\2\u0125"+
		"\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0128\3\2\2\2\u0127\u0125\3\2"+
		"\2\2\u0128\u012c\7\35\2\2\u0129\u012b\t\3\2\2\u012a\u0129\3\2\2\2\u012b"+
		"\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012f\3\2"+
		"\2\2\u012e\u012c\3\2\2\2\u012f\u0157\5\36\20\13\u0130\u0134\f\b\2\2\u0131"+
		"\u0133\t\3\2\2\u0132\u0131\3\2\2\2\u0133\u0136\3\2\2\2\u0134\u0132\3\2"+
		"\2\2\u0134\u0135\3\2\2\2\u0135\u0137\3\2\2\2\u0136\u0134\3\2\2\2\u0137"+
		"\u013b\7(\2\2\u0138\u013a\t\3\2\2\u0139\u0138\3\2\2\2\u013a\u013d\3\2"+
		"\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2\2\2\u013d"+
		"\u013b\3\2\2\2\u013e\u0157\5\36\20\t\u013f\u0143\f\7\2\2\u0140\u0142\t"+
		"\3\2\2\u0141\u0140\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143"+
		"\u0144\3\2\2\2\u0144\u0146\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u014a\7)"+
		"\2\2\u0147\u0149\t\3\2\2\u0148\u0147\3\2\2\2\u0149\u014c\3\2\2\2\u014a"+
		"\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u014a\3\2"+
		"\2\2\u014d\u0157\5\36\20\b\u014e\u0152\f\5\2\2\u014f\u0151\t\3\2\2\u0150"+
		"\u014f\3\2\2\2\u0151\u0154\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2"+
		"\2\2\u0153\u0155\3\2\2\2\u0154\u0152\3\2\2\2\u0155\u0157\7\36\2\2\u0156"+
		"\u00f1\3\2\2\2\u0156\u00f4\3\2\2\2\u0156\u0103\3\2\2\2\u0156\u0112\3\2"+
		"\2\2\u0156\u0121\3\2\2\2\u0156\u0130\3\2\2\2\u0156\u013f\3\2\2\2\u0156"+
		"\u014e\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2"+
		"\2\2\u0159\37\3\2\2\2\u015a\u0158\3\2\2\2\u015b\u015c\5*\26\2\u015c\u015d"+
		"\5(\25\2\u015d\u015e\5*\26\2\u015e\u0164\3\2\2\2\u015f\u0160\5*\26\2\u0160"+
		"\u0161\5(\25\2\u0161\u0162\5\36\20\2\u0162\u0164\3\2\2\2\u0163\u015b\3"+
		"\2\2\2\u0163\u015f\3\2\2\2\u0164!\3\2\2\2\u0165\u0169\5$\23\2\u0166\u0169"+
		"\7-\2\2\u0167\u0169\7+\2\2\u0168\u0165\3\2\2\2\u0168\u0166\3\2\2\2\u0168"+
		"\u0167\3\2\2\2\u0169#\3\2\2\2\u016a\u016b\t\5\2\2\u016b%\3\2\2\2\u016c"+
		"\u016f\7\37\2\2\u016d\u016f\7 \2\2\u016e\u016c\3\2\2\2\u016e\u016d\3\2"+
		"\2\2\u016f\'\3\2\2\2\u0170\u0171\t\6\2\2\u0171)\3\2\2\2\u0172\u0173\7"+
		"\60\2\2\u0173+\3\2\2\2-\628>CELRYdoz\u0083\u008b\u0095\u00a3\u00a7\u00af"+
		"\u00b8\u00bb\u00c6\u00cf\u00d6\u00dd\u00e4\u00ef\u00f8\u00ff\u0107\u010e"+
		"\u0116\u011d\u0125\u012c\u0134\u013b\u0143\u014a\u0152\u0156\u0158\u0163"+
		"\u0168\u016e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}