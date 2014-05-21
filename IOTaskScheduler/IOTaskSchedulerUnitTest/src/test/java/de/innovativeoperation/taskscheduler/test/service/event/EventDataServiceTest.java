package de.innovativeoperation.taskscheduler.test.service.event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.innovativeoperation.taskscheduler.test.MyUtil;
import de.sep.innovativeoperation.taskscheduler.exception.http.ResourceNotFoundException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNotValidException;
import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;
import de.sep.innovativeoperation.taskscheduler.service.event.EventDataService;
import de.sep.innovativeoperation.taskscheduler.service.eventtask.EventTaskDataService;
import de.sep.innovativeoperation.taskscheduler.service.issuedraft.IssueDraftDataService;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityDataService;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class EventDataServiceTest {

	@Autowired
	EventDataService eventDataService;
	
	@Autowired
	EventTaskDataService eventTaskDataService;
	
	@Autowired
	IssueDraftDataService issueDraftDataService;
	
	@Autowired
	IssueEntityDataService issueEntityDataService;

	private Event event;
	private Event otherEvent;
	private int maxNameLength = 100;

	@Before
	public void setUp() throws Exception {
		event = new Event();
		event.setName("foo");
		event.setId(0);
		event.setEventTasks(null);

		otherEvent = new Event("baa", null);
	}

	@Test
	public void testCreateGoodEvent() {
		Event savedEvent = new Event("baa", null);
		savedEvent = eventDataService.createEvent(event);
		assertTrue(savedEvent.getId() > 0);
		assertTrue(savedEvent.getName().equals("foo"));
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCreateNullNameEvent() {
		event.setName(null);
		eventDataService.createEvent(event);
	}

	@Test
	public void testCreateMaxNameEvent() {

		String hugeName = MyUtil.generateSingleCharStringOfLength(maxNameLength,"a");
		event.setName(hugeName);

		assertTrue(event.getId() == 0);
		Event savedEvent = eventDataService.createEvent(event);

		assertTrue(savedEvent.getId() > 0);
		assertFalse(savedEvent.getName().equals("foo"));
		assertTrue(savedEvent.getName().equals(hugeName));
		assertTrue(savedEvent.getName().length() == hugeName.length());
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCreateOverSizedNameEvent() {

		String overSizedeName = MyUtil.generateSingleCharStringOfLength(maxNameLength + 1,"a"
				);
		event.setName(overSizedeName);
		eventDataService.createEvent(event);
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCreateMultipleNameEvent() {

		for (int i = 0; i < 101; i++) {
			event.setName(MyUtil.generateSingleCharStringOfLength(i,"a"));
			Event savedEvent = new Event();
			savedEvent = eventDataService.createEvent(event);
			assertNotNull(savedEvent);
			
			assertTrue(savedEvent.getId() > 0);
		}
		event.setName(MyUtil.generateSingleCharStringOfLength(101,"a"));
		assertTrue(event.getName().length() == (maxNameLength + 1));
		eventDataService.createEvent(event);
	}
	@Test(expected = ValueIsNotValidException.class)
	public void testCreateMultipleNameUMLAUTEEvent() {

		for (int i = 0; i < 101; i++) {
			event.setName(MyUtil.generateSingleCharStringOfLength(i,"a"));
			Event savedEvent = new Event();
			savedEvent = eventDataService.createEvent(event);
			assertNotNull(savedEvent);
			assertTrue(savedEvent.getId()>0);			
		}
		event.setName(MyUtil.generateSingleCharStringOfLength(101,"a"));
		assertTrue(event.getName().length() == (maxNameLength + 1));
		eventDataService.createEvent(event);
	}
	
	@Test(expected = ValueIsNotValidException.class)
	public void testCreateMultipleNameWeirdEvent() {

		for (int i = 0; i < 101; i++) {
			event.setName(MyUtil.generateSingleCharStringOfLength(i,"a"));
			Event savedEvent = new Event();
			savedEvent = eventDataService.createEvent(event);
			assertNotNull(savedEvent);
			assertTrue(savedEvent.getId()>0);
			
			assertTrue(savedEvent.getId() > 0);
		}
		event.setName(MyUtil.generateSingleCharStringOfLength(101,"a"));
		assertTrue(event.getName().length() == (maxNameLength + 1));
		eventDataService.createEvent(event);
	}
	
	@Test//(expected = ValueIsNotValidException.class)
	public void testCreateMultipleNameSqlEvent() {

		event.setName("DROP SCHEMA TASKSCHEDULER;");
		Event savedEvent = eventDataService.createEvent(event);
		assertTrue(savedEvent.getId()>0);
		assertTrue(savedEvent.getName().equals("DROP SCHEMA TASKSCHEDULER;"));
	}

	@Test
	public void testUpdateGoodEvent() {
		assertTrue(event.getId() == 0);
		Event savedEvent = eventDataService.createEvent(event);
		int savedId = savedEvent.getId();
		assertTrue(savedId > 0);
		Event updatedEvent = eventDataService.updateEvent(savedId, otherEvent);
		assertTrue(updatedEvent.getId() > 0);
		assertTrue(updatedEvent.getName().equals(otherEvent.getName()));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateEventWithNull() {
		assertTrue(event.getId() == 0);
		Event savedEvent = eventDataService.createEvent(event);
		int savedId = savedEvent.getId();
		assertTrue(savedId > 0);
		eventDataService.updateEvent(savedId, null);		
	}
	@Test
	public void testUpdateEventWithNoName() {
		assertTrue(event.getId() == 0);
		Event savedEvent = eventDataService.createEvent(event);
		int savedId = savedEvent.getId();
		assertTrue(savedId > 0);
		otherEvent.setName("");
		Event updatedEvent = eventDataService.updateEvent(savedId, otherEvent);
		assertTrue(updatedEvent.getId() > 0);
		assertTrue(updatedEvent.getName().equals(otherEvent.getName()));
	}

	

	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateUnsavedEventWithSavedEvent() {
		
		Event otherSavedEvent = eventDataService.createEvent(otherEvent);
		assertTrue(otherSavedEvent.getId()>0);
		
		eventDataService.updateEvent(event.getId(), otherEvent);
	}
	@Test
	public void testUpdateSavedEventWithUnsavedEvent() {
		
		Event savedEvent = eventDataService.createEvent(event);
		assertTrue(savedEvent.getId()>0);
		assertTrue(otherEvent.getId()==0);
		Event updatedEvent = eventDataService.updateEvent(savedEvent.getId(), otherEvent);
		assertTrue(updatedEvent.getName().equals(otherEvent.getName()));
		
	}
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateUnsavedEventWithUnsavedEvent() {
		eventDataService.updateEvent(event.getId(), otherEvent);
	}

	@Test
	public void testTrigger() {
		Event savedEvent = eventDataService.createEvent(event);
		savedEvent.setEventTasks(new HashSet<EventTask>());
		EventTask newEventTask = new EventTask("foo");
		assertTrue(savedEvent.getId()>0);
		assertNotNull(newEventTask);
		assertNotNull(eventTaskDataService);
		EventTask savedEventTask = eventTaskDataService.createEventTask(savedEvent.getId(),newEventTask );
		
		IssueDraft relatedIssueDraft = eventTaskDataService.createRelationEventTaskIssueDraft(savedEventTask.getId(), new IssueDraft(	"issueTriggeredByEvent!", "thisIsAwesome", IssueType.BUG));
		assertTrue(relatedIssueDraft.getId()>0);
		eventDataService.trigger(savedEvent.getId());
		List<IssueEntity> issueEntities = issueEntityDataService.getAll();
		assertFalse(issueEntities.isEmpty());
		assertFalse(relatedIssueDraft.getIssueEntities().isEmpty());
		assertTrue(issueEntities.containsAll(relatedIssueDraft.getIssueEntities()));
	}
}
