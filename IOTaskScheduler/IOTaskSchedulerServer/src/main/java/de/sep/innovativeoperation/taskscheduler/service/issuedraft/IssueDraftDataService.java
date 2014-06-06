package de.sep.innovativeoperation.taskscheduler.service.issuedraft;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.dao.IssueDraftDAO;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNullException;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.service.AbstractGenericDataService;
import de.sep.innovativeoperation.taskscheduler.service.validation.IssueDraftValidationService;

/**
 * Logic for IssueDraft management
 * 
 * @author Stefan
 * 
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class IssueDraftDataService extends AbstractGenericDataService<IssueDraft> {

	@Autowired
	private IssueDraftDAO issueDraftDAO;

	@Autowired
	private IssueDraftValidationService issueDraftValidationService;

	
	public IssueDraft createIssueDraft(IssueDraft issueDraft) {
		// set id to 0 to tell the database it should be a new entity
		issueDraft.setId(0);

		issueDraftValidationService.checkObject(issueDraft);
		return issueDraftDAO.save(issueDraft);
	}



	public IssueDraft updateIssueDraft(int id, IssueDraft issueDraft) {

		issueDraftValidationService.checkObject(issueDraft);

		// search for object
		IssueDraft issueDraftOld = this.getById(id);
		
		//update object
		issueDraftOld.setIssueName(issueDraft.getIssueName());
		issueDraftOld.setIssueDescription(issueDraft.getIssueDescription());
		issueDraftOld.setIssueType(issueDraft.getIssueType());

		
		return issueDraftOld;

	}
	

	/**
	 * load all IssueEntities for one IssueDraft
	 * 
	 * @param issueDraftId
	 * @return
	 */
	public Set<IssueEntity> getIssueEntitiesForIssueDraft(int issueDraftId) {
		IssueDraft issueDraft = this.getById(issueDraftId);
		
		Set<IssueEntity> issueEntities = issueDraft.getIssueEntities();

		
		return issueEntities;
	}
	
	/**
	 * get all TimeTasks for the IssueDraft with the id
	 * @param id 	id of the IssueDraft
	 * @return
	 */
	public Set<TimeTask> getTimeTasksForIssueDraft(int id){
		IssueDraft issueDraft = this.getById(id);
		return issueDraft.getTimeTasks();
	}

	
	public List<IssueDraft> filterIssueDraft(IssueDraft issueDraft) {
		// check values ignore null values

		try {
			issueDraftValidationService.checkIssueName(issueDraft
					.getIssueName());
		} catch (ValueIsNullException e) {
		}

		try {
			issueDraftValidationService.checkIssueDescription(issueDraft
					.getIssueDescription());
		} catch (ValueIsNullException e) {
		}

		try {
			issueDraftValidationService.checkIssueType(issueDraft
					.getIssueType());
		} catch (ValueIsNullException e) {
		}

		return issueDraftDAO.filterIssueDraft( issueDraft.getIssueName(),issueDraft.getIssueDescription(), issueDraft.getIssueType());

	}



	@Override
	public void removeBidirctionalRelations(IssueDraft entity) {
		//remove bidirectional relation to timetask
		Iterator<TimeTask> iteratorTimeTasks = entity.getTimeTasks().iterator();
		while(iteratorTimeTasks.hasNext() ){
			iteratorTimeTasks.next().getIssueDrafts().remove(entity);
		}
		
		//remove bidirectional relation to eventtask
		Iterator<EventTask> iteratorEventTasks = entity.getEventTasks().iterator();
		while(iteratorEventTasks.hasNext() ){
			iteratorEventTasks.next().getIssueDrafts().remove(entity);
		}
		
		
	}



}
