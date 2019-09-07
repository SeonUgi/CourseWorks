package polynomial_arithmetic;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Interpreter{
	private LinkedList<Element> result;		//결과 저장
	private LinkedList<Element> list;		//식 임시저장
	private LinkedList<Element> source1;	//다항식 1
	private LinkedList<Element> source2;	//다항식 2
	private StreamTokenizer in; 
	private char op;						//마지막 출력시 두 식 사이의 operator 
	public Element element = null;			//지수 와 차수 저장을 위한 class

	public Interpreter(StreamTokenizer in) throws IOException{
		this.in = in;
		in.ordinaryChar('/');	// '/'는 주석문을 구성하는 문자로 여겨지므로 
		// 이것을 벗어나 누기 문자로 사용하기 위함
		in.ordinaryChar('-');	// a-b는 하나의 변수로 볼 수 있으므로 이것을 
		// 벗어나 빼기 문자로 사용하기 위함
		in.ordinaryChar('x');
	}
	
	public void printSymbolTable(PrintStream out) {	//식 연산 입력과 결과 모두 출력
		out.println("("+strAppend(source1)+") "+ op +
				" ("+strAppend(source2)+')' +" = " + strAppend(result));
		
	}
	
	//expression ::= polynomial operator polynomial
	public void Expression() throws IOException, SyntaxError{
		source1 = Polynomial();
		in.nextToken();
		switch(in.ttype){	//두 식사이의 연산을 operator에 맞춰 result에 저장
		case '+':
			source2 = Polynomial();
			result = Arithmetic(source1,'+',source2);
			op='+';			//마지막 출력때 사용할 operater
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
		
		Collections.sort(result, new Comparator<Element>(){	//지수에 크기에 따른 정렬을
			@Override
			public int compare(Element o1, Element o2) {
				return o2.getPower()-o1.getPower();
			}
		});
	}
	
	//polynomial ::= '(' term { plus-or-minus term }0+ ')'
	//다항식을 LinkedList에 삽입
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
		if(check!=2)	//괄호의 쌍이 맞지 않을 시 error
			throw new SyntaxError();
		
		cleaning(list);	//지수가 같은 항은 더해준다
		Collections.sort(list, new Comparator<Element>(){	//정렬
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
	
	//두 다항식 사이의 연산
	public LinkedList<Element> Arithmetic(LinkedList<Element> source1,char op,LinkedList<Element> source2){
		LinkedList<Element> result = new LinkedList<Element>();
		if(op == '+' || op == '-'){		//두 식을 result리스트에 집어넣는다. -의 경우 식을 모두 -를 곱해 준 후 저장한다
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
		
		else if(op == '*'){		//각 항끼리 모두 곱한 후 result에 저장
			for(int i=0;i<source1.size();i++){
				for(int j=0;j<source2.size();j++){
					int coefficient = source1.get(i).getCoef()*source2.get(j).getCoef();
					int power = source1.get(i).getPower()+source2.get(j).getPower();
					Element element = new Element(coefficient,power);
					result.add(element);
				}
			}
		}
		
		cleaning(result);	//지수가 같은 항 더해서 제거
		return result;
	}
	
	/*지수가 같은 항 제거
	 * 우선 제일 앞의 객체를 tmp에 저장
	 * 그후 리스트에 지수가 같은 객체를 빼서 tmp와 더함
	 * tmp를 리스트 제일 마지막에 넣는다.
	 * 그렇게 리스트를 한바뀌 돈다
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
	
	//다항식의 각 항들을 하나의 string으로 만들어준다.
	//리스트가 비어잇을 시 0으로 출력해준다.
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
