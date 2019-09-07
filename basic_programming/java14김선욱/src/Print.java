

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Print {
	public static void main(String[] args) {
		String fileName = "sample.txt";
		Scanner input = null;

		try{
			input = new Scanner(new File(fileName));
			
			Date date1 = new Date(input.nextInt(), input.nextInt(), input.nextInt());
			String whatDay = input.next();
			date1.setStartDay(whatDay);
			while(input.hasNextInt()) {
				
				Date date2 = new Date(input.nextInt(), input.nextInt());
				System.out.println(date1.toString(date2));
				System.out.println();
			}
		}
		catch(FileNotFoundException e) {
			System.out.print(fileName + " 파일을 불러올 수 없어!!!!!");
		}
		input.close();

	}

}
