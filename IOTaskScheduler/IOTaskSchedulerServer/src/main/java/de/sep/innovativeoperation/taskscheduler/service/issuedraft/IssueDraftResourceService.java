package de.sep.innovativeoperation.taskscheduler.service.issuedraft;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftsResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntitiesResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntityResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.TimeTaskResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.TimeTasksResource;
import de.sep.innovativeoperation.taskscheduler.service.AbstractGenericResourceService;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issueentity.IssueEntitiesResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issueentity.IssueEntityResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.timetask.TimeTaskResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.timetask.TimeTasksResourceAssembler;


@Service
@Transactional
public class IssueDraftResourceService extends AbstractGenericResourceService<IssueDraft,IssueDraftResource, IssueDraftsResource>{

	//SERVICES
	@Autowired
	private IssueDraftDataService issueDraftDataService;
	
	//ASSEMBLER
	
	//ISSUEENTITY
	@Autowired 
	private IssueEntityResourceAssembler issueEntityResourceAssembler;
	@Autowired
	private IssueEntitiesResourceAssembler issueEntitiesResourceAssembler;
	
	
	//TIMETASK
	@Autowired
	private TimeTaskResourceAssembler timeTaskResourceAssembler;
	@Autowired
	private TimeTasksResourceAssembler timeTasksResourceAssembler;
	

	public IssueDraftResource createIssueDraft(IssueDraftResource issueDraftResource) {
		IssueDraft issueDraft = issueDraftDataService.createIssueDraft(issueDraftResource.getContent());
		
		return resourceAssembler.toResource(issueDraft);
	}


	public IssueDraftResource updateIssueDraft(int id, IssueDraftResource issueDraftResource) {
		IssueDraft issueDraft = issueDraftDataService.updateIssueDraft(id, issueDraftResource.getContent());
		
		return resourceAssembler.toResource(issueDraft);
	}
	

	public IssueEntitiesResource getIssueEntitiesForIssueDraft(int issueDraftId) {
		Set<IssueEntity> issueEntities =  issueDraftDataService.getIssueEntitiesForIssueDraft(issueDraftId);
		List<IssueEntityResource> issueEntityResources = issueEntityResourceAssembler.toResources(issueEntities);
		return issueEntitiesResourceAssembler.toResource(issueEntityResources);
	}

	
	/**
	 * get all TimeTasks for the IssueDraft with the id
	 * @param id 	id of the IssueDraft
	 * @return
	 */
	public TimeTasksResource getTimeTasksForIssueDraft(int id){
		Set<TimeTask> timeTasks = issueDraftDataService.getTimeTasksForIssueDraft(id);
		List<TimeTaskResource> timeTaskResources = timeTaskResourceAssembler.toResources(timeTasks);
		TimeTasksResource timeTasksResource = timeTasksResourceAssembler.toResource(timeTaskResources);
		
		
		return timeTasksResource;
		
	}

	
	/**
	 * 
	 * @param issueDraftResource
	 * @return
	 */
	public IssueDraftsResource filterIssueDrafts(IssueDraftResource issueDraftResource){
		List<IssueDraft> resultIssueDrafts = issueDraftDataService.filterIssueDraft(issueDraftResource.getContent());
		List<IssueDraftResource> resultIssueDraftResources = resourceAssembler.toResources(resultIssueDrafts);
		
		return resourcesAssembler.toResource(resultIssueDraftResources);
	}

}
