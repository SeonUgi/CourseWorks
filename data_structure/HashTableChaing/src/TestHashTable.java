import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TestHashTable {
	private static void insertHeshTable(HashTable<Integer, Student> hashtable){
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File("student.txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		while(inFile.hasNextLine()){
			int number = inFile.nextInt();
			String name = inFile.next();
			double score = inFile.nextDouble();
			Student student = new Student(number, name, score);
			hashtable.insert(number, student);
		}
	}

	public static void main(String[] args) {
		HashTable<Integer, Student> hashtable = new HashTable<Integer,Student>();
		insertHeshTable(hashtable);
		Scanner input = null;
		
		try {
			input = new Scanner(new File("query.txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		while (input.hasNextLine()) {
			System.out.println();
			String command = input.next();
			if (command.equals("I")) {
				int number = input.nextInt();
				String name = input.next();
				double score = input.nextDouble();
				Student student = new Student(number, name, score);
				System.out.println("Student inserted: "+student);
				hashtable.insert(number, student);
			}
			
			else if(command.equals("D")){
				int number = input.nextInt();
				System.out.print("Removing student number: "+number);
				if(hashtable.remove(number))
					System.out.println(" removed");
				else
					System.out.println(" not found.");
				
			}
			
			else if(command.equals("P")){
				hashtable.printHashTable();
			}
			
			else if(command.equals("S")){
				int number = input.nextInt();
				System.out.print("Seraching for Student number: "+number);
				if(hashtable.search(number) != null)
					System.out.println("\n\t"+hashtable.search(number));
				else
					System.out.println(" not found.");
			}
			
			else
				continue;
		}
		input.close();
	}

}
