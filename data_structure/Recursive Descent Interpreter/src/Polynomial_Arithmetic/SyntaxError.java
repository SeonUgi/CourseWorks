package Polynomial_Arithmetic;

@SuppressWarnings("serial")
public class SyntaxError extends Exception {
	public SyntaxError() {
		super("Wrong statement end symbol!");
	}
	public SyntaxError(String msg) {
		super(msg);
	}
}
