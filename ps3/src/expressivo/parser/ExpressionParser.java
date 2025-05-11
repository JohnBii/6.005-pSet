// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, NUMBER=5, VARIABLE=6, SPACES=7;
  public static final int
    RULE_root = 0, RULE_multiply = 1, RULE_sum = 2, RULE_primitive = 3;
  public static final String[] ruleNames = {
    "root", "multiply", "sum", "primitive"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'*'", "'+'", "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, "NUMBER", "VARIABLE", "SPACES"
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
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
    public PrimitiveContext primitive() {
      return getRuleContext(PrimitiveContext.class,0);
    }
    public SumContext sum() {
      return getRuleContext(SumContext.class,0);
    }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(10);
      switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
      case 1:
        {
        setState(8);
        primitive();
        }
        break;
      case 2:
        {
        setState(9);
        sum();
        }
        break;
      }
      setState(12);
      match(EOF);
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

  public static class MultiplyContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public MultiplyContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_multiply; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMultiply(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMultiply(this);
    }
  }

  public final MultiplyContext multiply() throws RecognitionException {
    MultiplyContext _localctx = new MultiplyContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_multiply);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(14);
      primitive();
      setState(19);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__0) {
        {
        {
        setState(15);
        match(T__0);
        setState(16);
        primitive();
        }
        }
        setState(21);
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

  public static class SumContext extends ParserRuleContext {
    public List<MultiplyContext> multiply() {
      return getRuleContexts(MultiplyContext.class);
    }
    public MultiplyContext multiply(int i) {
      return getRuleContext(MultiplyContext.class,i);
    }
    public SumContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_sum; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterSum(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitSum(this);
    }
  }

  public final SumContext sum() throws RecognitionException {
    SumContext _localctx = new SumContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_sum);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(22);
      multiply();
      setState(27);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__1) {
        {
        {
        setState(23);
        match(T__1);
        setState(24);
        multiply();
        }
        }
        setState(29);
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

  public static class PrimitiveContext extends ParserRuleContext {
    public TerminalNode NUMBER() { return getToken(ExpressionParser.NUMBER, 0); }
    public TerminalNode VARIABLE() { return getToken(ExpressionParser.VARIABLE, 0); }
    public SumContext sum() {
      return getRuleContext(SumContext.class,0);
    }
    public PrimitiveContext primitive() {
      return getRuleContext(PrimitiveContext.class,0);
    }
    public PrimitiveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_primitive; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPrimitive(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPrimitive(this);
    }
  }

  public final PrimitiveContext primitive() throws RecognitionException {
    PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_primitive);
    try {
      setState(40);
      switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        setState(30);
        match(NUMBER);
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        setState(31);
        match(VARIABLE);
        }
        break;
      case 3:
        enterOuterAlt(_localctx, 3);
        {
        setState(32);
        match(T__2);
        setState(33);
        sum();
        setState(34);
        match(T__3);
        }
        break;
      case 4:
        enterOuterAlt(_localctx, 4);
        {
        setState(36);
        match(T__2);
        setState(37);
        primitive();
        setState(38);
        match(T__3);
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

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t-\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\3\2\3\2\5\2\r\n\2\3\2\3\2\3\3\3\3\3\3\7\3\24"+
      "\n\3\f\3\16\3\27\13\3\3\4\3\4\3\4\7\4\34\n\4\f\4\16\4\37\13\4\3\5"+
      "\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5+\n\5\3\5\2\2\6\2\4\6\b\2"+
      "\2.\2\f\3\2\2\2\4\20\3\2\2\2\6\30\3\2\2\2\b*\3\2\2\2\n\r\5\b\5\2\13"+
      "\r\5\6\4\2\f\n\3\2\2\2\f\13\3\2\2\2\r\16\3\2\2\2\16\17\7\2\2\3\17"+
      "\3\3\2\2\2\20\25\5\b\5\2\21\22\7\3\2\2\22\24\5\b\5\2\23\21\3\2\2\2"+
      "\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\5\3\2\2\2\27\25\3\2"+
      "\2\2\30\35\5\4\3\2\31\32\7\4\2\2\32\34\5\4\3\2\33\31\3\2\2\2\34\37"+
      "\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36\7\3\2\2\2\37\35\3\2\2\2 +"+
      "\7\7\2\2!+\7\b\2\2\"#\7\5\2\2#$\5\6\4\2$%\7\6\2\2%+\3\2\2\2&\'\7\5"+
      "\2\2\'(\5\b\5\2()\7\6\2\2)+\3\2\2\2* \3\2\2\2*!\3\2\2\2*\"\3\2\2\2"+
      "*&\3\2\2\2+\t\3\2\2\2\6\f\25\35*";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}