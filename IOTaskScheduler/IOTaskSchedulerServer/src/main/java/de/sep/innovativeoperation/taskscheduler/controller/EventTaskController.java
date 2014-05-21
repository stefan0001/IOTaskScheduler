package de.sep.innovativeoperation.taskscheduler.controller;

import static de.sep.innovativeoperation.taskscheduler.config.Config.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.sep.innovativeoperation.taskscheduler.model.resource.EventTaskResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTasksResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftsResource;
import de.sep.innovativeoperation.taskscheduler.service.eventtask.EventTaskResourceService;

@Controller
@RequestMapping(value = "/eventtask")
public class EventTaskController {

	@Autowired
	private EventTaskResourceService eventTaskResourceService;
	
	
	/**
	 * Load all EventTasks
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = JSON)
	public @ResponseBody EventTasksResource getAllEventTasks() {
		return eventTaskResourceService.getAll();
	}

	/**
	 * Load one EventTask
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{eventtaskid}", method = RequestMethod.GET, produces = JSON)
	public @ResponseBody EventTaskResource getOneEventTask(@PathVariable("eventtaskid") int id) {
		return eventTaskResourceService.getById(id);
	}
	
	
	/**
	 * Update a EventTask
	 * @param id	id of the EventTask
	 * @param eventTaskResource	EventTaskResource containing the information for the update
	 * @return EventTaskResource containing the event with all updated informations
	 */
	@RequestMapping(value = "/{timetaskid}", method = RequestMethod.PUT, produces = JSON)
	public @ResponseBody EventTaskResource updateEventTask(@PathVariable("timetaskid") int id, @RequestBody EventTaskResource eventTaskResource) {
		return eventTaskResourceService.updateEventTask(id, eventTaskResource);
	}
	
	
	/**
	 * Delete a EventTask
	 * @param id	id of the eventTask
	 */
	@RequestMapping(value = "/{eventtaskid}", method = RequestMethod.DELETE, produces = JSON)
	public @ResponseBody void deleteEventTask(@PathVariable("eventtaskid") int id) {
		eventTaskResourceService.deleteById(id);
	}
	
	
	
	
	
	/**
	 * get all IssueDrafts for the EventTask with the id
	 * @param id
	 * @return IssueDraftResource
	 */
	@RequestMapping(value="/{eventtaskid}/issuedraft",method = RequestMethod.GET, produces = JSON)
	public @ResponseBody IssueDraftsResource getIssueDraftsforEventTask(@PathVariable("eventtaskid") int id){
		return eventTaskResourceService.getIssueDraftsforEventTask(id);
	}
	
	
	

	
	/**
	 * create a relation between a eventtask and a issuedraft
	 * for id of issueDraft == 0 a new issuedraft is created
	 * @param eventTaskId
	 * @param issueDraft
	 * @return 
	 */
	@RequestMapping(value="/{eventtaskid}/issuedraft",method = RequestMethod.POST, produces = JSON)
	public @ResponseBody IssueDraftResource createRelationEventTaskIssueDraft(@PathVariable("eventtaskid") int eventTaskId, @RequestBody IssueDraftResource issueDraftResource){
		return eventTaskResourceService.createRelationEventTaskIssueDraft(eventTaskId, issueDraftResource);
	}
	
	
	/**
	 * delete a relation between a task and a issuedraft
	 * @param id                  id of the task
	 * @param issueDraftResource  the issuedraft with the issuedraftid
	 * @return IssueDraftResource
	 */
	@RequestMapping(value="/{eventtaskid}/issuedraft/{issuedraftid}",method = RequestMethod.DELETE, produces = JSON)
	public @ResponseBody void deleteRelationEventTaskIssueDraft(@PathVariable("eventtaskid") int eventTaskId, @PathVariable("issuedraftid") int issueDraftId){
		eventTaskResourceService.deleteRelationEventTaskIssueDraft(eventTaskId, issueDraftId);
	}
	

	
	

	
	
	
}
