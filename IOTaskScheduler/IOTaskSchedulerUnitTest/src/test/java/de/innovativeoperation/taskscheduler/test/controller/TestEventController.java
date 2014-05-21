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

import de.sep.innovativeoperation.taskscheduler.controller.EventController;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTaskResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventTasksResource;
import de.sep.innovativeoperation.taskscheduler.model.resource.EventsResource;
import de.sep.innovativeoperation.taskscheduler.service.event.EventResourceService;
import de.sep.innovativeoperation.taskscheduler.service.eventtask.EventTaskResourceService;

/**
 * Test the EventController at the URL: "/event" and it´s RequestMapping by Spring
 * with Mockito and SpringMockMvc
 * 
 * dependencies: EventResourceService and EventTaskResourceService
 * 
 * @author Joscha Zander
 * @TransactionConfiguration(defaultRollback = true)
 * @WebAppConfiguration
 * @ContextConfiguration({ "classpath:applicationContext.xml" })
 * @RunWith(SpringJUnit4ClassRunner.class)
 */
@TransactionConfiguration(defaultRollback = true)
@WebAppConfiguration
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEventController {

	@Autowired
	@InjectMocks
	private EventController eventController;

	@Mock
	private EventResourceService eventResourceService;
	@Mock
	private EventTaskResourceService eventTaskResourceService;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	private String url = "/event";
	private EventsResource eventsResource;
	private EventResource eventResource;
	private MediaType appJSON = MediaType.parseMediaType(JSON);

	@Before
	public void setup() {

		// reset mocks to prevent side effects
		if (eventResourceService != null)
			reset(eventResourceService);

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// setUp the MockMvcBuilder
		mockMvc = MockMvcBuilders
				.<StandaloneMockMvcBuilder> webAppContextSetup(wac).build();

		eventsResource = new EventsResource();
		eventResource = new EventResource();
	}

	@Test
	public void testAccessEventController200() throws Exception {

		when(eventResourceService.getAll()).thenReturn(eventsResource);

		mockMvc.perform(get(url).accept(appJSON)).andExpect(status().isOk());

		verify(eventResourceService, times(1)).getAll();
	}

	@Test
	public void testGetEventByIdOne200() throws Exception {

		when(eventResourceService.getById(1)).thenReturn(eventResource);

		mockMvc.perform(get(url + "/1").accept(appJSON)).andExpect(
				status().isOk());

		verify(eventResourceService, times(1)).getById(1);
	}

	@Test
	public void testCreateEvent200() throws Exception {

		when(eventResourceService.createEvent(eventResource)).thenReturn(
				eventResource);

		mockMvc.perform(
				post(url).contentType(appJSON).content("{}").accept(appJSON))
				.andExpect(status().isOk());

		verify(eventResourceService, times(1)).createEvent(eventResource);
	}

	@Test
	public void testDeleteEvent200() throws Exception {

		mockMvc.perform(delete(url + "/1").accept(appJSON)).andExpect(
				status().isOk());

		verify(eventResourceService, times(1)).deleteById(1);
	}

	@Test
	public void testUpdateEventOne200() throws Exception {
		EventResource updatedEventResource = new EventResource();

		when(eventResourceService.updateEvent(1, eventResource)).thenReturn(
				updatedEventResource);

		mockMvc.perform(
				put(url + "/1").contentType(appJSON).content("{}")
						.accept(appJSON)).andExpect(status().isOk());

		verify(eventResourceService, times(1)).updateEvent(1, eventResource);
	}

	@Test
	public void testTriggerEventOne200() throws Exception {
		mockMvc.perform(
				put(url + "/1/trigger").accept(appJSON).contentType(appJSON)
						.content("{}")).andExpect(status().isOk());

		verify(eventResourceService, times(1)).triggerEvent(1);
	}

	@Test
	public void testGetAllEventTasksForEvent200() throws Exception {
		EventTasksResource eventTasksResource = new EventTasksResource();

		when(eventTaskResourceService.getAllEventTasksForEvent(1)).thenReturn(
				eventTasksResource);

		mockMvc.perform(
				get(url + "/1/eventtask").accept(appJSON).contentType(appJSON)
						.content("{}")).andExpect(status().isOk());

		verify(eventTaskResourceService, times(1)).getAllEventTasksForEvent(1);
	}

	@Test
	public void testCreateEventTask200() throws Exception {
		EventTaskResource eventTaskResource = new EventTaskResource();
		EventTaskResource returnedEventTaskResource = new EventTaskResource();
		when(eventTaskResourceService.createEventTask(1, eventTaskResource))
				.thenReturn(returnedEventTaskResource);

		mockMvc.perform(
				post(url + "/1/eventtask").accept(appJSON).contentType(appJSON)
						.content("{}")).andExpect(status().isOk());

		verify(eventTaskResourceService, times(1)).createEventTask(1,
				eventTaskResource);
	}
}
