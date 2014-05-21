package de.sep.innovativeoperation.taskscheduler.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CouldNotMapRequestParamException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4034738185633329619L;

}
