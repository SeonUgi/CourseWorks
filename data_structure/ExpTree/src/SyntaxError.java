public class SyntaxError extends Exception {
	private static final long serialVersionUID = 1L;
	public SyntaxError() {
		super("Error in Statement");
	}
	public SyntaxError(String msg) {
		super(msg);
	}
}
