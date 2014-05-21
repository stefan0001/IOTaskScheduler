package de.sep.innovativeoperation.taskscheduler.service.issueentity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntitiesResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntityResource;
import de.sep.innovativeoperation.taskscheduler.service.AbstractGenericResourceService;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issueentity.IssueEntitiesResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issueentity.IssueEntityResourceAssembler;


@Service
@Transactional
public class IssueEntityResourceService extends AbstractGenericResourceService<IssueEntity,IssueEntityResource, IssueEntitiesResource>{
	//SERVICES
	@Autowired 
	private IssueEntityDataService issueEntityDataService;
	
	//ASSEMBLER
	@Autowired 
	private IssueEntityResourceAssembler issueEntityResourceAssembler;
	@Autowired
	private IssueEntitiesResourceAssembler issueEntitiesResourceAssembler;
	
	
	
	public IssueEntityResource createIssueEntity(int issueDraftId, IssueEntityResource issueEntityResource) {
		IssueEntity issueEntity = issueEntityDataService.createIssueEntity(issueDraftId, issueEntityResource.getContent() );
		
		return issueEntityResourceAssembler.toResource(issueEntity);
	}
	

	public IssueEntityResource updateIssueEntity(int id, IssueEntityResource issueEntityResource) {
		IssueEntity issueEntity = issueEntityDataService.updateIssueEntity(id, issueEntityResource.getContent() );
		
		return issueEntityResourceAssembler.toResource(issueEntity);
	}
	
	public IssueEntitiesResource filterIssueEntities(IssueEntityResource issueEntityResource){
		List<IssueEntity> resultIssueEntities = issueEntityDataService.filterIssueEntity(issueEntityResource.getContent(), issueEntityResource.getEmbedded().getResource().getContent());
		List<IssueEntityResource> resultIssueEntityResources = issueEntityResourceAssembler.toResources(resultIssueEntities);
		
		return issueEntitiesResourceAssembler.toResource(resultIssueEntityResources);
	}
	
	public void archiveById(int id){
		issueEntityDataService.archiveById(id);
	}
	
}
