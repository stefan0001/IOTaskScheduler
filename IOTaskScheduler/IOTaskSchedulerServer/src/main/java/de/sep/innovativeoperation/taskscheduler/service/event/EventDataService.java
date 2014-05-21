package de.sep.innovativeoperation.taskscheduler.service.event;


import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.dao.EventDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.service.AbstractGenericDataService;
import de.sep.innovativeoperation.taskscheduler.service.trigger.EventTaskTrigger;
import de.sep.innovativeoperation.taskscheduler.service.validation.EventValidationService;

@Service
@Transactional
public class EventDataService extends AbstractGenericDataService<Event>{
	
	
	
	
	//SERVICES
	@Autowired
	private EventValidationService eventValidationService;
	
	//DAO
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private EventTaskTrigger eventTaskTrigger;
	
	
	/**
	 * Create a new Event
	 * @param Event 
	 * @return Event from database
	 */
	public Event createEvent(Event event) {
		// set id to 0 to tell the database it should be a new entity
		event.setId(0);

		eventValidationService.checkObject(event);
		return eventDAO.save(event);
	}



	/**
	 * update a event with a given id
	 * @param id 	id of the event
	 * @param event the update data
	 * @return event updated from database
	 */
	public Event updateEvent(int id, Event event) {

		eventValidationService.checkObject(event);

		// search for object
		Event eventDB = this.getById(id);
		
		//update object
		eventDB.setName(event.getName());
		
		return eventDB;
	}
	
	/**
	 * Trigger all EventTask related to this Event
	 * @param event
	 */
	public void trigger(int id){
		Event event = this.getById(id);
		Set<EventTask> eventTasks = event.getEventTasks();
		Iterator<EventTask> iterator = eventTasks.iterator();
		
		while(iterator.hasNext()){
			eventTaskTrigger.trigger(iterator.next());
		}
		
	}



	@Override
	public void removeBidirctionalRelations(Event entity) {
		
	}
	
	

}
