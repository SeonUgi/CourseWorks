package p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeNumberer2 {

	public static void main(String[] args) {

		String inputFileName = args[0];

		String outputFileName = args[1];

		File inputFile = new File(inputFileName);

		Scanner in = null;
		PrintWriter out = null;

		try {
			in = new Scanner(inputFile);	// 파일이름 string을 인자로 줄 수 없다.
			out = new PrintWriter(outputFileName);	// 파일이름 string을 인자로 줄 수 있다.
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			System.exit(1);		// 애플리케이션을 종료.
		}

		while(in.hasNextLine()) {
			String line = in.nextLine();
			String newLine = line.substring(line.indexOf("*/")+2);
			out.println(newLine);
		}

		in.close();
		out.close();

	}
}
