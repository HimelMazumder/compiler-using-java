/* Generated By:JavaCC: Do not edit this line. Calculator.java */
import java.io.*;
import java.util.ArrayList;

public class Calculator implements CalculatorConstants {
  public static void main(String args []) throws ParseException, TokenMgrError, NumberFormatException
  {
    try {
      Calculator parser = new Calculator(new FileInputStream("E:\u005c\u005cDriveFolder\u005c\u005cAcademic_stuff\u005c\u005cUIU\u005c\u005ccourse_stuff\u005c\u005c8th_Trimester\u005c\u005cCompilerLab\u005c\u005cassignment_&_labwork_Compiler_Lab\u005c\u005cassignments\u005c\u005cAssignment_04_withJavaCC\u005c\u005csrc\u005c\u005cothers\u005c\u005cinput.txt"));
      parser.Start(System.out);
    }catch(Exception e) {
      e.printStackTrace();
    }

  }

  static ArrayList<Variable> variables = new ArrayList<Variable>();
  float previousValue = (float) 0.0;
  int expCount = 1;

  final public Variable searchVariables(String var) throws ParseException {
  Variable variable;
     variable = null;
     if(variables != null) {
       for(Variable tVar: variables) {
         if(tVar.name.equals(var)) {
           variable = tVar;
           break;
         }
       }
     }
     {if (true) return variable;}
    throw new Error("Missing return statement in function");
  }

  final public float Primary(PrintStream printStream) throws ParseException, NumberFormatException {
  Token t;
  Variable var;
  float d;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      t = jj_consume_token(VAR);
    var = searchVariables(t.image);
    if(var != null) {
      {if (true) return var.value;}
    }else {
        printStream.println( "Value of  " + t.image + " not found!" ); expCount++;
    }
      break;
    case NUMBER:
      t = jj_consume_token(NUMBER);
    {if (true) return Float.parseFloat(t.image);}
      break;
    case PREVIOUS:
      jj_consume_token(PREVIOUS);
    {if (true) return previousValue;}
      break;
    case OPEN_PAR:
      jj_consume_token(OPEN_PAR);
      d = Expression();
      jj_consume_token(CLOSE_PAR);
    {if (true) return d;}
      break;
    case MINUS:
      jj_consume_token(MINUS);
      d = Primary(System.out);
        {if (true) return -d;}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public float Term() throws ParseException, NumberFormatException {
        float i;
        float value;
    value = Primary(System.out);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULTIPLY:
      case DIVIDE:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULTIPLY:
        jj_consume_token(MULTIPLY);
        i = Primary(System.out);
                  value *= i;
        break;
      case DIVIDE:
        jj_consume_token(DIVIDE);
        i = Primary(System.out);
                  value /= i;
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public float Expression() throws ParseException, NumberFormatException {
        float i;
        float value;
    value = Term();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        i = Term();
      value += i;
        break;
      case MINUS:
        jj_consume_token(MINUS);
        i = Term();
      value -= i;
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

// Start - > (Expression EOL)* EOF 
  final public void Start(PrintStream printStream) throws ParseException, NumberFormatException {
  Token t;
  Variable var;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
      case MINUS:
      case NUMBER:
      case OPEN_PAR:
      case PREVIOUS:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        t = jj_consume_token(VAR);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case EOE:
          jj_consume_token(EOE);
      var = searchVariables(t.image);
      if(var != null) {
        printStream.println( "Value of  " + t.image + "(same as before): " + var.value ); expCount++;
      }else {
        printStream.println( "Value of  " + t.image + " not found!" ); expCount++;
      }
          break;
        case ASSIGN:
          jj_consume_token(ASSIGN);
          previousValue = Expression();
          jj_consume_token(EOE);
      var = searchVariables(t.image);
      if(var != null) {
        var.value = previousValue;
        printStream.println( "Value of  " + t.image + "(updated): " + var.value ); expCount++;
      }else {
        variables.add(new Variable(t.image, previousValue));
        printStream.println( "Value of  " + t.image + "(newly created): " + previousValue ); expCount++;
      }
          break;
        case PLUS:
          jj_consume_token(PLUS);
          previousValue = Expression();
          jj_consume_token(EOE);
      var = searchVariables(t.image);
      if(var != null) {
        previousValue += var.value;
        { printStream.println( "Result of expression " + expCount + ": " + previousValue ); expCount++;}
      }else {
        variables.add(new Variable(t.image, previousValue));
        printStream.println( "Value of  " + t.image + " not found!" ); expCount++;
      }
          break;
        case MINUS:
          jj_consume_token(MINUS);
          previousValue = Expression();
          jj_consume_token(EOE);
      var = searchVariables(t.image);
      if(var != null) {
        previousValue -= var.value;
        { printStream.println( "Result of expression " + expCount + ": " + previousValue ); expCount++;}
      }else {
        variables.add(new Variable(t.image, previousValue));
        printStream.println( "Value of  " + t.image + " not found!" ); expCount++;
      }
          break;
        case MULTIPLY:
          jj_consume_token(MULTIPLY);
          previousValue = Expression();
          jj_consume_token(EOE);
      var = searchVariables(t.image);
      if(var != null) {
        previousValue *= var.value;
        { printStream.println( "Result of expression " + expCount + ": " + previousValue ); expCount++;}
      }else {
        variables.add(new Variable(t.image, previousValue));
        printStream.println( "Value of  " + t.image + " not found!" ); expCount++;
      }
          break;
        case DIVIDE:
          jj_consume_token(DIVIDE);
          previousValue = Expression();
          jj_consume_token(EOE);
      var = searchVariables(t.image);
      if(var != null) {
        previousValue /= var.value;
        { printStream.println( "Result of expression " + expCount + ": " + previousValue ); expCount++;}
      }else {
        variables.add(new Variable(t.image, previousValue));
        printStream.println( "Value of  " + t.image + " not found!" ); expCount++;
      }
          break;
        default:
          jj_la1[6] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      case MINUS:
      case NUMBER:
      case OPEN_PAR:
      case PREVIOUS:
        previousValue = Expression();
        jj_consume_token(EOE);
      printStream.println( "Result of expression " + expCount + ": " + previousValue ); expCount++;
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(0);
  }

  /** Generated Token Manager. */
  public CalculatorTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x5450,0x180,0x180,0x60,0x60,0x5450,0x3e8,0x5450,};
   }

  /** Constructor with InputStream. */
  public Calculator(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Calculator(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CalculatorTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Calculator(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CalculatorTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Calculator(CalculatorTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CalculatorTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[15];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 15; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
