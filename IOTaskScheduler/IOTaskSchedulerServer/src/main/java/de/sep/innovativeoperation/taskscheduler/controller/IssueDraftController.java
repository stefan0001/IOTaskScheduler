package de.sep.innovativeoperation.taskscheduler.controller;

import static de.sep.innovativeoperation.taskscheduler.config.Config.JSON;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftsResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntitiesResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntityResource;
import de.sep.innovativeoperation.taskscheduler.service.issuedraft.IssueDraftResourceService;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityResourceService;
/**
 * Controller for CRUD operations on Issue Drafts
 * 
 * @author Stefan
 * 
 */
@Controller
@RequestMapping(value = "/issuedraft")
public class IssueDraftController {

	//SERVICES
	@Autowired
	private IssueDraftResourceService issueDraftResourceService;
	@Autowired
	private IssueEntityResourceService issueEntityResourceService;
	
	
	
	
	/**
	 * Load all IssueDrafts
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = JSON)
	public @ResponseBody IssueDraftsResource getIssueDrafts(@RequestParam(value = "filter" , required=false) IssueDraftResource issueDraftResource) {
		
		if(issueDraftResource == null){
			//load all in case for no filter
			return issueDraftResourceService.getAll();
		}
		
		//return filtered
		return issueDraftResourceService.filterIssueDrafts(issueDraftResource);
		

	}
	
	
	
	/**
	 * Load one IssueDraft
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{issuedraftid}",method = RequestMethod.GET, produces = JSON)
	public @ResponseBody IssueDraftResource getIssueDraft( @PathVariable("issuedraftid") int id) {
		return issueDraftResourceService.getById(id);
	}
	
	/**
	 * Update an issuedraft
	 * @param id			The id of the issuedraft
	 * @param issueDraft	The updated information of the issuedraft
	 * @return
	 */
	@RequestMapping(value="/{issuedraftid}",method = RequestMethod.PUT, produces = JSON)
	public @ResponseBody IssueDraftResource getIssueDraft( @PathVariable("issuedraftid") int id,  @RequestBody IssueDraftResource issueDraftResource) {
		return issueDraftResourceService.updateIssueDraft(id, issueDraftResource);
	}
	
	
	/**
	 * Delete IssueDraft with a given id
	 * @param id
	 * @param issueDraft
	 * @return
	 */
	@RequestMapping(value="/{issuedraftid}",method = RequestMethod.DELETE, produces = JSON)
	public @ResponseBody void deleteIssueDraft( @PathVariable("issuedraftid") int id) {
		issueDraftResourceService.deleteById(id);
	}
	
	

	/**
	 * Load all IssueEntities for a given IssueDraft
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{issuedraftid}/issueentity",method = RequestMethod.GET, produces = JSON)
	public @ResponseBody IssueEntitiesResource getIssueEntitiesforIssueDrafts( @PathVariable("issuedraftid") int id) {
		return issueDraftResourceService.getIssueEntitiesForIssueDraft(id);
	}
	
	/**
	 * Create new IssueEntity for a IssueDraft
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{issuedraftid}/issueentity",method = RequestMethod.POST, produces = JSON)
	public @ResponseBody IssueEntityResource createIssueEntityforIssueDrafts( @PathVariable("issuedraftid") int id, @RequestBody IssueEntityResource issueEntityResource) {
		return issueEntityResourceService.createIssueEntity(id, issueEntityResource);
	}
	
	
	
	
	/**
	 * Receive a new Issue Draft and save it
	 * @param issueDraft
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, produces = JSON)
	public @ResponseBody IssueDraftResource createIssueDraft( @RequestBody IssueDraftResource issueDraftResource) {
		return issueDraftResourceService.createIssueDraft(issueDraftResource);
	}
	
	

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
	    dataBinder.registerCustomEditor(IssueDraftResource.class, new PropertyEditorSupport() {
	        IssueDraftResource value;
	        @Override
	        public IssueDraftResource getValue() {
	            return value;
	        }

	        @Override
	        public void setAsText(String text) throws IllegalArgumentException {
	        	ObjectMapper mapper = new ObjectMapper();
	            try {
					value = mapper.readValue(text, IssueDraftResource.class);
				} catch (IOException e) {
					throw new IllegalArgumentException();
				}
	        }
	    });
	}
	
	
	


}
