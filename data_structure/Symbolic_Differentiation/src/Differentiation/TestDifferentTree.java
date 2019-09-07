package Differentiation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class TestDifferentTree {
	public static void main(String[] args) throws IOException, SyntaxError {		
		StreamTokenizer in = new StreamTokenizer(
					new BufferedReader(
					new FileReader("input.txt")));
		DifferentTree tree = new DifferentTree(in);
		while(in.nextToken() != StreamTokenizer.TT_EOF) {
			try {
				tree.buildExpTree();	// 표현 트리 생성
			}
			catch (SyntaxError e) {
				System.out.println("\n" + e.getMessage());
				System.exit(1);
			}
			System.out.print("Expression Tree Output: ");
			tree.print();
			
			System.out.print("Differentiated: ");
			tree.different();
			tree.print();
			
			System.out.print("Simplified: ");
			tree.simplified();
			tree.print();
			System.out.println();
			
			tree.clear();
		}
	}
}