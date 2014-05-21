package de.innovativeoperation.taskscheduler.test.controller;

import static de.sep.innovativeoperation.taskscheduler.config.Config.JSON;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import de.sep.innovativeoperation.taskscheduler.controller.EventTaskController;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTaskResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTasksResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftsResource;
import de.sep.innovativeoperation.taskscheduler.service.eventtask.EventTaskResourceService;

@TransactionConfiguration(defaultRollback = true)
@WebAppConfiguration
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEventTaskController {
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	@InjectMocks
	private EventTaskController eventTaskController;

	@Mock
	private EventTaskResourceService eventTaskResourceService;

	private MediaType appJSON = MediaType.parseMediaType(JSON);

	private MockMvc mockMvc;
	private String url = "/eventtask";
	private EventTasksResource eventTasksResource;
	private EventTaskResource eventTaskResource;

	@Before
	public void setup() {
		if (eventTaskResourceService != null)
			reset(eventTaskResourceService);

		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders
				.<StandaloneMockMvcBuilder> webAppContextSetup(wac).build();

		eventTasksResource = new EventTasksResource();
		eventTaskResource = new EventTaskResource();
	}

	@Test
	public void testAccessEventController200() throws Exception {

		when(eventTaskResourceService.getAll()).thenReturn(eventTasksResource);

		this.mockMvc.perform(get(url).accept(appJSON)).andExpect(status().isOk());

		verify(eventTaskResourceService, times(1)).getAll();
	}

	@Test
	public void testGetEventTaskByIdOne200() throws Exception {

		when(eventTaskResourceService.getById(1)).thenReturn(eventTaskResource);

		this.mockMvc.perform(get(url + "/1").accept(appJSON)).andExpect(
				status().isOk());

		verify(eventTaskResourceService, times(1)).getById(1);
	}

	@Test
	public void testUpdateEventTaskOne200() throws Exception {
		EventTaskResource updatedEventTaskResource = new EventTaskResource();
		EventTaskResource calledEventTaskResource = eventTaskResource;
		when(
				eventTaskResourceService.updateEventTask(1,
						calledEventTaskResource)).thenReturn(
				updatedEventTaskResource);

		this.mockMvc.perform(
				put(url + "/1").accept(appJSON).contentType(appJSON)
						.content("{}")).andExpect(status().isOk());

		verify(eventTaskResourceService, times(1)).updateEventTask(1,
				calledEventTaskResource);
	}

	@Test
	public void testGetIssueDraftForEventTask200() throws Exception {
		IssueDraftsResource issueDraftsResource = new IssueDraftsResource();
		when(eventTaskResourceService.getIssueDraftsforEventTask(1))
				.thenReturn(issueDraftsResource);

		this.mockMvc.perform(
				get(url + "/1/issuedraft").accept(appJSON)
						.contentType(appJSON).content("{}")).andExpect(
				status().isOk());

		verify(eventTaskResourceService, times(1))
				.getIssueDraftsforEventTask(1);
	}

	@Test
	public void testCreateRelationEventTaskIssueDraft200() throws Exception {
		IssueDraftResource issueDraftResource = new IssueDraftResource();
		
		when(eventTaskResourceService.createRelationEventTaskIssueDraft(1, issueDraftResource))
				.thenReturn(issueDraftResource);

		this.mockMvc.perform(
				post(url + "/1/issuedraft").accept(appJSON)
						.contentType(appJSON).content("{}")).andExpect(
				status().isOk());

		verify(eventTaskResourceService, times(1))
				.createRelationEventTaskIssueDraft(1, issueDraftResource);
	}
	
	@Test
	public void testDeleteRelationEventTaskIssueDraft200() throws Exception {
		
		this.mockMvc.perform(
				delete(url + "/1/issuedraft/1").accept(appJSON)
						.contentType(appJSON).content("{}")).andExpect(
				status().isOk());

		verify(eventTaskResourceService, times(1))
				.deleteRelationEventTaskIssueDraft(1, 1);
	}
	
	@Test
	public void testDeleteEventTask200() throws Exception {

		this.mockMvc.perform(delete(url + "/1").accept(appJSON)).andExpect(
				status().isOk());

		verify(eventTaskResourceService, times(1)).deleteById(1);
	}

	@Test
	public void testPost405() throws Exception {

		this.mockMvc.perform(
				post(url).accept(appJSON).content(
						"{\"links\":[],\"content\":[]}")).andExpect(
				status().isMethodNotAllowed());

	}

}
