package de.sep.innovativeoperation.taskscheduler.service.trigger;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityDataService;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TimeTaskTrigger {

	@Autowired 
	IssueEntityDataService issueEntityDataService;
	
	/**
	 * Creates Issues for time tasks
	 * @param timeTask which Issues shall be created
	 */
	public void trigger(TimeTask timeTask){
		
		Set<IssueDraft> issueDrafts = timeTask.getIssueDrafts();
		Iterator<IssueDraft> iterator = issueDrafts.iterator();
		
		while(iterator.hasNext()){
			IssueDraft current = iterator.next();
			createIssueFor(current);
		}
		
		//plus 1 to the fire counter
		timeTask.setFireCount(timeTask.getFireCount()+1);
		
	}
	
	private IssueEntity createIssueFor(IssueDraft issueDraft){
		IssueEntity issueEntity = new IssueEntity(IssueStatus.NEW,IssueResolution.UNRESOLVED, null);
		return issueEntityDataService.createIssueEntity(issueDraft.getId(), issueEntity);
	}
	
}



