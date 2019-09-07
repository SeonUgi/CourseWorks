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
				tree.buildExpTree();	// ǥ�� Ʈ�� ����
			}
			catch (SyntaxError e) {
				System.out.println("\n" + e.getMessage());
				System.exit(1);
			}
			tree.print();	// ǥ�� Ʈ���� �ð������� ���
			result = tree.evaluate();	// ǥ�� Ʈ�� ����
			System.out.println("=> " + result);
		}
		tree.printSymbolTable(); // ��#�������� �ɺ� ���̺� �ִ� ���� ���
	}
}
