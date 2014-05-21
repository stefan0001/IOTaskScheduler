package de.sep.innovativeoperation.taskscheduler.service.assembler.timetask;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.controller.TimeTaskController;
import de.sep.innovativeoperation.taskscheduler.model.resource.TimeTaskResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.TimeTasksResource;
import de.sep.innovativeoperation.taskscheduler.service.assembler.generic.AbstractGenericDataResourcesAssembler;


@Service
public class TimeTasksResourceAssembler  extends AbstractGenericDataResourcesAssembler<TimeTaskResource,TimeTasksResource> {

	public TimeTasksResourceAssembler() {
		super(TimeTaskController.class, TimeTasksResource.class);
	}


	public TimeTasksResource toResource(Iterable<TimeTaskResource> entity) {
		TimeTasksResource resource = new TimeTasksResource(entity);
		
		//self link
		resource.add(linkTo(methodOn(TimeTaskController.class).getAllTimeTask()).withSelfRel());
		
		return resource;
	}



}
