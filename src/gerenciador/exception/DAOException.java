package gerenciador.exception;


public class DAOException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4048971491137679426L;

	public DAOException() {
		
	}

	public DAOException(String message) {
		super(message);
		
	}

	public DAOException(Throwable cause) {
		super(cause);
		
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
