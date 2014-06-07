package de.innovativeoperation.taskscheduler.test.controller;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static de.sep.innovativeoperation.taskscheduler.config.Config.JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import de.sep.innovativeoperation.taskscheduler.controller.IssueEntityController;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntitiesResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntityResource;

import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityResourceService;

import static org.mockito.Mockito.*;

@TransactionConfiguration(defaultRollback = true)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestIssueEntityController {

	private MockMvc mockMvc;
	private final String url = "/issueentity";

	private MediaType appJSON = MediaType.parseMediaType(JSON);

	private IssueDraft issueDraft;
	private List<IssueDraft> issueDraftList;
	private List<IssueEntityResource> issueEntityList;
	private IssueEntitiesResource issueEntitiesResource;
	private IssueEntityResource issueEntityResource;
	private IssueEntity issueEntity;

	@Autowired
	@InjectMocks
	private IssueEntityController issueEntityController;

	@Mock
	private IssueEntityResourceService issueEntityResourceService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {

		if (issueEntityResourceService != null)
			reset(issueEntityResourceService);

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders
				.<StandaloneMockMvcBuilder> webAppContextSetup(wac).build();

		issueDraft = new IssueDraft("name", "issueDescription", IssueType.BUG);
		issueDraftList = new LinkedList<IssueDraft>();
		issueDraftList.add(issueDraft);

		issueEntity = new IssueEntity();
		issueEntity.setId(0);
		issueEntity.setIssueDraft(issueDraft);
		issueEntity.setIssueResolution(IssueResolution.DONE);
		issueEntity.setIssueStatus(IssueStatus.CLOSED);
		issueEntityResource = new IssueEntityResource(issueEntity);
		issueEntityList = new LinkedList<IssueEntityResource>();
		issueEntityList.add(issueEntityResource);
		issueEntitiesResource = new IssueEntitiesResource(issueEntityList); 
	}

	@Test
	public void shouldStartSpringContext() throws Exception {
		Assert.assertNotNull(issueEntityController);
	}

	@Test
	public void testGetAllIssueEntities200() throws Exception {

		when(issueEntityResourceService.filterIssueEntities(issueEntityResource)).thenReturn(
				issueEntitiesResource);

		mockMvc.perform(
				get(url).contentType(appJSON).accept(appJSON).param("filter", "{ }"))
				.andExpect(status().isOk());

		verify(issueEntityResourceService, times(1)).filterIssueEntities(issueEntityResource);
	}
	@Test
	public void testGetAllIssueEntitiesWithNull200() throws Exception {

		String thisIsNull = null;
		when(issueEntityResourceService.getAll()).thenReturn(
				issueEntitiesResource);

		mockMvc.perform(
				get(url).contentType(appJSON).accept(appJSON).param("filter", thisIsNull))
				.andExpect(status().isOk());

		verify(issueEntityResourceService, times(1)).getAll();
	}
	@Test
	public void testGetAllIssueEntitiesWithoutParam200() throws Exception {

		
		when(issueEntityResourceService.getAll()).thenReturn(
				issueEntitiesResource);

		mockMvc.perform(
				get(url).contentType(appJSON).accept(appJSON))
				.andExpect(status().isOk());

		verify(issueEntityResourceService, times(1)).getAll();
	}

	@Test
	public void testGetIssueEntityWithIdZero() throws Exception {

		when(issueEntityResourceService.getById(0)).thenReturn(
				issueEntityResource);
		mockMvc.perform(get(url + "/0").accept(appJSON)).andExpect(
				status().isOk());

		verify(issueEntityResourceService, times(1)).getById(0);

	}

	@Test
	public void testUpdateIssueEntityWithIdZero() throws Exception {
		IssueEntity alteredIssueEntity = new IssueEntity();
		alteredIssueEntity.setId(0);

		alteredIssueEntity.setIssueDraft(issueDraft);
		alteredIssueEntity.setIssueResolution(IssueResolution.CANNOT_REPRODUCE);
		alteredIssueEntity.setIssueStatus(IssueStatus.NEW);
		IssueEntityResource alteredIssueEntityResource = new IssueEntityResource(
				alteredIssueEntity);
		List<IssueEntityResource> alteredIssueEntityList = new LinkedList<IssueEntityResource>();
		alteredIssueEntityList.add(alteredIssueEntityResource);

		when(
				issueEntityResourceService.updateIssueEntity(0,
						issueEntityResource)).thenReturn(
				alteredIssueEntityResource);

		mockMvc.perform(
				put(url + "/0")
						.accept(appJSON)
						.contentType(appJSON)
						.content(
								"{\"links\":[],\"id\":0,\"issueName\":\"name\",\"issueDescription\":\"issueDescription\",\"issueType\":\"BUG\"}"))
				.andExpect(status().isOk());

		verify(issueEntityResourceService, times(1)).updateIssueEntity(0,
				issueEntityResource);

	}

	@Test
	public void testDeleteIssueEntityExpect200() throws Exception {
		mockMvc.perform(delete(url + "/0").accept(appJSON)).andExpect(
				status().isOk());

		verify(issueEntityResourceService,times(1)).deleteById(0);
	}

	@Test
	public void testAccessingWrongResourceExpected404() throws Exception {

		mockMvc.perform(get("/issueentitiess"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteAllIssueEntitiesExpect405() throws Exception {
		mockMvc.perform(delete(url)).andExpect(status().isMethodNotAllowed());

	}
	
	@Test
	public void testFilterIssueEntities() throws Exception {
		IssueEntitiesResource issueEntitiesResource = new IssueEntitiesResource();
		when(issueEntityResourceService.filterIssueEntities(issueEntityResource)).thenReturn(issueEntitiesResource);
		mockMvc.perform(get(url).contentType(appJSON).accept(appJSON).param("filter", "{ }")).andExpect(status().isOk());
		
		verify(issueEntityResourceService, times(1)).filterIssueEntities(issueEntityResource);
	}
	
	@Test
	public void testFilterIssueEntitiesWithNull() throws Exception {
		IssueEntitiesResource issueEntitiesResource = new IssueEntitiesResource();
		String thisIsNull = null;
		when(issueEntityResourceService.getAll()).thenReturn(issueEntitiesResource);
		mockMvc.perform(get(url).contentType(appJSON).accept(appJSON).param("filter", thisIsNull)).andExpect(status().isOk());
		
		verify(issueEntityResourceService, times(1)).getAll();


	}
}