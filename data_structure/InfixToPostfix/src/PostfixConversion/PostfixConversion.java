package PostfixConversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PostfixConversion {

	public static void main(String[] args) { 
		String infixLine, postfixLine;
		InfixToPostfixConversion conveter; // PostfixEvaluation Ŭ���� ���
		double result;
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File("input.txt"));
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			System.exit(1);
		}
		while (inFile.hasNextLine()) {
			infixLine = inFile.nextLine();
			System.out.println(infixLine);
			conveter = new InfixToPostfixConversion(infixLine);
			postfixLine = conveter.chageToPostfix();	//�������� => ��������
			System.out.println("==> "+postfixLine);
			result = conveter.evaluatePostfix(postfixLine);	//��������� ����
			System.out.printf("= %.2f\n\n", result);
		}
		inFile.close();
	}
}
