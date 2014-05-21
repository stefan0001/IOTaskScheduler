package de.sep.innovativeoperation.taskscheduler.exception.validation;

public class ValueIsNullException extends ValidationFailureException {

	/**
	 * 
	 */
	public ValueIsNullException() {
		super();
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValueIsNullException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValueIsNullException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 */
	public ValueIsNullException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public ValueIsNullException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9008791758652482295L;

}
