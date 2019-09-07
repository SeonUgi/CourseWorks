package polynomial_arithmetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StreamTokenizer;

public class TestInterpreter {
	public static void main(String[] args) throws IOException, SyntaxError {		
		StreamTokenizer in  = 	new StreamTokenizer(
								new BufferedReader( 
								new FileReader("input.txt")));
		
		PrintStream out = new PrintStream(new File("output.txt"));
		//���� ����� ���� ������½�Ʈ��
		Interpreter itp = new Interpreter(in);
		
		while(in.nextToken() != StreamTokenizer.TT_EOF){	//���� ���� ������ ������ �ݺ�
			itp.Expression();	// ���׽� ����
			itp.printSymbolTable(System.out);	//��� ���
			itp.printSymbolTable(out);	//��� ���
		}
	}
}
