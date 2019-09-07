import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner inputDFA = null;
		Scanner inputString = null;
		
		try{
			inputDFA = new Scanner(new FileReader("input.txt"));
			inputString = new Scanner(new FileReader("string.txt"));
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		Graph<Integer,String> graph = new Graph<Integer,String>();
		graph.createGraph(inputDFA);	//牢立青纺 积己
		while(inputString.hasNextLine()){
			String str = inputString.nextLine();
			if(graph.DFA(str))
				System.out.println("The string "+str+" is legal.");
			
			else
				System.out.println("The string "+str+" is not legal.");
			System.out.println();
		}
		
		inputDFA.close();
		inputString.close();
	}
}
