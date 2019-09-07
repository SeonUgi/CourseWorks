package Polynomial_Arithmetic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestInterpreter {
	public static void main(String[] args) throws IOException {		
		StreamTokenizer in  = 	new StreamTokenizer(
								new BufferedReader( 
								new InputStreamReader(System.in)));
		Interpreter itp = new Interpreter(in);
		double result;
		while (true) {
			in.nextToken();
			if (in.ttype == '#')	// 가져온 토큰이 ‘#’이면 프로그램 종료
				break;
			in.pushBack();	// 가져온 토큰을 다시 원위치 시켜 문장을 파싱할 때  
			try {		// .. 처리하도록 한다.
				result = itp.parseStatement();
				System.out.println("=> " + result);
			}
			catch (SyntaxError e) {
				System.out.println("\n" + e.getMessage());
				System.exit(1);
			}
		}
		itp.printSymbolTable();
	}
}
