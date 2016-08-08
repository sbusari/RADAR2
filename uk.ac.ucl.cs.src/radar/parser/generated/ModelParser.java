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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		NOT=39, AND=40, OR=41, HexLiteral=42, DecimalLiteral=43, OctalLiteral=44, 
		FloatingPointLiteral=45, CharacterLiteral=46, StringLiteral=47, Identifier=48, 
		COMMENT=49, LINE_COMMENT=50, NEWLINE=51, SINGLESPACE=52, WS=53;
	public static final int
		RULE_model = 0, RULE_model_element = 1, RULE_objective_decl = 2, RULE_objective_def = 3, 
		RULE_quality_var_decl = 4, RULE_quality_var_def = 5, RULE_decision_def = 6, 
		RULE_decision_body = 7, RULE_option_name = 8, RULE_option_def = 9, RULE_parameter_def = 10, 
		RULE_distribution = 11, RULE_distribution_args = 12, RULE_distribution_arg = 13, 
		RULE_arithmetic_expr = 14, RULE_comparision = 15, RULE_number = 16, RULE_integerLiteral = 17, 
		RULE_optimisationDirection = 18, RULE_comparator = 19, RULE_var_name = 20;
	public static final String[] ruleNames = {
		"model", "model_element", "objective_decl", "objective_def", "quality_var_decl", 
		"quality_var_def", "decision_def", "decision_body", "option_name", "option_def", 
		"parameter_def", "distribution", "distribution_args", "distribution_arg", 
		"arithmetic_expr", "comparision", "number", "integerLiteral", "optimisationDirection", 
		"comparator", "var_name"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Model'", "';'", "'Objective'", "'='", "'with'", "'margin'", "'E'", 
		"'('", "')'", "'P'", "'percentile'", "','", "'decision'", "'{'", "':'", 
		"'}'", "'deterministic'", "'normal'", "'normal_ci'", "'geometric'", "'exponential'", 
		"'random'", "'uniform'", "'triangular'", "'^'", "'/'", "'*'", "'+'", "'-'", 
		"'%'", "'Max'", "'Min'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "NOT", "AND", "OR", "HexLiteral", "DecimalLiteral", 
		"OctalLiteral", "FloatingPointLiteral", "CharacterLiteral", "StringLiteral", 
		"Identifier", "COMMENT", "LINE_COMMENT", "NEWLINE", "SINGLESPACE", "WS"
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
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
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

			setState(80);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(76);
				match(T__4);
				setState(77);
				number();
				setState(78);
				match(T__5);
				}
			}

			setState(82);
			match(T__1);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(83);
				match(NEWLINE);
				}
				}
				setState(88);
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
			setState(135);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new ObjectiveExpectationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				match(T__6);
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(90);
					match(SINGLESPACE);
					}
					}
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(96);
				match(T__7);
				setState(97);
				var_name();
				setState(98);
				match(T__8);
				}
				break;
			case 2:
				_localctx = new ObjectiveBooleanProbabilityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				match(T__9);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(101);
					match(SINGLESPACE);
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(107);
				match(T__7);
				setState(108);
				var_name();
				setState(109);
				match(T__8);
				}
				break;
			case 3:
				_localctx = new ObjectiveProbabilityContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				match(T__9);
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(112);
					match(SINGLESPACE);
					}
					}
					setState(117);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(118);
				match(T__7);
				setState(119);
				comparision();
				setState(120);
				match(T__8);
				}
				break;
			case 4:
				_localctx = new ObjectivePercentileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(122);
				match(T__10);
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SINGLESPACE) {
					{
					{
					setState(123);
					match(SINGLESPACE);
					}
					}
					setState(128);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(129);
				match(T__7);
				setState(130);
				integerLiteral();
				setState(131);
				match(T__11);
				setState(132);
				var_name();
				setState(133);
				match(T__8);
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
			setState(137);
			var_name();
			setState(138);
			match(T__3);
			setState(139);
			quality_var_def();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(140);
				match(NEWLINE);
				}
				}
				setState(145);
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
			setState(153);
			switch (_input.LA(1)) {
			case T__12:
				_localctx = new QualityVariableDecisionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				decision_def();
				}
				break;
			case T__7:
			case T__27:
			case T__28:
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
				setState(147);
				arithmetic_expr(0);
				setState(148);
				match(T__1);
				}
				break;
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
				_localctx = new QualityVariableParameterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				parameter_def();
				setState(151);
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
			setState(155);
			match(T__12);
			setState(156);
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
			setState(158);
			match(T__7);
			setState(159);
			((Decision_bodyContext)_localctx).decision_Name = match(StringLiteral);
			setState(160);
			match(T__8);
			setState(161);
			match(T__13);
			setState(169); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(162);
				option_name();
				setState(163);
				match(T__14);
				setState(164);
				option_def();
				setState(165);
				match(T__1);
				setState(167);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(166);
					match(NEWLINE);
					}
				}

				}
				}
				setState(171); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==StringLiteral );
			setState(173);
			match(T__15);
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
			setState(175);
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
			setState(179);
			switch (_input.LA(1)) {
			case T__7:
			case T__27:
			case T__28:
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
				setState(177);
				arithmetic_expr(0);
				}
				break;
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
				_localctx = new OptionParameterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
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
			setState(181);
			distribution();
			setState(182);
			match(T__7);
			setState(191);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__27) | (1L << T__28) | (1L << NOT) | (1L << HexLiteral) | (1L << DecimalLiteral) | (1L << OctalLiteral) | (1L << FloatingPointLiteral) | (1L << Identifier) | (1L << NEWLINE) | (1L << WS))) != 0)) {
				{
				setState(183);
				distribution_arg();
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(184);
					match(T__11);
					setState(185);
					distribution_arg();
					}
					}
					setState(190);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(193);
			match(T__8);
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
			setState(195);
			((DistributionContext)_localctx).distributionValue = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) ) {
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
			setState(197);
			distribution_arg();
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(198);
				match(T__11);
				setState(199);
				distribution_arg();
				}
				}
				setState(204);
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
			setState(205);
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
	public static class ExprNumberContext extends Arithmetic_exprContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ExprNumberContext(Arithmetic_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).enterExprNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ModelListener ) ((ModelListener)listener).exitExprNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ModelVisitor ) return ((ModelVisitor<? extends T>)visitor).visitExprNumber(this);
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
			setState(243);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				_localctx = new ExprPreOperatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE || _la==WS) {
					{
					{
					setState(208);
					_la = _input.LA(1);
					if ( !(_la==NEWLINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(213);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(214);
				((ExprPreOperatorContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
					((ExprPreOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(218);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(215);
						_la = _input.LA(1);
						if ( !(_la==NEWLINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(220);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				setState(221);
				arithmetic_expr(7);
				}
				break;
			case 2:
				{
				_localctx = new ExprNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE || _la==WS) {
					{
					{
					setState(222);
					_la = _input.LA(1);
					if ( !(_la==NEWLINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(228);
				match(NOT);
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(229);
						_la = _input.LA(1);
						if ( !(_la==NEWLINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(234);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				}
				setState(235);
				arithmetic_expr(4);
				}
				break;
			case 3:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(236);
				number();
				}
				break;
			case 4:
				{
				_localctx = new ExprBracketContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(237);
				match(T__7);
				setState(238);
				arithmetic_expr(0);
				setState(239);
				match(T__8);
				}
				break;
			case 5:
				{
				_localctx = new ExprCompareContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(241);
				comparision();
				}
				break;
			case 6:
				{
				_localctx = new ExprIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(242);
				var_name();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(348);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(346);
					switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
					case 1:
						{
						_localctx = new ExprPowerContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(245);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(246);
						match(T__24);
						setState(247);
						arithmetic_expr(13);
						}
						break;
					case 2:
						{
						_localctx = new ExprDivContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(248);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(252);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(249);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(254);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(255);
						match(T__25);
						setState(259);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(256);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(261);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
						}
						setState(262);
						arithmetic_expr(12);
						}
						break;
					case 3:
						{
						_localctx = new ExprMultContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(263);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(267);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(264);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(269);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(270);
						match(T__26);
						setState(274);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(271);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(276);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
						}
						setState(277);
						arithmetic_expr(11);
						}
						break;
					case 4:
						{
						_localctx = new ExprAddContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(278);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(282);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(279);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(284);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(285);
						match(T__27);
						setState(289);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(286);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(291);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
						}
						setState(292);
						arithmetic_expr(10);
						}
						break;
					case 5:
						{
						_localctx = new ExprSubContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(293);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(297);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(294);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(299);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(300);
						match(T__28);
						setState(304);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(301);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(306);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
						}
						setState(307);
						arithmetic_expr(9);
						}
						break;
					case 6:
						{
						_localctx = new ExprAndContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(308);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(312);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(309);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(314);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(315);
						match(AND);
						setState(319);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(316);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(321);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
						}
						setState(322);
						arithmetic_expr(7);
						}
						break;
					case 7:
						{
						_localctx = new ExprOrContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(323);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(327);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(324);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(329);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(330);
						match(OR);
						setState(334);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(331);
								_la = _input.LA(1);
								if ( !(_la==NEWLINE || _la==WS) ) {
								_errHandler.recoverInline(this);
								} else {
									consume();
								}
								}
								} 
							}
							setState(336);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
						}
						setState(337);
						arithmetic_expr(6);
						}
						break;
					case 8:
						{
						_localctx = new ExprPercentContext(new Arithmetic_exprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_expr);
						setState(338);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(342);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==NEWLINE || _la==WS) {
							{
							{
							setState(339);
							_la = _input.LA(1);
							if ( !(_la==NEWLINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							} else {
								consume();
							}
							}
							}
							setState(344);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(345);
						match(T__29);
						}
						break;
					}
					} 
				}
				setState(350);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
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
			setState(359);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				_localctx = new CompareVariablesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(351);
				var_name();
				setState(352);
				((CompareVariablesContext)_localctx).relationalOp = comparator();
				setState(353);
				var_name();
				}
				break;
			case 2:
				_localctx = new CompareExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				var_name();
				setState(356);
				((CompareExpressionContext)_localctx).relationalOp = comparator();
				setState(357);
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

	public static class NumberContext extends ParserRuleContext {
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
	 
		public NumberContext() { }
		public void copyFrom(NumberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AtomicIntegerContext extends NumberContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public AtomicIntegerContext(NumberContext ctx) { copyFrom(ctx); }
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
	public static class AtomicFloatContext extends NumberContext {
		public TerminalNode FloatingPointLiteral() { return getToken(ModelParser.FloatingPointLiteral, 0); }
		public AtomicFloatContext(NumberContext ctx) { copyFrom(ctx); }
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
	public static class AtomicDecimalContext extends NumberContext {
		public TerminalNode DecimalLiteral() { return getToken(ModelParser.DecimalLiteral, 0); }
		public AtomicDecimalContext(NumberContext ctx) { copyFrom(ctx); }
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

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_number);
		try {
			setState(364);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				_localctx = new AtomicIntegerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(361);
				integerLiteral();
				}
				break;
			case 2:
				_localctx = new AtomicFloatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				match(FloatingPointLiteral);
				}
				break;
			case 3:
				_localctx = new AtomicDecimalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(363);
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
			setState(366);
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
			setState(370);
			switch (_input.LA(1)) {
			case T__30:
				enterOuterAlt(_localctx, 1);
				{
				setState(368);
				((OptimisationDirectionContext)_localctx).max = match(T__30);
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				setState(369);
				((OptimisationDirectionContext)_localctx).min = match(T__31);
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
			setState(372);
			((ComparatorContext)_localctx).logicalOperatorValue = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
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
			setState(374);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\67\u017b\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\7\2\61\n\2\f"+
		"\2\16\2\64\13\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\3\6\3=\n\3\r\3\16\3>\3"+
		"\3\6\3B\n\3\r\3\16\3C\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\5\4M\n\4\3\4\3\4\3"+
		"\4\3\4\5\4S\n\4\3\4\3\4\7\4W\n\4\f\4\16\4Z\13\4\3\5\3\5\7\5^\n\5\f\5\16"+
		"\5a\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5i\n\5\f\5\16\5l\13\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\7\5t\n\5\f\5\16\5w\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\177\n\5"+
		"\f\5\16\5\u0082\13\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u008a\n\5\3\6\3\6\3\6"+
		"\3\6\7\6\u0090\n\6\f\6\16\6\u0093\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7"+
		"\u009c\n\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00aa\n"+
		"\t\6\t\u00ac\n\t\r\t\16\t\u00ad\3\t\3\t\3\n\3\n\3\13\3\13\5\13\u00b6\n"+
		"\13\3\f\3\f\3\f\3\f\3\f\7\f\u00bd\n\f\f\f\16\f\u00c0\13\f\5\f\u00c2\n"+
		"\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\7\16\u00cb\n\16\f\16\16\16\u00ce\13"+
		"\16\3\17\3\17\3\20\3\20\7\20\u00d4\n\20\f\20\16\20\u00d7\13\20\3\20\3"+
		"\20\7\20\u00db\n\20\f\20\16\20\u00de\13\20\3\20\3\20\7\20\u00e2\n\20\f"+
		"\20\16\20\u00e5\13\20\3\20\3\20\7\20\u00e9\n\20\f\20\16\20\u00ec\13\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00f6\n\20\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u00fd\n\20\f\20\16\20\u0100\13\20\3\20\3\20\7\20\u0104"+
		"\n\20\f\20\16\20\u0107\13\20\3\20\3\20\3\20\7\20\u010c\n\20\f\20\16\20"+
		"\u010f\13\20\3\20\3\20\7\20\u0113\n\20\f\20\16\20\u0116\13\20\3\20\3\20"+
		"\3\20\7\20\u011b\n\20\f\20\16\20\u011e\13\20\3\20\3\20\7\20\u0122\n\20"+
		"\f\20\16\20\u0125\13\20\3\20\3\20\3\20\7\20\u012a\n\20\f\20\16\20\u012d"+
		"\13\20\3\20\3\20\7\20\u0131\n\20\f\20\16\20\u0134\13\20\3\20\3\20\3\20"+
		"\7\20\u0139\n\20\f\20\16\20\u013c\13\20\3\20\3\20\7\20\u0140\n\20\f\20"+
		"\16\20\u0143\13\20\3\20\3\20\3\20\7\20\u0148\n\20\f\20\16\20\u014b\13"+
		"\20\3\20\3\20\7\20\u014f\n\20\f\20\16\20\u0152\13\20\3\20\3\20\3\20\7"+
		"\20\u0157\n\20\f\20\16\20\u015a\13\20\3\20\7\20\u015d\n\20\f\20\16\20"+
		"\u0160\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u016a\n\21\3"+
		"\22\3\22\3\22\5\22\u016f\n\22\3\23\3\23\3\24\3\24\5\24\u0175\n\24\3\25"+
		"\3\25\3\26\3\26\3\26\2\3\36\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*\2\7\3\2\23\32\4\2\65\65\67\67\3\2\36\37\3\2,.\3\2#(\u019f\2,\3"+
		"\2\2\2\4E\3\2\2\2\6G\3\2\2\2\b\u0089\3\2\2\2\n\u008b\3\2\2\2\f\u009b\3"+
		"\2\2\2\16\u009d\3\2\2\2\20\u00a0\3\2\2\2\22\u00b1\3\2\2\2\24\u00b5\3\2"+
		"\2\2\26\u00b7\3\2\2\2\30\u00c5\3\2\2\2\32\u00c7\3\2\2\2\34\u00cf\3\2\2"+
		"\2\36\u00f5\3\2\2\2 \u0169\3\2\2\2\"\u016e\3\2\2\2$\u0170\3\2\2\2&\u0174"+
		"\3\2\2\2(\u0176\3\2\2\2*\u0178\3\2\2\2,-\7\3\2\2-.\5*\26\2.\62\7\4\2\2"+
		"/\61\7\65\2\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63"+
		"8\3\2\2\2\64\62\3\2\2\2\65\67\5\4\3\2\66\65\3\2\2\2\67:\3\2\2\28\66\3"+
		"\2\2\289\3\2\2\29\3\3\2\2\2:8\3\2\2\2;=\5\6\4\2<;\3\2\2\2=>\3\2\2\2><"+
		"\3\2\2\2>?\3\2\2\2?F\3\2\2\2@B\5\n\6\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2C"+
		"D\3\2\2\2DF\3\2\2\2E<\3\2\2\2EA\3\2\2\2F\5\3\2\2\2GH\7\5\2\2HI\5&\24\2"+
		"IL\5*\26\2JK\7\6\2\2KM\5\b\5\2LJ\3\2\2\2LM\3\2\2\2MR\3\2\2\2NO\7\7\2\2"+
		"OP\5\"\22\2PQ\7\b\2\2QS\3\2\2\2RN\3\2\2\2RS\3\2\2\2ST\3\2\2\2TX\7\4\2"+
		"\2UW\7\65\2\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\7\3\2\2\2ZX\3\2"+
		"\2\2[_\7\t\2\2\\^\7\66\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b"+
		"\3\2\2\2a_\3\2\2\2bc\7\n\2\2cd\5*\26\2de\7\13\2\2e\u008a\3\2\2\2fj\7\f"+
		"\2\2gi\7\66\2\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3"+
		"\2\2\2mn\7\n\2\2no\5*\26\2op\7\13\2\2p\u008a\3\2\2\2qu\7\f\2\2rt\7\66"+
		"\2\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vx\3\2\2\2wu\3\2\2\2xy\7\n"+
		"\2\2yz\5 \21\2z{\7\13\2\2{\u008a\3\2\2\2|\u0080\7\r\2\2}\177\7\66\2\2"+
		"~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7\n\2\2\u0084\u0085\5$"+
		"\23\2\u0085\u0086\7\16\2\2\u0086\u0087\5*\26\2\u0087\u0088\7\13\2\2\u0088"+
		"\u008a\3\2\2\2\u0089[\3\2\2\2\u0089f\3\2\2\2\u0089q\3\2\2\2\u0089|\3\2"+
		"\2\2\u008a\t\3\2\2\2\u008b\u008c\5*\26\2\u008c\u008d\7\6\2\2\u008d\u0091"+
		"\5\f\7\2\u008e\u0090\7\65\2\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2"+
		"\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\13\3\2\2\2\u0093\u0091"+
		"\3\2\2\2\u0094\u009c\5\16\b\2\u0095\u0096\5\36\20\2\u0096\u0097\7\4\2"+
		"\2\u0097\u009c\3\2\2\2\u0098\u0099\5\26\f\2\u0099\u009a\7\4\2\2\u009a"+
		"\u009c\3\2\2\2\u009b\u0094\3\2\2\2\u009b\u0095\3\2\2\2\u009b\u0098\3\2"+
		"\2\2\u009c\r\3\2\2\2\u009d\u009e\7\17\2\2\u009e\u009f\5\20\t\2\u009f\17"+
		"\3\2\2\2\u00a0\u00a1\7\n\2\2\u00a1\u00a2\7\61\2\2\u00a2\u00a3\7\13\2\2"+
		"\u00a3\u00ab\7\20\2\2\u00a4\u00a5\5\22\n\2\u00a5\u00a6\7\21\2\2\u00a6"+
		"\u00a7\5\24\13\2\u00a7\u00a9\7\4\2\2\u00a8\u00aa\7\65\2\2\u00a9\u00a8"+
		"\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a4\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2"+
		"\2\2\u00af\u00b0\7\22\2\2\u00b0\21\3\2\2\2\u00b1\u00b2\7\61\2\2\u00b2"+
		"\23\3\2\2\2\u00b3\u00b6\5\36\20\2\u00b4\u00b6\5\26\f\2\u00b5\u00b3\3\2"+
		"\2\2\u00b5\u00b4\3\2\2\2\u00b6\25\3\2\2\2\u00b7\u00b8\5\30\r\2\u00b8\u00c1"+
		"\7\n\2\2\u00b9\u00be\5\34\17\2\u00ba\u00bb\7\16\2\2\u00bb\u00bd\5\34\17"+
		"\2\u00bc\u00ba\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf"+
		"\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00b9\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\7\13\2\2\u00c4\27\3\2\2"+
		"\2\u00c5\u00c6\t\2\2\2\u00c6\31\3\2\2\2\u00c7\u00cc\5\34\17\2\u00c8\u00c9"+
		"\7\16\2\2\u00c9\u00cb\5\34\17\2\u00ca\u00c8\3\2\2\2\u00cb\u00ce\3\2\2"+
		"\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\33\3\2\2\2\u00ce\u00cc"+
		"\3\2\2\2\u00cf\u00d0\5\36\20\2\u00d0\35\3\2\2\2\u00d1\u00d5\b\20\1\2\u00d2"+
		"\u00d4\t\3\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8"+
		"\u00dc\t\4\2\2\u00d9\u00db\t\3\2\2\u00da\u00d9\3\2\2\2\u00db\u00de\3\2"+
		"\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00df\u00f6\5\36\20\t\u00e0\u00e2\t\3\2\2\u00e1\u00e0\3"+
		"\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		"\u00e6\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00ea\7)\2\2\u00e7\u00e9\t\3"+
		"\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea"+
		"\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00f6\5\36"+
		"\20\6\u00ee\u00f6\5\"\22\2\u00ef\u00f0\7\n\2\2\u00f0\u00f1\5\36\20\2\u00f1"+
		"\u00f2\7\13\2\2\u00f2\u00f6\3\2\2\2\u00f3\u00f6\5 \21\2\u00f4\u00f6\5"+
		"*\26\2\u00f5\u00d1\3\2\2\2\u00f5\u00e3\3\2\2\2\u00f5\u00ee\3\2\2\2\u00f5"+
		"\u00ef\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6\u015e\3\2"+
		"\2\2\u00f7\u00f8\f\17\2\2\u00f8\u00f9\7\33\2\2\u00f9\u015d\5\36\20\17"+
		"\u00fa\u00fe\f\r\2\2\u00fb\u00fd\t\3\2\2\u00fc\u00fb\3\2\2\2\u00fd\u0100"+
		"\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3\2\2\2\u0100"+
		"\u00fe\3\2\2\2\u0101\u0105\7\34\2\2\u0102\u0104\t\3\2\2\u0103\u0102\3"+
		"\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\u0108\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u015d\5\36\20\16\u0109\u010d"+
		"\f\f\2\2\u010a\u010c\t\3\2\2\u010b\u010a\3\2\2\2\u010c\u010f\3\2\2\2\u010d"+
		"\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u0110\3\2\2\2\u010f\u010d\3\2"+
		"\2\2\u0110\u0114\7\35\2\2\u0111\u0113\t\3\2\2\u0112\u0111\3\2\2\2\u0113"+
		"\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0117\3\2"+
		"\2\2\u0116\u0114\3\2\2\2\u0117\u015d\5\36\20\r\u0118\u011c\f\13\2\2\u0119"+
		"\u011b\t\3\2\2\u011a\u0119\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2"+
		"\2\2\u011c\u011d\3\2\2\2\u011d\u011f\3\2\2\2\u011e\u011c\3\2\2\2\u011f"+
		"\u0123\7\36\2\2\u0120\u0122\t\3\2\2\u0121\u0120\3\2\2\2\u0122\u0125\3"+
		"\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\3\2\2\2\u0125"+
		"\u0123\3\2\2\2\u0126\u015d\5\36\20\f\u0127\u012b\f\n\2\2\u0128\u012a\t"+
		"\3\2\2\u0129\u0128\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c\u012e\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0132\7\37"+
		"\2\2\u012f\u0131\t\3\2\2\u0130\u012f\3\2\2\2\u0131\u0134\3\2\2\2\u0132"+
		"\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0135\3\2\2\2\u0134\u0132\3\2"+
		"\2\2\u0135\u015d\5\36\20\13\u0136\u013a\f\b\2\2\u0137\u0139\t\3\2\2\u0138"+
		"\u0137\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2"+
		"\2\2\u013b\u013d\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u0141\7*\2\2\u013e"+
		"\u0140\t\3\2\2\u013f\u013e\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2"+
		"\2\2\u0141\u0142\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u0141\3\2\2\2\u0144"+
		"\u015d\5\36\20\t\u0145\u0149\f\7\2\2\u0146\u0148\t\3\2\2\u0147\u0146\3"+
		"\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u014c\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u0150\7+\2\2\u014d\u014f\t\3"+
		"\2\2\u014e\u014d\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0153\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u015d\5\36"+
		"\20\b\u0154\u0158\f\5\2\2\u0155\u0157\t\3\2\2\u0156\u0155\3\2\2\2\u0157"+
		"\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\3\2"+
		"\2\2\u015a\u0158\3\2\2\2\u015b\u015d\7 \2\2\u015c\u00f7\3\2\2\2\u015c"+
		"\u00fa\3\2\2\2\u015c\u0109\3\2\2\2\u015c\u0118\3\2\2\2\u015c\u0127\3\2"+
		"\2\2\u015c\u0136\3\2\2\2\u015c\u0145\3\2\2\2\u015c\u0154\3\2\2\2\u015d"+
		"\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\37\3\2\2"+
		"\2\u0160\u015e\3\2\2\2\u0161\u0162\5*\26\2\u0162\u0163\5(\25\2\u0163\u0164"+
		"\5*\26\2\u0164\u016a\3\2\2\2\u0165\u0166\5*\26\2\u0166\u0167\5(\25\2\u0167"+
		"\u0168\5\36\20\2\u0168\u016a\3\2\2\2\u0169\u0161\3\2\2\2\u0169\u0165\3"+
		"\2\2\2\u016a!\3\2\2\2\u016b\u016f\5$\23\2\u016c\u016f\7/\2\2\u016d\u016f"+
		"\7-\2\2\u016e\u016b\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016d\3\2\2\2\u016f"+
		"#\3\2\2\2\u0170\u0171\t\5\2\2\u0171%\3\2\2\2\u0172\u0175\7!\2\2\u0173"+
		"\u0175\7\"\2\2\u0174\u0172\3\2\2\2\u0174\u0173\3\2\2\2\u0175\'\3\2\2\2"+
		"\u0176\u0177\t\6\2\2\u0177)\3\2\2\2\u0178\u0179\7\62\2\2\u0179+\3\2\2"+
		"\2.\628>CELRX_ju\u0080\u0089\u0091\u009b\u00a9\u00ad\u00b5\u00be\u00c1"+
		"\u00cc\u00d5\u00dc\u00e3\u00ea\u00f5\u00fe\u0105\u010d\u0114\u011c\u0123"+
		"\u012b\u0132\u013a\u0141\u0149\u0150\u0158\u015c\u015e\u0169\u016e\u0174";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}