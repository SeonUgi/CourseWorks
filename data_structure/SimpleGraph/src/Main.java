import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner inFile = null;
		
		try{
			inFile = new Scanner(new FileReader("input.txt"));
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		Graph<Integer> graph = new Graph<Integer>(true,true);
		graph.createGraph(inFile);	//그래프 만듬
		graph.print();	//출력
		graph.dijkstra();
		inFile.close();
	}
}
