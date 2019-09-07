package polynomial_arithmetic;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Interpreter{
	private LinkedList<Element> result;		//��� ����
	private LinkedList<Element> list;		//�� �ӽ�����
	private LinkedList<Element> source1;	//���׽� 1
	private LinkedList<Element> source2;	//���׽� 2
	private StreamTokenizer in; 
	private char op;						//������ ��½� �� �� ������ operator 
	public Element element = null;			//���� �� ���� ������ ���� class

	public Interpreter(StreamTokenizer in) throws IOException{
		this.in = in;
		in.ordinaryChar('/');	// '/'�� �ּ����� �����ϴ� ���ڷ� �������Ƿ� 
		// �̰��� ��� ���� ���ڷ� ����ϱ� ����
		in.ordinaryChar('-');	// a-b�� �ϳ��� ������ �� �� �����Ƿ� �̰��� 
		// ��� ���� ���ڷ� ����ϱ� ����
		in.ordinaryChar('x');
	}
	
	public void printSymbolTable(PrintStream out) {	//�� ���� �Է°� ��� ��� ���
		out.println("("+strAppend(source1)+") "+ op +
				" ("+strAppend(source2)+')' +" = " + strAppend(result));
		
	}
	
	//expression ::= polynomial operator polynomial
	public void Expression() throws IOException, SyntaxError{
		source1 = Polynomial();
		in.nextToken();
		switch(in.ttype){	//�� �Ļ����� ������ operator�� ���� result�� ����
		case '+':
			source2 = Polynomial();
			result = Arithmetic(source1,'+',source2);
			op='+';			//������ ��¶� ����� operater
			break;
			
		case '-':
			source2 = Polynomial();
			result = Arithmetic(source1,'-',source2);
			op='-';
			break;
			
		case '*':
			source2 = Polynomial();
			result = Arithmetic(source1,'*',source2);
			op='*';
			break;
			
		default:
			throw new SyntaxError();
		}
		
		Collections.sort(result, new Comparator<Element>(){	//������ ũ�⿡ ���� ������
			@Override
			public int compare(Element o1, Element o2) {
				return o2.getPower()-o1.getPower();
			}
		});
	}
	
	//polynomial ::= '(' term { plus-or-minus term }0+ ')'
	//���׽��� LinkedList�� ����
	public LinkedList<Element> Polynomial() throws IOException, SyntaxError{
		list = new LinkedList<Element>();
		int check = 0;
		while(true){
			if(in.ttype == '(')
				check++;
			in.nextToken();
			if(in.ttype == '+' || in.ttype == StreamTokenizer.TT_NUMBER){
				if(in.ttype == StreamTokenizer.TT_NUMBER)
					in.pushBack();
				list.add(term('+'));
			}
			
			else if(in.ttype == '-' || in.ttype == StreamTokenizer.TT_NUMBER){
				if(in.ttype == StreamTokenizer.TT_NUMBER)
					in.pushBack();
				list.add(term('-'));
			}
			
			if(in.ttype == ')'){
				check++;
				break;
			}
		}
		if(check!=2)	//��ȣ�� ���� ���� ���� �� error
			throw new SyntaxError();
		
		cleaning(list);	//������ ���� ���� �����ش�
		Collections.sort(list, new Comparator<Element>(){	//����
			@Override
			public int compare(Element o1, Element o2) {
				return o2.getPower()-o1.getPower();
			}
		});
		return list;
	}
	
	//term ::= term-with-x | term-without-x
	public Element term(char op) throws IOException, SyntaxError{
		int coefficient = 0;
		int power = 0;
		
		while(true){
			in.nextToken();
			if(in.ttype == StreamTokenizer.TT_NUMBER){
				if(op=='-')
					coefficient= -(int) in.nval;
				else
					coefficient= (int) in.nval;
			}
			
			else if(in.ttype == 'x'){
				in.nextToken();	
				if(in.ttype == '+' || in.ttype == '-' || in.nval == 1){
					power = 1;
					in.pushBack();
					return new Element(coefficient,power);
				}
				else if(in.ttype == StreamTokenizer.TT_NUMBER){
					power=(int) in.nval;
					return new Element(coefficient,power);
				}
			}
			
			else if(in.ttype == '+' || in.ttype == '-'){
				in.pushBack();
				return new Element(coefficient);
			}
			else if(in.ttype == ')')
				return new Element(coefficient);
			
			else
				throw new SyntaxError();
		}
	}
	
	//�� ���׽� ������ ����
	public LinkedList<Element> Arithmetic(LinkedList<Element> source1,char op,LinkedList<Element> source2){
		LinkedList<Element> result = new LinkedList<Element>();
		if(op == '+' || op == '-'){		//�� ���� result����Ʈ�� ����ִ´�. -�� ��� ���� ��� -�� ���� �� �� �����Ѵ�
			for(int i=0;i<source1.size();i++)
				result.add(new Element(source1.get(i).getCoef(),source1.get(i).getPower()));
			for(int i=0;i<source2.size();i++){
				if(op=='+')
					result.add(new Element(source2.get(i).getCoef(),source2.get(i).getPower()));
				else{
					result.add(new Element(-source2.get(i).getCoef(),source2.get(i).getPower()));
				}
			}
		}
		
		else if(op == '*'){		//�� �׳��� ��� ���� �� result�� ����
			for(int i=0;i<source1.size();i++){
				for(int j=0;j<source2.size();j++){
					int coefficient = source1.get(i).getCoef()*source2.get(j).getCoef();
					int power = source1.get(i).getPower()+source2.get(j).getPower();
					Element element = new Element(coefficient,power);
					result.add(element);
				}
			}
		}
		
		cleaning(result);	//������ ���� �� ���ؼ� ����
		return result;
	}
	
	/*������ ���� �� ����
	 * �켱 ���� ���� ��ü�� tmp�� ����
	 * ���� ����Ʈ�� ������ ���� ��ü�� ���� tmp�� ����
	 * tmp�� ����Ʈ ���� �������� �ִ´�.
	 * �׷��� ����Ʈ�� �ѹٲ� ����
	 */
	public void cleaning(LinkedList<Element> list){
		for(int i=0;i<list.size();i++){
			Element tmp = list.poll();
			for(int j=0;j<list.size();j++){
				if(tmp.getPower()==list.get(j).getPower())
					tmp.setCoef(tmp.getCoef()+list.remove(j).getCoef());
			}
			if(tmp.getCoef()!=0)
				list.addLast(tmp);
		}
	}
	
	//���׽��� �� �׵��� �ϳ��� string���� ������ش�.
	//����Ʈ�� ������� �� 0���� ������ش�.
	public String strAppend(LinkedList<Element> list){
		StringBuilder sb = new StringBuilder();
		if(list.size() == 0)
			sb.append("0");
		for(int i=0;i<list.size();i++){
			if(i != 0 && list.get(i).getCoef()>0)
				sb.append("+");
		
			sb.append(list.get(i).toString());
			if(i != list.size()-1)
				sb.append(" ");
		}
		return sb.toString();
	}
}
