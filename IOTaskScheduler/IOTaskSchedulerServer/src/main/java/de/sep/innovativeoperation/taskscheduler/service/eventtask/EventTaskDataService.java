package de.sep.innovativeoperation.taskscheduler.service.eventtask;

import java.util.Iterator;
import java.util.Set;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.dao.EventTaskDAO;
import de.sep.innovativeoperation.taskscheduler.exception.http.ResourceNotFoundException;
import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.service.AbstractGenericDataService;
import de.sep.innovativeoperation.taskscheduler.service.event.EventDataService;
import de.sep.innovativeoperation.taskscheduler.service.issuedraft.IssueDraftDataService;
import de.sep.innovativeoperation.taskscheduler.service.validation.EventTaskValidationService;

@Service
@Transactional
public class EventTaskDataService extends AbstractGenericDataService<EventTask> {

	
	//SERVICES
	@Autowired
	private EventTaskValidationService eventTaskValidationService;
	
	@Autowired
	private EventDataService eventDataService;
	
	@Autowired
	private IssueDraftDataService issueDraftDataService;
	
	//DAO
	@Autowired
	private EventTaskDAO eventTaskDAO;
	
	
	/**
	 * Create a new EventTask
	 * @param eventid  id of the event
	 * @param EventTask 
	 * @return EventTask from database
	 */
	public EventTask createEventTask(int eventid, EventTask eventTask) {
		Event event = eventDataService.getById(eventid);
		
		// set id to 0 to tell the database it should be a new entity
		eventTask.setId(0);
		
		eventTask.setEvent(event);

		eventTaskValidationService.checkObject(eventTask);
		EventTask savedEventTask = eventTaskDAO.save(eventTask);
		
		//assign bidirectional relation
		event.getEventTasks().add(savedEventTask);
		return savedEventTask;
	}
	



	/**
	 * update a EventTask with a given id
	 * @param id 	id of the EventTask
	 * @param EventTask the update data
	 * @return EventTask updated from database
	 */
	public EventTask updateEventTask(int id, EventTask eventTask) {

		eventTaskValidationService.checkObject(eventTask); 

		// search for object
		EventTask eventTaskDB = eventTaskDAO.findById(id);
		
		//update object
		eventTaskDB.setName(eventTask.getName());
		
		return eventTaskDB;
	}
	
	
	
	/**
	 * get all IssueDrafts for the EventTask with the id
	 * @param id 	id of the EventTask
	 * @return
	 */
	public Set<IssueDraft> getIssueDraftsforEventTask(int id){
		EventTask eventTask = this.getById(id);
		return eventTask.getIssueDrafts();
	}
	
	/**
	 * create a relation between a EventTask and a issuedraft
	 * for id of issueDraft == 0 a new issuedraft is created
	 * @param eventTaskId
	 * @param issueDraft
	 * @return
	 */
	public IssueDraft createRelationEventTaskIssueDraft(int eventTaskId, IssueDraft issueDraft){
		EventTask eventTaskFromDB = this.getById(eventTaskId);
		IssueDraft issueDraftFromDB;
		
		if(issueDraft.getId() == 0){
			//for id == 0 create a new issuedraft
			issueDraftFromDB = issueDraftDataService.createIssueDraft(issueDraft);
		} else {
			//else try to finde the issuedraft
			issueDraftFromDB = issueDraftDataService.getById(issueDraft.getId());
		}
		
			
		return createRelationEventTaskIssueDraft(eventTaskFromDB, issueDraftFromDB);
		
	}
	
	
	/**
	 * create a relation between a EventTask and a issuedraft
	 * @param eventTaskId
	 * @param issueDraftId
	 */
	private IssueDraft createRelationEventTaskIssueDraft(EventTask eventTask, IssueDraft issueDraft){
		
		if(!eventTask.getIssueDrafts().contains(issueDraft) ){
			eventTask.getIssueDrafts().add(issueDraft);
			//assign bidirectional relation
			issueDraft.getEventTasks().add(eventTask);
		}
		return issueDraft;
	}
	
	
	
	/**
	 * delete a relation between a EventTask and a issuedraft
	 * @param eventTaskId
	 * @param issueDraftId
	 */
	public void deleteRelationEventTaskIssueDraft(int eventTaskId, int issueDraftId){
		EventTask eventTask = this.getById(eventTaskId);
		IssueDraft issueDraft = issueDraftDataService.getById(issueDraftId);
		
		
		if(!eventTask.getIssueDrafts().contains(issueDraft) ){
			throw new ResourceNotFoundException();
		}
		eventTask.getIssueDrafts().remove(issueDraft);
		
		//remove bidirectional relation
		issueDraft.getEventTasks().remove(eventTask);
	}
	
	/**
	 * get all EventTasks for a given Event id
	 * @param id id of the event
	 * @return
	 */
	public Set<EventTask> getAllEventTasksForEvent(int id){
		Event event = eventDataService.getById(id);
		return event.getEventTasks();
	}




	@Override
	public void removeBidirctionalRelations(EventTask entity) {
		
		Iterator<IssueDraft> iteratorIssueDrafts = entity.getIssueDrafts().iterator();
		while(iteratorIssueDrafts.hasNext() ){
			iteratorIssueDrafts.next().getEventTasks().remove(entity);
		}
		
		entity.getEvent().getEventTasks().remove(entity);
		

		
	}
	
	
}
