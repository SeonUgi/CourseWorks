package PostfixConversion;

import java.util.Stack;
import java.util.regex.Pattern;

public class InfixToPostfixConversion{
	String[] formula;
	Stack<String> stack = new Stack<String>();
	
	InfixToPostfixConversion(){
		formula = null;
	}
	
	InfixToPostfixConversion(String infix){
		formula=infix.split(" ");
	}
	
	private static int operatorPriority(String operator) {	//연산자 우선순위 판정
        if(operator.equals("(")) 
        	return 0;
        if(operator.equals("+") || operator.equals("-")) 
        	return 1;
        if(operator.equals("*") || operator.equals("/")) 
        	return 2;
        return 3;
    }
	
	private static boolean isOperator(String ch) {	//연산자인지 검사
        return (ch.equals("+")||ch.equals("-")||ch.equals("*")||ch.equals("/"));
    }
	
	private static boolean isNumeric(String str){	//숫자인지 검사
		return Pattern.matches("[0-9]+", str);
	}
	
	public String chageToPostfix() {	//중위 연산식 ==> 후위 연산식
		StringBuilder postfix = new StringBuilder();
		
		for(int i=0;i<formula.length;i++){
			if(formula[i].equals("(")){
				stack.push(formula[i]);
			}
			else if(formula[i].equals(")")){
				while(!stack.peek().equals("(")){
					postfix.append(stack.pop());
					postfix.append(" ");
				}
				stack.pop();
			}
			else if(isOperator(formula[i])){
				while(!stack.isEmpty()&&operatorPriority(stack.peek())>=operatorPriority(formula[i])){
					postfix.append(stack.pop());
					postfix.append(" ");
				}
				stack.push(formula[i]);
			}
			else if(isNumeric(formula[i])){
				postfix.append(formula[i]);
				postfix.append(" ");
			}
		}
		
		while(!stack.isEmpty()){
			postfix.append(stack.pop());
			postfix.append(" ");
		}
		
		return postfix.toString();
	}	

	public double evaluatePostfix(String postfixLine) {
		String[] postfix = postfixLine.split(" ");
		double result;
		
		for(int i=0;i<postfix.length;i++){
			if(isNumeric(postfix[i]))	//숫자일 경우
				stack.push(postfix[i]);
			
			else if(isOperator(postfix[i])){	//연산자의 경우 연산
				result=Double.parseDouble(stack.pop());
				if(postfix[i].equals("+"))
					result= Double.parseDouble(stack.pop())+result;
				else if(postfix[i].equals("-"))
					result= Double.parseDouble(stack.pop())-result;
				else if(postfix[i].equals("*"))
					result= Double.parseDouble(stack.pop())*result;
				else if(postfix[i].equals("/"))
					result= Double.parseDouble(stack.pop())/result;
				
				stack.push(Double.toString(result));
			}
		}
		
		return Double.parseDouble(stack.pop());
	}
}
