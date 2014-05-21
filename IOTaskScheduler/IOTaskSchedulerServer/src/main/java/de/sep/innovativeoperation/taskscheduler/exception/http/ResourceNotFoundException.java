package de.sep.innovativeoperation.taskscheduler.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = -7203864582500189404L;

}
