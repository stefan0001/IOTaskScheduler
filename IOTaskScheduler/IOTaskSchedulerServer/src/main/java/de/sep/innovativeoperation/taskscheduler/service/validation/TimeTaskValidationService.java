package de.sep.innovativeoperation.taskscheduler.service.validation;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.exception.validation.ValidationFailureException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNotValidException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNullException;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;

/**
 * Validationservice for TimeTask
 * @author Stefan
 *
 */
@Service
public class TimeTaskValidationService implements AbstractGenericValidationService<TimeTask> {

	
	public void checkObject(TimeTask object) throws ValidationFailureException {
		this.checkTaskName(object.getName());
		this.checkFireTime(object.getFirstFireTime());
		this.checkIntervall(object.getIntervall());
	}

	public void checkTaskName(String taskName) {
		if (taskName == null) {
			throw new ValueIsNullException();
		}
		if(taskName.length() > 100){
			throw new ValueIsNotValidException();
		}
	}

	public void checkFireTime(Calendar fireTime) {
		if (fireTime == null) {
			throw new ValueIsNullException();
		}
	}

	public void checkIntervall(int intervall) {
		if (intervall < 3600) {
			throw new ValueIsNotValidException();
		}
	}

}
