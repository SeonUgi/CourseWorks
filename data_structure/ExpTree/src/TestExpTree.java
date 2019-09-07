import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class TestExpTree {
	public static void main(String[] args) throws IOException, SyntaxError {		
		StreamTokenizer in  = 	new StreamTokenizer(
					new BufferedReader (
					new InputStreamReader(System.in)));
		ExpTree tree = new ExpTree(in);
		double result;
		while (true) {
			in.nextToken();
			if (in.ttype == '#')
				break;
			in.pushBack();
			try {
				tree.buildExpTree();	// 표현 트리 생성
			}
			catch (SyntaxError e) {
				System.out.println("\n" + e.getMessage());
				System.exit(1);
			}
			tree.print();	// 표현 트리를 시각적으로 출력
			result = tree.evaluate();	// 표현 트리 실행
			System.out.println("=> " + result);
		}
		tree.printSymbolTable(); // ‘#’만나면 심볼 테이블에 있는 내용 출력
	}
}
