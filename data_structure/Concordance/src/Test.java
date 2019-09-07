import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		Scanner inFile = null;
		SimpleBST bst = new SimpleBST();
		try {
			inFile =new Scanner(new File("sample.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int count = 1;
		while(inFile.hasNextLine()){
			String str = inFile.nextLine().toLowerCase();
			StringTokenizer st = new StringTokenizer(str,"()</>-.,!- ");
			while(st.hasMoreTokens())
				bst.insert(st.nextToken(), count);
			count++;
		}
		bst.print();
		inFile.close();
	}

}
