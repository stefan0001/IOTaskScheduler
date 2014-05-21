package de.sep.innovativeoperation.taskscheduler.service.assembler.eventtask;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.controller.EventTaskController;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTaskResource;
import de.sep.innovativeoperation.taskscheduler.service.assembler.generic.AbstractGenericDataResourceAssembler;


@Service
public class EventTaskResourceAssembler extends AbstractGenericDataResourceAssembler<EventTask, EventTaskResource>{

	public EventTaskResourceAssembler() {
		super(EventTaskController.class, EventTaskResource.class);
	}

	@Override
	public EventTaskResource toResource(EventTask entity) {
		EventTaskResource resource = new EventTaskResource(entity, new EventResource(entity.getEvent()));
		//timetask->issuedraft relation
		resource.add(linkTo(methodOn(EventTaskController.class).getIssueDraftsforEventTask(entity.getId())).withRel("issuedrafts"));
		
		//self
		resource.add(linkTo(methodOn(EventTaskController.class).getOneEventTask(entity.getId())).withSelfRel());

		return resource;
	}

}
