package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeNumberer {


	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		
		System.out.print("Input file: ");
		String inputFileName = console.next();
		
		System.out.print("Output file: ");
		String outputFileName = console.next();
		
		File inputFile = new File(inputFileName);
		
		Scanner in = null;
		PrintWriter out = null;
		
		try {
			in = new Scanner(inputFile);	// �����̸� string�� ���ڷ� �� �� ����.
			out = new PrintWriter(outputFileName);	// �����̸� string�� �����ڷ� �� �� �ִ�.
		} catch (FileNotFoundException e) {
			console.close();
			e.printStackTrace();
			System.exit(1);		// ���ø����̼��� ����.
		}
		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String newLine = line.substring(line.indexOf("*/")+2);
			out.println(newLine);
		}
		console.close();
		in.close();
		out.close();
		
	}
}
