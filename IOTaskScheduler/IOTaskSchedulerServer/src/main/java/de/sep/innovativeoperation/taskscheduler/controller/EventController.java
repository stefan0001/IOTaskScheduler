package de.sep.innovativeoperation.taskscheduler.controller;

import static de.sep.innovativeoperation.taskscheduler.config.Config.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.sep.innovativeoperation.taskscheduler.model.resource.EventResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTaskResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTasksResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventsResource;
import de.sep.innovativeoperation.taskscheduler.service.event.EventResourceService;
import de.sep.innovativeoperation.taskscheduler.service.eventtask.EventTaskResourceService;

@Controller
@RequestMapping(value = "/event")
public class EventController {
	//RESOURCE SERVICE
	@Autowired
	EventResourceService eventResourceService;
	
	@Autowired
	EventTaskResourceService eventTaskResourceService;

	/**
	 * get all events
	 * @return EventsResource
	 */
	@RequestMapping(method = RequestMethod.GET, produces = JSON)
	public @ResponseBody EventsResource getEvents(){
		return eventResourceService.getAll();
	}
	
	
	/**
	 * get one event
	 * @param id  	id of the event
	 * @return EventResource
	 */
	@RequestMapping(value = "/{eventid}", method = RequestMethod.GET, produces = JSON)
	public @ResponseBody EventResource getEvent(@PathVariable("eventid") int id){
		return eventResourceService.getById(id);
	}
	
	/**
	 * create a new event
	 * @param eventResource the event with the raw data
	 * @return EventResource with the saved Event from database
	 */
	@RequestMapping( method = RequestMethod.POST, produces = JSON)
	public @ResponseBody EventResource createEvent(@RequestBody EventResource eventResource){
		return eventResourceService.createEvent(eventResource);
	}
	
	
	/**
	 * delete the event with the given id
	 * @param id	id of the event
	 * @return
	 */
	@RequestMapping(value = "/{eventid}", method = RequestMethod.DELETE, produces = JSON)
	public @ResponseBody void deleteEvent(@PathVariable("eventid") int id){
		eventResourceService.deleteById(id);
	}
	
	
	/**
	 * Update the event with the given id
	 * @param id eventid
	 * @param eventResource	The EventResource the the raw data
	 * @return EventResource with the updated event from database
	 */
	@RequestMapping(value = "/{eventid}", method = RequestMethod.PUT, produces = JSON)
	public @ResponseBody EventResource updateEvent(@PathVariable("eventid") int id, @RequestBody EventResource eventResource){
		return eventResourceService.updateEvent(id, eventResource);
	}
	
	
	/**
	 * Trigger a Event
	 * @param id EventId
	 * @return OK the event got triggered
	 */
	@RequestMapping(value = "/{eventid}/trigger", method = RequestMethod.PUT, produces = JSON)
	public @ResponseBody void triggerEvent(@PathVariable("eventid") int id){
		eventResourceService.triggerEvent(id);
	}
	
	
	/**
	 * Get all EventTasks for a event
	 * @param id EventId
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/{eventid}/eventtask", method = RequestMethod.GET, produces = JSON)
	public @ResponseBody EventTasksResource getAllEventTasksForEvent(@PathVariable("eventid") int id){
		return eventTaskResourceService.getAllEventTasksForEvent(id);
	}
	
	
	/**
	 * Creating a new EventTask
	 * @param eventTaskResource	EventTaskResource containing the information for the new EventTask
	 * @return EventTaskResource containing the new EventTask
	 */
	@RequestMapping(value = "/{eventid}/eventtask", method = RequestMethod.POST, produces = JSON)
	public @ResponseBody EventTaskResource createEventTask(@PathVariable("eventid") int id, @RequestBody EventTaskResource eventTaskResource) {
		return eventTaskResourceService.createEventTask(id, eventTaskResource);
	}

}
