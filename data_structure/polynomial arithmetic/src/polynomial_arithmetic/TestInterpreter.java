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
		//파일 출력을 위한 파일출력스트림
		Interpreter itp = new Interpreter(in);
		
		while(in.nextToken() != StreamTokenizer.TT_EOF){	//파일 끝에 도달할 때까지 반복
			itp.Expression();	// 다항식 연산
			itp.printSymbolTable(System.out);	//결과 출력
			itp.printSymbolTable(out);	//결과 출력
		}
	}
}
