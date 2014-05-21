package de.innovativeoperation.taskscheduler.test.controller;

import static de.sep.innovativeoperation.taskscheduler.config.Config.JSON;

import java.util.LinkedList;
import java.util.List;
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

import static org.hamcrest.core.Is.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import de.sep.innovativeoperation.taskscheduler.controller.IssueDraftController;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftsResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntitiesResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueEntityResource;
import de.sep.innovativeoperation.taskscheduler.service.issuedraft.IssueDraftResourceService;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityResourceService;

/**
 * 
 * @author Joscha Zander
 * 
 */
@TransactionConfiguration(defaultRollback = true)
@WebAppConfiguration
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestIssueDraftController {

	@Autowired
	@InjectMocks
	private IssueDraftController issueDraftController;

	@Mock
	private IssueDraftResourceService issueDraftResourceService;
	@Mock
	private IssueEntityResourceService issueEntityResourceService;

	private MediaType appJSON = MediaType.parseMediaType(JSON);
	private MockMvc mockMvc;
	private String url = "/issuedraft";
	private IssueDraft issueDraft;
	private List<IssueDraft> issueDraftList;
	private List<IssueEntityResource> issueEntityList;
	private IssueDraftResource issueDraftResource;
	private IssueDraftsResource issueDraftsResource;
	private IssueEntitiesResource issueEntitiesResource;
	private IssueEntityResource issueEntityResource;
	private IssueEntity issueEntity;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setupTest() {
		if (issueDraftResourceService != null)
			reset(issueDraftResourceService);
		if (issueEntityResourceService != null)
			reset(issueEntityResourceService);

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Build a MockMvc by registering one or more @Controller's instances
		// and configuring Spring MVC infrastructure programmatically.
		mockMvc = MockMvcBuilders
				.<StandaloneMockMvcBuilder> webAppContextSetup(wac).build();

		issueDraft = new IssueDraft("name", "issueDescription", IssueType.BUG);
		issueDraftList = new LinkedList<IssueDraft>();
		issueDraftList.add(issueDraft);

		issueDraftResource = new IssueDraftResource(issueDraft);
		issueDraftsResource = new IssueDraftsResource();

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
	public void testAccessingIssueDraftExpected200() throws Exception {

		when(issueDraftResourceService.getAll())
				.thenReturn(issueDraftsResource);

		this.mockMvc.perform(get(url).accept(appJSON)).andExpect(
				status().isOk());

		verify(issueDraftResourceService, times(1)).getAll();
	}

	@Test
	public void testAccessingIssueDraftWithIdOneExpected200() throws Exception {

		when(issueDraftResourceService.getById(1)).thenReturn(
				issueDraftResource);

		this.mockMvc.perform(get("/issuedraft/1").accept(appJSON)).andExpect(
				status().isOk());

		verify(issueDraftResourceService, times(1)).getById(1);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateIssueDraftWithIdZeroAndVerifyResponseJsonExpected201()
			throws Exception {
		IssueDraft issueDraftTemp = new IssueDraft("name", "issueDescription",
				IssueType.BUG);
		IssueDraftResource issueDraftResourceTemp = new IssueDraftResource(
				issueDraftTemp);
		IssueDraft alteredIssueDraft = new IssueDraft("name2",
				"issueDescription2", IssueType.BUG);
		IssueDraftResource alteredIssueDraftResource = new IssueDraftResource(
				alteredIssueDraft);

		when(
				issueDraftResourceService.updateIssueDraft(0,
						issueDraftResourceTemp)).thenReturn(
				alteredIssueDraftResource);

		this.mockMvc
				.perform(
						put(url + "/0")
								.contentType(appJSON)
								.content(
										"{\"links\":[],\"id\":0,\"issueName\":\"name2\",\"issueDescription\":\"issueDescription2\",\"issueType\":\"BUG\"}"))

				.andExpect(status().isOk())
				.andExpect(jsonPath("$.issueName", is("name2")))
				.andExpect(
						jsonPath("$.issueDescription", is("issueDescription2")))
				.andExpect(jsonPath("$.issueType", is("BUG")));

		verify(issueDraftResourceService, times(1)).updateIssueDraft(0,
				issueDraftResourceTemp);
	}

	@Test
	public void testDeleteIssueDraftWithIdZeroExpect200() throws Exception {

		this.mockMvc.perform(delete(url + "/0").accept(appJSON)).andExpect(
				status().isOk());

		verify(issueDraftResourceService, times(1)).deleteById(0);
	}

	@Test
	public void testGetAllIssueEntitiyForIssueDraftWithIdZEROAndVerifyResponseJsonExpect200()
			throws Exception {

		when(issueDraftResourceService.getIssueEntitiesForIssueDraft(0))
				.thenReturn(issueEntitiesResource);

		this.mockMvc.perform(get(url + "/0/issueentity").accept(appJSON))
				.andExpect(status().isOk());

		verify(issueDraftResourceService, times(1))
				.getIssueEntitiesForIssueDraft(0);
	}

	@Test
	public void testCreateNewIssueEntitiyForIssueDraftWithIdOneAndVerifyResponseJsonExpect201()
			throws Exception {

		IssueEntity mockedIssueEntity = new IssueEntity();
		mockedIssueEntity.setId(1);
		mockedIssueEntity.setIssueDraft(issueDraft);
		mockedIssueEntity.setIssueResolution(IssueResolution.DONE);
		mockedIssueEntity.setIssueStatus(IssueStatus.CLOSED);

		IssueEntityResource newIssueEntityResource = issueEntityResource;

		when(
				issueEntityResourceService.createIssueEntity(1,
						newIssueEntityResource)).thenReturn(
				newIssueEntityResource);

		this.mockMvc
				.perform(
						post(url + "/1/issueentity")
								.contentType(appJSON)
								.content(
										"{\"links\":[],\"id\":0,\"issueName\":\"name2\",\"issueDescription\":\"issueDescription2\",\"issueType\":\"BUG\"}")
								.accept(appJSON)).andExpect(status().isOk());

		verify(issueEntityResourceService, times(1)).createIssueEntity(1,
				newIssueEntityResource);
	}

	@Test
	public void testFilterIssueDrafts200() throws Exception {
		IssueDraftResource issueDraftResource = new IssueDraftResource();
		IssueDraftsResource returnedIssueDraftsResource = new IssueDraftsResource();

		when(issueDraftResourceService.filterIssueDrafts(issueDraftResource))
				.thenReturn(returnedIssueDraftsResource);

		this.mockMvc.perform(get(url).accept(appJSON).param("filter", "{ }"))
				.andExpect(status().isOk());

		verify(issueDraftResourceService, times(0)).getAll();

		verify(issueDraftResourceService, times(1)).filterIssueDrafts(
				issueDraftResource);
	}

	public void testFilterIssueDraftsWithoutParam() throws Exception {
		IssueDraftResource issueDraftResource = new IssueDraftResource();
		IssueDraftsResource returnedIssueDraftsResource = new IssueDraftsResource();
		String thisIsNull = null;
		
		when(issueDraftResourceService.filterIssueDrafts(issueDraftResource))
				.thenReturn(returnedIssueDraftsResource);

		this.mockMvc.perform(get(url).accept(appJSON).param("filter", thisIsNull))
				.andExpect(status().isOk());

		verify(issueDraftResourceService, times(0)).filterIssueDrafts(
				issueDraftResource);

		verify(issueDraftResourceService, times(1)).getAll();
	}

	@Test
	public void testCreateIssueDraftExpect200() throws Exception {
		IssueDraftResource issueDraftResource = new IssueDraftResource();
		IssueDraftResource returnedIssueDraftResource = new IssueDraftResource();
		
		when(issueDraftResourceService.createIssueDraft(issueDraftResource))
				.thenReturn(returnedIssueDraftResource);

		this.mockMvc.perform(post(url).accept(appJSON).contentType(appJSON).content("{ }")).andExpect(
				status().isOk());

		verify(issueDraftResourceService, times(1)).createIssueDraft(
				issueDraftResource);
	}

	@Test
	public void testAccessingWrongResourceExpected404() throws Exception {

		this.mockMvc.perform(get("/issuedrafts")).andExpect(
				status().isNotFound());
	}
}
