package de.sep.innovativeoperation.taskscheduler.exception.validation;

public class ValueIsNotValidException extends ValidationFailureException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1069371965341602179L;

	/**
	 * 
	 */
	public ValueIsNotValidException() {
		super();
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValueIsNotValidException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValueIsNotValidException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 */
	public ValueIsNotValidException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public ValueIsNotValidException(Throwable cause) {
		super(cause);
		
	}

}
