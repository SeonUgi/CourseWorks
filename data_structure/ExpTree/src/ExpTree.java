import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Map;
import java.util.TreeMap;

enum NodeType {CONSTANT, VARIABLE, OPERATOR}

abstract class ExpTreeNode {
	public abstract NodeType getType();
}

class ConstantNode extends ExpTreeNode {
	private double number;
	public ConstantNode(double number) {
		this.number = number;
	}
	public NodeType getType() {
		return NodeType.CONSTANT;
	}
	public double getNumber() {
		return number;
	}
}

class IdentifierNode extends ExpTreeNode {
	private String name;
	public IdentifierNode(String name) {
		this.name = name;
	}
	public NodeType getType() {
		return NodeType.VARIABLE;
	}
	public String getVar() {
		return name;
	}
}

class OperatorNode extends ExpTreeNode {
	private char op;
	private ExpTreeNode left;
	private ExpTreeNode right;
	public OperatorNode(char op, ExpTreeNode left, ExpTreeNode right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	public NodeType getType() {
		return NodeType.OPERATOR;
	}
	public char getOp() {
		return op;
	}
	public ExpTreeNode getLeft() {
		return left;
	}
	public ExpTreeNode getRight() {
		return right;
	}
}

public class ExpTree {
	private ExpTreeNode root;
	private Map<String, Double> st; // = new TreeMap<>();
	private StreamTokenizer in; 

	public ExpTree(StreamTokenizer in) {
		this.in = in;
		st = new TreeMap<String, Double>();		
		root = null;
		in.ordinaryChar('/');	// '/'�� �ּ����� �����ϴ� ���ڷ� �������Ƿ�
//  �̰��� ��� ������ ���ڷ� ����ϱ� ����
		in.ordinaryChar('-');	// a-b�� �ϳ��� ������ �� �� �����Ƿ� �̰��� 
					// ��� ���� ���ڷ� ����ϱ� ����
	}
	public void buildExpTree() throws IOException, SyntaxError {
		root = readStatement();
	}
	// Statement ::= Identifier '=' Expression ';'
	public ExpTreeNode readStatement() throws IOException, SyntaxError{
		in.nextToken();
		String id = in.sval;
		ExpTreeNode left = new IdentifierNode(id);
		in.nextToken();	// '='����
		if(in.ttype != '=')
			throw new SyntaxError("= ����");
		ExpTreeNode right = readExpression();
		in.nextToken();	//; ����
		if(in.ttype != ';')
			throw new SyntaxError("; ����");
		
		return new OperatorNode('=', left, right);
	}

	// Expression ::= Term { ( '+' | '-' ) Term }	
	public ExpTreeNode readExpression() throws IOException, SyntaxError{
		ExpTreeNode t1,t2;
		char op;
		t1 = readTerm();
		while(true){
			in.nextToken();
			op = (char)in.ttype;
			if(op == '+' || op == '-'){
				t2 = readTerm();
				t1 = new OperatorNode(op, t1, t2);
			}
			else{
				in.pushBack();
				return t1;
			}
		}
	}

	// Term ::= Factor { ( '*' | '/' ) Factor }	
	public ExpTreeNode readTerm() throws IOException, SyntaxError{
		ExpTreeNode t1,t2;
		char op;
		t1 = readFactor();
		while(true){
			in.nextToken();
			op = (char)in.ttype;
			if(op == '*' || op == '/'){
				t2 = readFactor();
				t1 = new OperatorNode(op, t1, t2);
			}
			else{
				in.pushBack();
				return t1;
			}
		}
	}

	// Factor ::= Identifier | Number | '(' Expression ')' | '-' Factor
	public ExpTreeNode readFactor() throws IOException, SyntaxError{
		ExpTreeNode result = null;
		in.nextToken();
		
		if(in.ttype == StreamTokenizer.TT_WORD)
			result =  new IdentifierNode(in.sval);
		
		else if(in.ttype == StreamTokenizer.TT_NUMBER)
			result = new ConstantNode(in.nval);
		
		else if(in.ttype == '('){
			result = readExpression();
			if(in.nextToken()!=')')
				throw new SyntaxError();
		}
		
		else if(in.ttype == '-'){
			result = new ConstantNode(0);
			ExpTreeNode op = new OperatorNode('-', result, readFactor());
			return op;
		}
		
		else
			throw new SyntaxError();
		return result;
	}
	
	public double evaluate() throws SyntaxError{
		return evaluate(root);
	}

	public double evaluate(ExpTreeNode tree) throws SyntaxError{
		double result = 0;
		
		if(tree.getType() == NodeType.CONSTANT)	//�����϶� ���� ��ȯ
			return ((ConstantNode)tree).getNumber();
		
		else if(tree.getType() == NodeType.VARIABLE){	//�����϶� Ʈ���� ������ ��ȯ,������ ����
			if(st.containsKey(((IdentifierNode)tree).getVar()))
				return st.get(((IdentifierNode)tree).getVar());	
			else 
				throw new SyntaxError("Except Varialbe");
		}
		
		else	//�������� ��� ������ ��� ����
			result = evaluate(((OperatorNode)tree).getRight());
		
		if((((OperatorNode) tree).getOp()) == '=') {	// = �϶� ������ ����
			st.put(((IdentifierNode)(((OperatorNode) tree).getLeft())).getVar(), result);
			return result;
		} 
		
		else if ((((OperatorNode) tree).getOp()) == '+') {
			return result += evaluate((((OperatorNode) tree).getLeft()));
		} 
		else if ((((OperatorNode) tree).getOp()) == '-') {
			return result = evaluate((((OperatorNode) tree).getLeft()))	- result;
		} 
		else if ((((OperatorNode) tree).getOp()) == '*') {
			return result *= evaluate((((OperatorNode) tree).getLeft()));
		} 
		else {
			return result = evaluate((((OperatorNode) tree).getLeft()))	/ result;
		}
	}

	public void printSymbolTable() {
		for (Map.Entry<String, Double> entry : st.entrySet()) {
		       System.out.println(entry.getKey() + " = " + entry.getValue());
		}					
	}	
	public void print() {
		print(root,0);
		System.out.println();
	}
	private void print(ExpTreeNode tree,int count) {
		if (tree != null) {
			if(tree.getType() == NodeType.OPERATOR)
				print(((OperatorNode)tree).getRight(),count+1);
			
			for(int i=0;i<count;i++)
				System.out.print("\t");
			
			if(tree.getType() == NodeType.VARIABLE)
				System.out.print(((IdentifierNode)tree).getVar());
			else if(tree.getType() == NodeType.OPERATOR)
				System.out.print(((OperatorNode)tree).getOp());
			else if(tree.getType() == NodeType.CONSTANT)
				System.out.print(((ConstantNode)tree).getNumber());
			System.out.println();
			
			if(tree.getType() == NodeType.OPERATOR)
				print(((OperatorNode)tree).getLeft(),count+1);
		}
	}
}
