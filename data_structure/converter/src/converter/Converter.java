package converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Converter {
	Queue<Integer> queue = new LinkedList<Integer>();
	
	public Converter(){
		Scanner inFile = null;
		int count = 0;
		
		try{
			inFile = new Scanner(new FileReader("test.txt"));
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		while(inFile.hasNextLine()){
			String str = inFile.nextLine();
			boolean check = true;
			
			if(str.charAt(0)=='0'){
				check = true;
				count++;
			}
			
			else{
				if(check == true && count != 0 && !queue.contains(count))
					queue.add(count);
				count=0;
				check = false;
			}
		}
		
		inFile.close();
	}
	
	public void output() throws IOException{
		for(int i=0;i<queue.size();i++){
			char c = (char)(queue.poll()+65);
			System.out.printf("%c ",c);
		}
		/*
		PrintStream out = new PrintStream(new File("output.txt"));
		StringBuilder sb = new StringBuilder();
		
		sb.append('0');
		for(int i=0;i<queue.size()-1;i++){
			int temp = queue.poll();
			if(temp != queue.peek())
				sb.append('0');
			else
				sb.append('1');
			
			if(sb.length()%7==0){
				char c = (char)Integer.parseInt(sb.toString(), 2);
				out.printf("%c",c);
				sb.setLength(0);
			}
		}
		out.close();
		*/
	}

	public static void main(String[] args) throws IOException {
		Converter main = new Converter();
		main.output();
	}

}
