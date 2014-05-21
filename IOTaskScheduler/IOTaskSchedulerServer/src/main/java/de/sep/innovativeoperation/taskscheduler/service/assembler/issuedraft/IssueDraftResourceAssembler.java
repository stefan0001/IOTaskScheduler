package de.sep.innovativeoperation.taskscheduler.service.assembler.issuedraft;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.controller.IssueDraftController;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.service.assembler.generic.AbstractGenericDataResourceAssembler;

@Service
public class IssueDraftResourceAssembler extends AbstractGenericDataResourceAssembler<IssueDraft, IssueDraftResource> {

	public IssueDraftResourceAssembler(){
		super(IssueDraftController.class, IssueDraftResource.class);
	}

	public IssueDraftResource toResource(IssueDraft issueDraft) {
		IssueDraftResource resource = new IssueDraftResource(issueDraft);
		//self link
		resource.add(linkTo(methodOn(IssueDraftController.class).getIssueEntitiesforIssueDrafts(issueDraft.getId())).withRel("issueentities"));

		
		//link to issueentities
		resource.add(linkTo(methodOn(IssueDraftController.class).getIssueDraft(issueDraft.getId())).withSelfRel());
		
		return resource;
	}

}
