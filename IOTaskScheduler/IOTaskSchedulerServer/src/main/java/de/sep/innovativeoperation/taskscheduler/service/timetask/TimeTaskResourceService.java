package de.sep.innovativeoperation.taskscheduler.service.timetask;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftsResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.TimeTaskResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.TimeTasksResource;
import de.sep.innovativeoperation.taskscheduler.service.AbstractGenericResourceService;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issuedraft.IssueDraftResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.issuedraft.IssueDraftsResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.timetask.TimeTaskResourceAssembler;
import de.sep.innovativeoperation.taskscheduler.service.assembler.timetask.TimeTasksResourceAssembler;


@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TimeTaskResourceService extends AbstractGenericResourceService<TimeTask,TimeTaskResource, TimeTasksResource>{
	//DATA SERVICE	
	@Autowired
	private TimeTaskDataService timeTaskDataService;
	
	//ASSEMBLER
	
	//TIMETASK
	@Autowired
	private TimeTaskResourceAssembler timeTaskResourceAssembler;
	@Autowired
	private TimeTasksResourceAssembler timeTasksResourceAssembler;
	
	//ISSUEDRAFT
	@Autowired
	private IssueDraftResourceAssembler issueDraftResourceAssembler;
	@Autowired
	private IssueDraftsResourceAssembler issueDraftsResourceAssembler;
	
	
	
	public TimeTaskResource createTimeTask(TimeTaskResource timeTaskResource){
		TimeTask timeTask = timeTaskDataService.createTimeTask(timeTaskResource.getContent() );
		TimeTaskResource resource =  timeTaskResourceAssembler.toResource(timeTask);
		return resource;
	}
	
	/**
	 * update a TimeTask with the given id
	 * @param id	id of the TimeTask
	 * @param timeTask
	 * @return
	 */
	public TimeTaskResource updateTimeTask(int id, TimeTaskResource timeTaskResource){
		TimeTask timeTask = timeTaskDataService.updateTimeTask(id , timeTaskResource.getContent() );
		TimeTaskResource resource =  timeTaskResourceAssembler.toResource(timeTask);
		return resource;
	}
	

	
	
	
	/**
	 * get all IssueDrafts for the TimeTask with the id
	 * @param id 	id of the TimeTask
	 * @return
	 */
	public IssueDraftsResource getIssueDraftsforTimeTask(int id){
		//get issuedraft
		Set<IssueDraft> issueDrafts = timeTaskDataService.getIssueDraftsforTimeTask(id);
		//generate resources
		List<IssueDraftResource> issueDraftResoruces = issueDraftResourceAssembler.toResources(issueDrafts);
		//generate resources to one resource
		return issueDraftsResourceAssembler.toResource(issueDraftResoruces);
		
	}
	
	
	/**
	 * create a relation between a timetask and a issuedraft
	 * for id of issueDraft == 0 a new issuedraft is created
	 * @param timeTaskId
	 * @param issueDraftResource
	 */
	public IssueDraftResource createRelationTimeTaskIssueDraft(int timeTaskId, IssueDraftResource issueDraftResource){
		 IssueDraft issueDraftResult = timeTaskDataService.createRelationTimeTaskIssueDraft(timeTaskId, issueDraftResource.getContent());
		 IssueDraftResource newIssueDraftResource = issueDraftResourceAssembler.toResource(issueDraftResult);
		 
		 return newIssueDraftResource;
	}
	
	/**
	 * delete a relation between a timetask and a issuedraft
	 * @param timeTaskId
	 * @param issueDraftId
	 */
	public void deleteRelationTimeTaskIssueDraft(int timeTaskId, int issueDraftId){
		timeTaskDataService.deleteRelationTimeTaskIssueDraft(timeTaskId, issueDraftId);
	}
}


