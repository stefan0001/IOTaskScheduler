package de.sep.innovativeoperation.taskscheduler.service.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventsResource;
import de.sep.innovativeoperation.taskscheduler.service.AbstractGenericResourceService;
import de.sep.innovativeoperation.taskscheduler.service.assembler.event.EventResourceAssembler;

@Service
@Transactional
public class EventResourceService extends AbstractGenericResourceService<Event, EventResource, EventsResource>{

	//DATA SERVICE
	@Autowired
	EventDataService eventDataService;
	
	
	//ASSEMBLER
	@Autowired
	EventResourceAssembler eventResourceAssembler;
	

	
	/**
	 * Create a new Event
	 * @param EventResource
	 * @return EventResource with eventfrom database
	 */
	public EventResource createEvent(EventResource eventResource) {
		Event event = eventDataService.createEvent(eventResource.getContent());
		return eventResourceAssembler.toResource(event);

	}



	/**
	 * update a event with a given id
	 * @param id 	id of the event
	 * @param event the update data
	 * @return EventResource with updated event from database
	 */
	public EventResource updateEvent(int id, EventResource eventResource) {
		Event event = eventDataService.updateEvent(id, eventResource.getContent());
		return eventResourceAssembler.toResource(event);
	}
	
	/**
	 * Trigger all EventTask related to this event
	 * @param eventResource
	 */
	public void triggerEvent(int id){
		eventDataService.trigger(id);
	}
}
