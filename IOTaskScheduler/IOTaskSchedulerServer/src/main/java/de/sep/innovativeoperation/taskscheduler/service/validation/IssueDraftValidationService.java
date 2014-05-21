package de.sep.innovativeoperation.taskscheduler.service.validation;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.exception.validation.ValidationFailureException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNotValidException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNullException;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;

/**
 * Service to Validate a IssueDraft
 * @author Stefan
 *
 */
@Service
public class IssueDraftValidationService implements AbstractGenericValidationService<IssueDraft> {
	
	
	public void checkObject(IssueDraft object) throws ValidationFailureException {
		checkIssueName(object.getIssueName());
		checkIssueDescription(object.getIssueDescription());
		checkIssueType(object.getIssueType());
		
	}
	
	public void checkIssueName(String issueName){
		if (issueName == null) {
			throw new ValueIsNullException();
		}
		if(issueName.length() > 100){
			throw new ValueIsNotValidException();
		}
	}
	
	public void checkIssueDescription(String issueDescription){
		if (issueDescription == null) {
			throw new ValueIsNullException();
		}
		if(issueDescription.length() > 500){
			throw new ValueIsNotValidException();
		}
	}
	
	public void checkIssueType(IssueType issueType){
		if (issueType == null) {
			throw new ValueIsNullException();
		}
	}
	


}
