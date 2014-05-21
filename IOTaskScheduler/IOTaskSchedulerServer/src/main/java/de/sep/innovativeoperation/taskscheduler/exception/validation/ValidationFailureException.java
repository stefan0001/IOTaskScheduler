package de.sep.innovativeoperation.taskscheduler.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public abstract class ValidationFailureException extends RuntimeException {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1084329371641196868L;

	/**
	 * 
	 */
	public ValidationFailureException() {
		super();
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValidationFailureException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidationFailureException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 */
	public ValidationFailureException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public ValidationFailureException(Throwable cause) {
		super(cause);
		
	}


}
