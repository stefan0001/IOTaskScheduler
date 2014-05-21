package de.sep.innovativeoperation.taskscheduler.service.assembler.issueentity;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.controller.IssueEntityController;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntityResource;
import de.sep.innovativeoperation.taskscheduler.service.assembler.generic.AbstractGenericDataResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issuedraft.IssueDraftResourceAssembler;

@Service
public class IssueEntityResourceAssembler extends AbstractGenericDataResourceAssembler<IssueEntity, IssueEntityResource>{
	@Autowired
	private IssueDraftResourceAssembler issueDraftResourceAssembler;
	

	
	public IssueEntityResourceAssembler() {
		super(IssueEntityController.class, IssueEntityResource.class);
	}


	public IssueEntityResource toResource(IssueEntity entity) {
		IssueDraftResource issueDraftResource = issueDraftResourceAssembler.toResource(entity.getIssueDraft());
		
		IssueEntityResource issueEntityResource = new IssueEntityResource(entity, issueDraftResource);
		//self link
		issueEntityResource.add(linkTo(methodOn(IssueEntityController.class).getIssueEntity(entity.getId())).withSelfRel());
		
		
		//add embedded resource issuedraft

		
		return issueEntityResource;
	}









}
