package de.sep.innovativeoperation.taskscheduler.service.trigger;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityDataService;


@Service
@Transactional
public class EventTaskTrigger {
	
	@Autowired 
	IssueEntityDataService issueEntityDataService;
	/**
	 * Creates Issues for time tasks
	 * @param timeTask which Issues shall be created
	 */
	public void trigger(EventTask eventTask){
		
		Set<IssueDraft> issueDrafts = eventTask.getIssueDrafts();
		Iterator<IssueDraft> iterator = issueDrafts.iterator();
		
		while(iterator.hasNext()){
			IssueDraft current = iterator.next();
			createIssueFor(current);
		}
	}
	
	private IssueEntity createIssueFor(IssueDraft issueDraft){
		IssueEntity issueEntity = new IssueEntity(IssueStatus.NEW,IssueResolution.UNRESOLVED, null);
		return issueEntityDataService.createIssueEntity(issueDraft.getId(), issueEntity);

	}
	
	
}
