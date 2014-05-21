package de.sep.innovativeoperation.taskscheduler.service.eventtask;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTaskResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTasksResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftsResource;
import de.sep.innovativeoperation.taskscheduler.service.AbstractGenericResourceService;
import de.sep.innovativeoperation.taskscheduler.service.assembler.eventtask.EventTaskResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.eventtask.EventTasksResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issuedraft.IssueDraftResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issuedraft.IssueDraftsResourceAssembler;

@Service
@Transactional
public class EventTaskResourceService extends AbstractGenericResourceService<EventTask, EventTaskResource, EventTasksResource>{
	//DATA SERVICE
	@Autowired
	private EventTaskDataService eventTaskDataService;
	
	//ASSEMBLER
	
	//EVENTTASK
	@Autowired
	private EventTaskResourceAssembler eventTaskResourceAssembler;
	@Autowired
	private EventTasksResourceAssembler eventTasksResourceAssembler;

	//ISSUEDRAFT
	@Autowired
	private IssueDraftResourceAssembler issueDraftResourceAssembler;
	@Autowired
	private IssueDraftsResourceAssembler issueDraftsResourceAssembler;
	
	
	/**
	 * Create a new EventTask
	 * @param eventid  id of the event
	 * @param eventTaskResource EventTaskResources containing the information for creating a new EventTask
	 * @return	EventTaskresource containing the new EventTask from the Database
	 */
	public EventTaskResource createEventTask(int eventid, EventTaskResource eventTaskResource) {
		EventTask createdEventTask = eventTaskDataService.createEventTask(eventid, eventTaskResource.getContent());
		return eventTaskResourceAssembler.toResource(createdEventTask);
	}

	/**
	 * Update a EventTask
	 * @param id	id of the event task
	 * @param eventTaskResource	EventTaskResources containing the information for the update
	 * @return the updated EventTaskResource
	 */
	public EventTaskResource updateEventTask(int id, EventTaskResource eventTaskResource) {
		EventTask updateEventTask = eventTaskDataService.updateEventTask(id, eventTaskResource.getContent());
		return eventTaskResourceAssembler.toResource(updateEventTask);
	}
	
	
	
	
	
	
	/**
	 * get all IssueDrafts for the EventTask with the id
	 * @param id 	id of the EventTask
	 * @return
	 */
	public IssueDraftsResource getIssueDraftsforEventTask(int id){
		//get issuedraft
		Set<IssueDraft> issueDrafts = eventTaskDataService.getIssueDraftsforEventTask(id);
		//generate resources
		List<IssueDraftResource> issueDraftResoruces = issueDraftResourceAssembler.toResources(issueDrafts);
		//generate resources to one resource
		return issueDraftsResourceAssembler.toResource(issueDraftResoruces);
		
	}
	
	
	/**
	 * create a relation between a eventtask and a issuedraft
	 * for id of issueDraft == 0 a new issuedraft is created
	 * @param eventTaskId
	 * @param issueDraft
	 */
	public IssueDraftResource createRelationEventTaskIssueDraft(int eventTaskId, IssueDraftResource issueDraftResource){
		 IssueDraft issueDraftResult = eventTaskDataService.createRelationEventTaskIssueDraft(eventTaskId, issueDraftResource.getContent());
		 IssueDraftResource newIssueDraftResource = issueDraftResourceAssembler.toResource(issueDraftResult);
		 
		 return newIssueDraftResource;
	}
	
	/**
	 * delete a relation between a eventtask and a issuedraft
	 * @param eventTaskId
	 * @param issueDraftId
	 */
	public void deleteRelationEventTaskIssueDraft(int eventTaskId, int issueDraftId){
		eventTaskDataService.deleteRelationEventTaskIssueDraft(eventTaskId, issueDraftId);
	}
	
	/**
	 * get all EventTasks for a given Event id
	 * @param id id of the event
	 * @return
	 */
	public EventTasksResource getAllEventTasksForEvent(int id){
		Set<EventTask> eventTasks = eventTaskDataService.getAllEventTasksForEvent(id);
		List<EventTaskResource> resources = eventTaskResourceAssembler.toResources(eventTasks);
		
		return eventTasksResourceAssembler.toResource(resources);
	}
	
	
}
