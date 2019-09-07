package polynomial_arithmetic;

public class SyntaxError extends Exception {
	private static final long serialVersionUID = 1792907010635380819L;
	public SyntaxError() {
		super("Error in Statement");
	}
	public SyntaxError(String msg) {
		super(msg);
	}
}
