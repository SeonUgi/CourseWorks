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
			if (in.ttype == '#')	// ������ ��ū�� ��#���̸� ���α׷� ����
				break;
			in.pushBack();	// ������ ��ū�� �ٽ� ����ġ ���� ������ �Ľ��� ��  
			try {		// .. ó���ϵ��� �Ѵ�.
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
