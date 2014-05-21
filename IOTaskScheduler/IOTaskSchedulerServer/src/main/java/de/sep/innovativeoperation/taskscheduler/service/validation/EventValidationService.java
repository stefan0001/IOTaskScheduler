package de.sep.innovativeoperation.taskscheduler.service.validation;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.exception.validation.ValidationFailureException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNotValidException;
import de.sep.innovativeoperation.taskscheduler.model.data.Event;

@Service
public class EventValidationService implements AbstractGenericValidationService<Event>{

	@Override
	public void checkObject(Event object) throws ValidationFailureException {
		this.checkName(object.getName());
		
	}
	
	public void checkName(String name){
		if(name == null){
			throw new ValueIsNotValidException();
		}
		
		if(name.length() > 100){
			throw new ValueIsNotValidException();
		}
			
	}

}
