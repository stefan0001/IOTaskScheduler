package de.sep.innovativeoperation.taskscheduler.service.assembler.timetask;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.controller.TimeTaskController;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.model.resource.TimeTaskResource;
import de.sep.innovativeoperation.taskscheduler.service.assembler.generic.AbstractGenericDataResourceAssembler;

@Service
public class TimeTaskResourceAssembler extends AbstractGenericDataResourceAssembler<TimeTask, TimeTaskResource>{

	public TimeTaskResourceAssembler() {
		super(TimeTaskController.class, TimeTaskResource.class);
	}

	public TimeTaskResource toResource(TimeTask entity) {
		TimeTaskResource resource = new TimeTaskResource(entity);
		
		//timetask->issuedraft relation
		resource.add(linkTo(methodOn(TimeTaskController.class).getIssueDraftsforTimeTask(entity.getId())).withRel("issuedrafts"));
		
		//self link
		resource.add(linkTo(methodOn(TimeTaskController.class).getOneTimeTask(entity.getId())).withSelfRel());
		
		return resource;
	}

}
