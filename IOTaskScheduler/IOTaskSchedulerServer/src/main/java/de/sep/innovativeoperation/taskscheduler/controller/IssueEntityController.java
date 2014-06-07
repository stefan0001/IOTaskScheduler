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

import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntitiesResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntityResource;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityResourceService;
/**
 * Controller for CRUD operations on Issue Entities
 * 
 * @author Stefan
 * 
 */
@Controller
@RequestMapping(value = "/issueentity")
public class IssueEntityController {

	// ResourceService
	@Autowired
	private IssueEntityResourceService issueEntityResourceService;

	

	/**
	 * Get IssueEntities
	 * @param issueEntityResource	Filter for a given issueentity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = JSON)
	public @ResponseBody IssueEntitiesResource getIssueEntities(@RequestParam(value = "filter" , required=false) IssueEntityResource issueEntityResource) {
		if(issueEntityResource == null){ 
			//find all
			issueEntityResource = new IssueEntityResource();
		}
		
		//return filtered issueentities
		return issueEntityResourceService.filterIssueEntities(issueEntityResource);
	}
	
	
	

	/**
	 * Load one IssueEntity
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{issueentityid}", method = RequestMethod.GET, produces = JSON)
	public @ResponseBody IssueEntityResource getIssueEntity(@PathVariable("issueentityid") int id) {
		return issueEntityResourceService.getById(id);

	}

	/**
	 * Update one issueEntity
	 * @param id
	 * @param issueEntityResource
	 * @return
	 */
	@RequestMapping(value = "/{issueentityid}", method = RequestMethod.PUT, produces = JSON)
	public @ResponseBody IssueEntityResource updateIssueEntity(@PathVariable("issueentityid") int id, @RequestBody IssueEntityResource issueEntityResource) {
		return issueEntityResourceService.updateIssueEntity(id, issueEntityResource);
	}

	/**
	 * Delete a IssueEntity with a given id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{issueentityid}", method = RequestMethod.DELETE, produces = JSON)
	public @ResponseBody void deleteIssueEntity(@PathVariable("issueentityid") int id) {
		//archive issueentity
		issueEntityResourceService.deleteById(id);
	}
	
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
	    dataBinder.registerCustomEditor(IssueEntityResource.class, new PropertyEditorSupport() {
	        IssueEntityResource value;
	        @Override
	        public IssueEntityResource getValue() {
	            return value;
	        }

	        @Override
	        public void setAsText(String text) throws IllegalArgumentException {
	        	ObjectMapper mapper = new ObjectMapper();
	            try {
					value = mapper.readValue(text, IssueEntityResource.class);
				} catch (IOException e) {
					throw new IllegalArgumentException();
				}
	        }
	    });
	}
	


}
