package de.sep.innovativeoperation.taskscheduler.service.validation;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.exception.validation.ValidationFailureException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNullException;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
/**
 * Valdiation service for IssueEntity
 * @author Stefan
 *
 */
@Service
public class IssueEntityValidationService implements AbstractGenericValidationService<IssueEntity>{


	
	public void checkObject(IssueEntity object) throws ValidationFailureException {
		checkIssueResolution(object.getIssueResolution());
		checkIssueStatus(object.getIssueStatus());
	}
	
	public void checkIssueResolution(IssueResolution issueResolution){
		if(issueResolution == null){
			throw new ValueIsNullException();
		}
	}
	
	public void checkIssueStatus(IssueStatus issueStatus){
		if(issueStatus == null){
			throw new ValueIsNullException();
		}
	}
	
	


}
