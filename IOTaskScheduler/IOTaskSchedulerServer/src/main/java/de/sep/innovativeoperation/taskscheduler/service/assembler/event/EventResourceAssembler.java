package de.sep.innovativeoperation.taskscheduler.service.assembler.event;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.controller.EventController;
import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventResource;
import de.sep.innovativeoperation.taskscheduler.service.assembler.generic.AbstractGenericDataResourceAssembler;

/**
 * Converts Event to Event
 * @author stefan
 *
 */
@Service
public class EventResourceAssembler extends AbstractGenericDataResourceAssembler<Event, EventResource> {

	public EventResourceAssembler() {
		super(EventController.class,EventResource.class);
	}

	@Override
	public EventResource toResource(Event entity) {
		EventResource resource = new EventResource(entity);
		
		resource.add(linkTo(methodOn(EventController.class).getEvent(entity.getId())).withSelfRel());
		return resource;
	}

}
