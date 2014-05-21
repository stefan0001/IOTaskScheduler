package de.sep.innovativeoperation.taskscheduler.service.validation;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.exception.validation.ValidationFailureException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNotValidException;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;


@Service
public class EventTaskValidationService implements AbstractGenericValidationService<EventTask> {
	
	@Override
	public void checkObject(EventTask object) throws ValidationFailureException {
		checkName(object.getName());
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
