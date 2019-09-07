import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class TestArrayBST {
	//파일에서 읽어 트리에 저장
	static public void injectFile(ArrayBSTree<Integer> tree){
		Scanner inFile = null;
		try{
			inFile = new Scanner(new File("input.txt"));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		while(inFile.hasNextLine()){
			String str = inFile.nextLine();
			StringTokenizer st = new StringTokenizer(str);
			while(st.hasMoreTokens()){
				tree.insert(Integer.parseInt(st.nextToken()));
			}
		}
		inFile.close();
	}
	
	public static void main(String[] args) {
		ArrayBSTree<Integer> tree = new ArrayBSTree<Integer>();
		injectFile(tree);	//파일에서 읽어 트리에 저장
		String command;
		int data;
	
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a command: i(insert), s(earch), r(emove), d(isplay), p(rint)");

		while (true) {
			System.out.print("> ");
			command = input.next();
			if (command.equals("i")) {
				data = input.nextInt();
				if (tree.insert(data)) 
					System.out.println(data + " inserted.");
				else
					System.out.println(data + " is in the tree.");				
			}
			else if (command.equals("s")) {
				data = input.nextInt();
				if (tree.search(data))
					System.out.println(data + " is in the tree.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("r")) {
				data = input.nextInt();
				if (tree.remove(data))
					System.out.println(data + " removed.");
				else
					System.out.println("No such " + data + "!");
			}
			else if(command.equals("d"))
				tree.display();
			else if (command.equals("p"))
				tree.print();
			else if (command.equals("inorder"))
				tree.inorderTraverse();			
			else if (command.equals("q")) {
				System.out.println("Commands terminated.");
				break;
			}
		}
		input.close();
	}
}
