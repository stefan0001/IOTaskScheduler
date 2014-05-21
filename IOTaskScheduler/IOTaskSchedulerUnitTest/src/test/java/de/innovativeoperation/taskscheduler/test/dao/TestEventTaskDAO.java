package de.innovativeoperation.taskscheduler.test.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.dao.EventTaskDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEventTaskDAO {

	@Autowired
	EventTaskDAO eventTaskDAO;

	private IssueDraft issueDraft;
	private EventTask eventTask;
	private EventTask eventTaskNullEvent;
	private EventTask eventTaskNullName;
	private EventTask eventTaskNullIssueDraftSet;
	private Event event;

	@Before
	public void setUp() {
		issueDraft = new IssueDraft("newIssue", "WorkToDo", IssueType.BUG);
		Set<IssueDraft> issueDraftSet = new HashSet<IssueDraft>();
		Set<EventTask> eventTasksSet = new HashSet<EventTask>();
		issueDraftSet.add(issueDraft);

		event = new Event("testEvent", eventTasksSet);
		eventTask = new EventTask("TestSchreiben", issueDraftSet, event);
		eventTaskNullEvent = new EventTask("TestSchreiben", issueDraftSet, null);
		eventTaskNullName = new EventTask(null, issueDraftSet, event);
		eventTaskNullIssueDraftSet = new EventTask("TestSchreiben", null, event);
	}

	@Test
	public void testSaveEventTaskDAO() {
		EventTask savedEventTask = eventTaskDAO.save(eventTask);

		assertTrue(savedEventTask.getId() > 0);
		assertNotNull(eventTaskDAO.findById(savedEventTask.getId()));
	}

	@Test(expected = ConstraintViolationException.class)
	public void saveEventTaskwithNullEvent() {

		eventTaskDAO.save(eventTaskNullEvent);

	}

	@Test
	public void saveEventTaskwithNullIssueDraftSet() {

		EventTask savedEventTask = eventTaskDAO
				.save(eventTaskNullIssueDraftSet);
		assertTrue(savedEventTask.getId() > 0);

	}

	@Test(expected = ConstraintViolationException.class)
	public void saveEventTaskwithNullName() {

		eventTaskDAO.save(eventTaskNullName);

	}

	@Test
	public void testFindByIdEventTaskDAO() {
		eventTask.setName("FindMe");
		EventTask savedEventTask = eventTaskDAO.save(eventTask);
		EventTask foundEventTask = eventTaskDAO
				.findById(savedEventTask.getId());
		assertNotNull(foundEventTask);

		assertTrue(savedEventTask.getName().equals(foundEventTask.getName()));
		assertTrue(savedEventTask.equals(foundEventTask));

		assertNull(eventTaskDAO.findById(8888));
		assertNull(eventTaskDAO.findById(0));
		assertNull(eventTaskDAO.findById(-1));
		assertNull(eventTaskDAO.findById(Integer.MAX_VALUE));
		assertNull(eventTaskDAO.findById(Integer.MIN_VALUE));

	}

	@Test
	public void testFetchAllEventTaskDAO() {

		assertTrue(eventTaskDAO.fetchAll().isEmpty());
		EventTask savedEventTaskTestSchreiben = eventTaskDAO.save(eventTask);
		eventTask.setName("SchreibeTests");
		EventTask savedEventTaskSchreibeTest = eventTaskDAO.save(eventTask);
		List<EventTask> eventTasks = eventTaskDAO.fetchAll();
		assertFalse(eventTasks.isEmpty());
		assertTrue(eventTasks.get(0).getName()
				.equals(savedEventTaskTestSchreiben.getName()));
		assertTrue(eventTasks.get(1).getName()
				.equals(savedEventTaskSchreibeTest.getName()));
	}

	@Test
	public void testRemoveEventTaskDAO() {
		EventTask savedEventTask = eventTaskDAO.save(eventTask);

		assertTrue(savedEventTask.getId() > 0);
		eventTaskDAO.remove(savedEventTask);
		assertNull(eventTaskDAO.findById(savedEventTask.getId()));
	}
}
