import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestMegre {	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner inFile = new Scanner(new FileReader("input.txt"));
		int arr[][] = new int[10][];
		
		for(int i=0;i<arr.length;i++){
			String[] str = inFile.nextLine().split(" ");
			arr[i] = new int[str.length];
			for(int j=0;j<str.length;j++)
				arr[i][j] = Integer.parseInt(str[j]);
		}
		inFile.close();
		Merge merge = new Merge(arr);
		
		System.out.println("Input data in 2-dimensional array");
		for(int i=0;i<arr.length;i++){
			System.out.print("["+i+"]");
			for(int j=0;j<arr[i].length;j++)
				System.out.print(" "+arr[i][j]);
			System.out.println();
		}
		merge.print();
	}
}
