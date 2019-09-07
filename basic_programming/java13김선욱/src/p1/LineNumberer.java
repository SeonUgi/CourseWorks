package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LineNumberer {

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
			in = new Scanner(inputFile);	// 파일이름 string을 인자로 줄 수 없다.
			out = new PrintWriter(outputFileName);	// 파일이름 string을 ㅇ니자로 줄 수 있다.
		} catch (FileNotFoundException e) {
			console.close();
			e.printStackTrace();
			System.exit(1);		// 애플리케이션을 종료.
		}
		
		int lineNumber = 0;
		while(in.hasNextLine()) {
			String line = in.nextLine();
			out.println("/* " + ++lineNumber + " */ " + line);
		}
		console.close();
		in.close();
		out.close();
		
	}
}
