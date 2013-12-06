package gerenciador.exception;


public class RNException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7375044365114440708L;


	public RNException() {
		
	}

	public RNException(String message) {
		super(message);
		
	}

	public RNException(Throwable cause) {
		super(cause);
		
	}

	public RNException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
