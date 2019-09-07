package Differentiation;
import java.io.IOException;
import java.io.StreamTokenizer;

enum NodeType {CONSTANT, VARIABLE, OPERATOR}

abstract class DifferentTreeNode {	//Ÿ���� ������ �߻�Ŭ����
	public abstract NodeType getType();
}

class ConstantNode extends DifferentTreeNode {	//����� ���
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

class IdentifierNode extends DifferentTreeNode {	//������ ���
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

class OperatorNode extends DifferentTreeNode {	//�������� ���
	private char op;
	private DifferentTreeNode left;
	private DifferentTreeNode right;
	public OperatorNode(char op, DifferentTreeNode left, DifferentTreeNode right) {
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
	public DifferentTreeNode getLeft() {
		return left;
	}
	public void setLeft(DifferentTreeNode left){
		this.left = left;
	}
	public DifferentTreeNode getRight() {
		return right;
	}
	public void setRight(DifferentTreeNode right){
		this.right = right;
	}
}


public class DifferentTree {
	private DifferentTreeNode root;
	private StreamTokenizer in; 

	public DifferentTree(StreamTokenizer in) {
		this.in = in;
		root = null;
		in.ordinaryChar('/');	// '/'�� �ּ����� �����ϴ� ���ڷ� �������Ƿ�
								// �̰��� ��� ������ ���ڷ� ����ϱ� ����
		in.ordinaryChar('-');	// a-b�� �ϳ��� ������ �� �� �����Ƿ� �̰��� 
								// ��� ���� ���ڷ� ����ϱ� ����
	}
	
	public void clear(){
		root =null;
	}
	public void buildExpTree() throws IOException, SyntaxError {
		root = readStatement();
	}
	
	// Statement ::= Expression op Expression ';'
	public DifferentTreeNode readStatement() throws IOException, SyntaxError{
		in.pushBack();
		DifferentTreeNode left = readExpression();
		DifferentTreeNode right = null;
		char op =0;
		
		in.nextToken();
		if(in.ttype != ';' && in.ttype == StreamTokenizer.TT_EOL){
			op = (char)in.ttype;
			right = readExpression();
		}
		
		if(in.ttype != ';')
			throw new SyntaxError("; ����");
		
		if(right != null)
			return new OperatorNode(op, left, right);
		else 
			return left;
	}

	// Expression ::= Term { ( '+' | '-' ) Term }	
	public DifferentTreeNode readExpression() throws IOException, SyntaxError{
		DifferentTreeNode t1,t2;
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

	// Term ::= Factor { ( '^' | '*' | '/' ) Factor }	
	public DifferentTreeNode readTerm() throws IOException, SyntaxError{
		DifferentTreeNode t1,t2;
		char op;
		t1 = readFactor();
		while(true){
			in.nextToken();
			op = (char)in.ttype;
			if(op == '^' || op == '*' || op == '/'){
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
	public DifferentTreeNode readFactor() throws IOException, SyntaxError{
		DifferentTreeNode result = null;
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
			DifferentTreeNode op = new OperatorNode('-', result, readFactor());
			return op;
		}
		
		else
			throw new SyntaxError();
		return result;
	}
	
	public void different(){	//�̺�
		root = differentRecusion(root);
	}
	private DifferentTreeNode differentRecusion(DifferentTreeNode tree){		
		if(tree.getType() != NodeType.OPERATOR){	//�����ڰ� �ƴҽ�
			if(tree.getType()==NodeType.CONSTANT)	//����϶� 0����
				return new ConstantNode(0);		
			else if(tree.getType()==NodeType.VARIABLE){	//�����϶� x�̸� 1 x�� �ƴϸ� 0
				if(((IdentifierNode)tree).getVar().equals("x"))
					return new ConstantNode(1);
				else
					return new ConstantNode(0);
			}
		}
		
		else{
			DifferentTreeNode left=((OperatorNode)tree).getLeft();
			DifferentTreeNode right=((OperatorNode)tree).getRight();
			
			if(((OperatorNode)tree).getOp() == '*'){	//* �̺�
				DifferentTreeNode tmpLeft = new OperatorNode('*',left,differentRecusion(right));
				DifferentTreeNode tmpRight = new OperatorNode('*',differentRecusion(left),right); 
				tree=new OperatorNode('+',tmpLeft,tmpRight);
			}
			else if(((OperatorNode)tree).getOp() == '/'){	//�̺�
				DifferentTreeNode tmpLeft = new OperatorNode('*',differentRecusion(left),right); 
				DifferentTreeNode tmpRight = new OperatorNode('*',left,differentRecusion(right));
				tmpLeft=new OperatorNode('-',tmpLeft,tmpRight);
				tmpRight = new OperatorNode('*',right,right);
				tree=new OperatorNode('/',tmpLeft,tmpRight);
			}
			else if(((OperatorNode)tree).getOp() == '+'){	//+�̺�
				tree=new OperatorNode('+',differentRecusion(left),differentRecusion(right));
			}
			else if(((OperatorNode)tree).getOp() == '-'){	//-�̺�
				tree=new OperatorNode('-',differentRecusion(left),differentRecusion(right));
			}
			else if(((OperatorNode)tree).getOp() == '^'){	//^ �̺� 
				//���� ���� ��忡 ����
				DifferentTreeNode tmpLeft =new OperatorNode('*',((OperatorNode)tree).getRight(),	 
									new OperatorNode('^',((OperatorNode)tree).getLeft(),
									new ConstantNode(((ConstantNode)right).getNumber()-1)));
				DifferentTreeNode tmpRight = differentRecusion(left);	//x�� ���� �̺�
				tree = new OperatorNode('*',tmpLeft,tmpRight); 
			}
		}
		return tree;
	}
	
	public void simplified(){
		root = simplified(root);
	}
	public DifferentTreeNode simplified(DifferentTreeNode tree){
		if(tree.getType() == NodeType.OPERATOR){
			((OperatorNode)tree).setLeft(simplified(((OperatorNode)tree).getLeft()));	//���ʳ�� ���� ���� ��ȸ
			
			if(tree.getType() != NodeType.OPERATOR)
				return null;
			
			if(((OperatorNode)tree).getLeft().getType() == NodeType.CONSTANT){	//������ ��� �϶�
				if(((ConstantNode)(((OperatorNode)tree).getLeft())).getNumber() == 0){	//������ 0�϶�
					if(((OperatorNode)tree).getOp() == '+' && ((OperatorNode)tree).getRight().getType() != NodeType.OPERATOR)
						return ((OperatorNode)tree).getRight();
					
					else if(((OperatorNode)tree).getOp() == '*')
						return new ConstantNode(0);
					
					else if(((OperatorNode)tree).getOp() == '/')
						return new ConstantNode(0);
				}
				
				else if(((ConstantNode)(((OperatorNode)tree).getLeft())).getNumber() == 1){	//������ 1�϶�
					if(((OperatorNode)tree).getOp() == '*')
						return ((OperatorNode)tree).getRight();
				}
			}
			
			if(((OperatorNode)tree).getRight().getType() == NodeType.CONSTANT){	//�������� ��� �϶�
				if(((ConstantNode)(((OperatorNode)tree).getRight())).getNumber() == 0){	//�������� 0�϶�
					if(((OperatorNode)tree).getOp() == '+' && ((OperatorNode)tree).getLeft().getType() != NodeType.OPERATOR)
						return ((OperatorNode)tree).getLeft();
					
					else if(((OperatorNode)tree).getOp() == '-')
						return ((OperatorNode)tree).getLeft();
					
					else if(((OperatorNode)tree).getOp() == '*')
						return new ConstantNode(0);
					
					else if(((OperatorNode)tree).getOp() == '/')
						return new ConstantNode(0);
					
					else if(((OperatorNode)tree).getOp() == '^')
						return new ConstantNode(1);
				}
				
				else if(((ConstantNode)(((OperatorNode)tree).getRight())).getNumber() == 1){	//�������� 1�϶�
					if(((OperatorNode)tree).getOp() == '*')
						return ((OperatorNode)tree).getLeft();
					
					else if(((OperatorNode)tree).getOp() == '/')
						return ((OperatorNode)tree).getLeft();
					
					else if(((OperatorNode)tree).getOp() == '^')
						return ((OperatorNode)tree).getLeft();
				}
				
				if(((OperatorNode)tree).getLeft().getType() == NodeType.CONSTANT){	//������ ����϶�
					if(((OperatorNode)tree).getOp() == '+')
						return new ConstantNode(((ConstantNode)(((OperatorNode)tree).getLeft())).getNumber()+
								((ConstantNode)(((OperatorNode)tree).getRight())).getNumber());
					
					else if(((OperatorNode)tree).getOp() == '-')
						return new ConstantNode(((ConstantNode)(((OperatorNode)tree).getLeft())).getNumber()-
								((ConstantNode)(((OperatorNode)tree).getRight())).getNumber());
					
					else if(((OperatorNode)tree).getOp() == '*')
						return new ConstantNode(((ConstantNode)(((OperatorNode)tree).getLeft())).getNumber()*
								((ConstantNode)(((OperatorNode)tree).getRight())).getNumber());
				}
				
			}
			
			if(((OperatorNode)tree).getLeft().getType() == NodeType.VARIABLE && ((OperatorNode)tree).getRight().getType() == NodeType.VARIABLE){
				if(((IdentifierNode)(((OperatorNode)tree).getLeft())).getVar().
						equals(((IdentifierNode)(((OperatorNode)tree).getRight())).getVar())){
					if(((OperatorNode)tree).getOp() == '+')
						return new OperatorNode('*', new ConstantNode(2),new IdentifierNode(((IdentifierNode)(((OperatorNode)tree).getLeft())).getVar()));
					if(((OperatorNode)tree).getOp() == '-')
						return new ConstantNode(0);
						
					else if(((OperatorNode)tree).getOp() == '/')
						return new ConstantNode(1);
						
					else if(((OperatorNode)tree).getOp() == '*')
						return new OperatorNode('^', 
							new IdentifierNode(((IdentifierNode)(((OperatorNode)tree).getLeft())).getVar()),
							new ConstantNode(2));
				}
				
			}
			if(tree.getType() == NodeType.OPERATOR && ((OperatorNode)tree).getRight().getType() == NodeType.CONSTANT){
				if(((ConstantNode)(((OperatorNode)tree).getRight())).getNumber() == 0){	//�������� 0�϶�
					if(((OperatorNode)tree).getOp() == '+' || ((OperatorNode)tree).getOp() == '-')
						return ((OperatorNode)tree).getLeft();
					if(((OperatorNode)tree).getOp() == '*' || ((OperatorNode)tree).getOp() == '/')
						return new ConstantNode(0);
				}
				else if(((ConstantNode)(((OperatorNode)tree).getRight())).getNumber() == 1){	//�������� 1�϶�
					if(((OperatorNode)tree).getOp() == '*' || ((OperatorNode)tree).getOp() == '/')
						return ((OperatorNode)tree).getLeft();
				}
			}
			
			((OperatorNode)tree).setRight(simplified(((OperatorNode)tree).getRight()));	//���ʳ�� ���� ���� ��ȸ
			
			if(((OperatorNode)tree).getLeft().getType() != NodeType.OPERATOR && ((OperatorNode)tree).getRight().getType() != NodeType.OPERATOR){
				if(((OperatorNode)tree).getLeft().getType() == NodeType.CONSTANT && ((OperatorNode)tree).getRight().getType() == NodeType.CONSTANT)
					tree = simplified(tree);
					
				else if(((OperatorNode)tree).getLeft().getType() == NodeType.VARIABLE && ((OperatorNode)tree).getRight().getType() == NodeType.CONSTANT){
					if(((ConstantNode)(((OperatorNode)tree).getRight())).getNumber()<2)
						tree = simplified(tree);
				}
				else if(((OperatorNode)tree).getLeft().getType() == NodeType.CONSTANT && ((OperatorNode)tree).getRight().getType() == NodeType.VARIABLE){
					if(((ConstantNode)(((OperatorNode)tree).getLeft())).getNumber()<2)
						tree = simplified(tree);
				}
			}
			
			else if(((OperatorNode)tree).getLeft().getType() == NodeType.OPERATOR && ((OperatorNode)tree).getRight().getType() == NodeType.CONSTANT){
				if(((ConstantNode)((OperatorNode)tree).getRight()).getNumber()<2)
					tree = simplified(tree);
			}
			else if(((OperatorNode)tree).getLeft().getType() == NodeType.CONSTANT && ((OperatorNode)tree).getRight().getType() == NodeType.OPERATOR){
				if(((ConstantNode)(((OperatorNode)tree).getLeft())).getNumber()<2)
					tree = simplified(tree);
			}
		}
		return tree;
	}

	public void print() {
		print(root);
		System.out.println();
	}
	private void print(DifferentTreeNode tree) {
		if (tree != null) {
			if(tree.getType() == NodeType.OPERATOR){	//�����ڰ� ������ ( ���
				System.out.print("( ");
				print(((OperatorNode)tree).getLeft());
			}
			
			if(tree.getType() == NodeType.VARIABLE)	//������ ��
				System.out.print(((IdentifierNode)tree).getVar()+" ");
			
			else if(tree.getType() == NodeType.OPERATOR)	//�������� ��
				System.out.print(((OperatorNode)tree).getOp()+" ");
			
			else if(tree.getType() == NodeType.CONSTANT)	//����� ��
				System.out.print(((ConstantNode)tree).getNumber()+" ");
			
			if(tree.getType() == NodeType.OPERATOR){
				print(((OperatorNode)tree).getRight());
				System.out.print(") ");	//������ ��� ��� �� ) ���
			}
		}
	}
}