package Polynomial_Arithmetic;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Map;
import java.util.TreeMap;

public class Interpreter {
	private Map<String, Double> st; 
	private StreamTokenizer in; 

	public Interpreter(StreamTokenizer in) throws IOException {
		this.in = in;
		st = new TreeMap<String, Double>();
		in.ordinaryChar('/');	// '/'�� �ּ����� �����ϴ� ���ڷ� �������Ƿ� 
		// �̰��� ��� ������ ���ڷ� ����ϱ� ����
		in.ordinaryChar('-');	// a-b�� �ϳ��� ������ �� �� �����Ƿ� �̰��� 
		// ��� ���� ���ڷ� ����ϱ� ����
	}
	public void printSymbolTable() {
	    for (Map.Entry<String, Double> entry : st.entrySet()) {
		System.out.println(entry.getKey() + " = " + entry.getValue());
	    }					
	}
	
	// Statement ::= Identifier '=' Expression ';'
	public double parseStatement() throws IOException, SyntaxError {
		double result = 0;
		String id = "";
		while(true){
			in.nextToken();
			if(in.ttype==StreamTokenizer.TT_WORD)
				id = in.sval;
			else if(in.ttype=='=')
				result = parseExpression();
			else if(in.ttype==';'){ 
				st.put(id, result);
				return result;
			}
			else
				throw new SyntaxError();
		}
	}
	
	// Expression ::= Term { ( '+' | '-' ) Term }
	public double parseExpression() throws IOException, SyntaxError {
		double result = parseTerm();
		while (true) {
			in.nextToken();
			switch (in.ttype) {
			case '+' : result += parseTerm();break;
			case '-' : result -= parseTerm(); break;
			default : in.pushBack(); return result;
			}
		}
	}
	// Term ::= Factor { ( '*' | '/' ) Factor }
	public double parseTerm() throws IOException, SyntaxError {
		double result = parseFactor();
		while (true) {
			in.nextToken();
			switch (in.ttype) {
			case '*' : result *= parseFactor();break;
			case '/' : result /= parseFactor(); break;
			default : in.pushBack(); return result;
			}
		}
	}
	// Factor ::= Identifier | Number | '(' Expression ')' | '-' Factor
	public double parseFactor() throws IOException, SyntaxError {
		double result = 0;
		in.nextToken();
		
		if(in.ttype == StreamTokenizer.TT_WORD)
			result = st.get(in.sval);
		
		else if(in.ttype == StreamTokenizer.TT_NUMBER)
			result += in.nval;
		
		else if(in.ttype == '('){
			result = parseExpression();
			if(in.nextToken()!=')')
				throw new SyntaxError();
				 
		}
		
		else if(in.ttype == '-')
			result -= parseFactor();
		
		else
			throw new SyntaxError();
		return result;
	}
}
