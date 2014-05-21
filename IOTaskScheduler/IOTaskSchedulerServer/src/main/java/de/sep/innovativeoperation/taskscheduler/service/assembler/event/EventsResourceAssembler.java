package de.sep.innovativeoperation.taskscheduler.service.assembler.event;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.controller.EventController;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventsResource;
import de.sep.innovativeoperation.taskscheduler.service.assembler.generic.AbstractGenericDataResourcesAssembler;

@Service
public class EventsResourceAssembler extends AbstractGenericDataResourcesAssembler<EventResource, EventsResource>{

	public EventsResourceAssembler() {
		super(EventController.class,EventsResource.class);
		
	}

	@Override
	public EventsResource toResource(Iterable<EventResource> entity) {
		EventsResource resource = new EventsResource(entity);
		
		resource.add(linkTo(methodOn(EventController.class).getEvents()).withSelfRel());
		return resource;
	}

}
