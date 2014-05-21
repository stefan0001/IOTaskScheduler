package de.innovativeoperation.taskscheduler.test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.innovativeoperation.taskscheduler.test.MyUtil;
import de.sep.innovativeoperation.taskscheduler.dao.EventDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;


@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEventDAO {

	@Autowired
	EventDAO eventDAO;

	private Event event;
	private Set<EventTask> eventTasksSet;
	private int maxNameLength = 100;

	@Before
	public void setUp() {
		eventTasksSet = new HashSet<EventTask>();
		event = new Event();
		event.setId(0);
		event.setEventTasks(eventTasksSet);
		event.setName("Tests schreiben");

	}

	@Test
	public void testSaveEvent() {
		Event savedEvent = eventDAO.save(event);
		assertTrue(savedEvent.getId() > 0);
	}

	@Test
	public void testFindEventById() {
		Event savedEvent = eventDAO.save(event);
		Event foundEvent = eventDAO.findById(savedEvent.getId());
		assertNotNull(foundEvent);
	}

	@Test
	public void testFetchAll() {
		List<Event> events = eventDAO.fetchAll();
		if (!events.isEmpty())
			for (Event event : events) {
				eventDAO.remove(event);
			}
		events = eventDAO.fetchAll();
		assertTrue(events.isEmpty());
		Event savedEvent = eventDAO.save(event);
		assertTrue(savedEvent.getId() > 0);
		events = eventDAO.fetchAll();
		assertFalse(events.isEmpty());
	}

	@Test
	public void testRemoveEventById() {
		Event savedEvent = eventDAO.save(event);
		int savedEventId = savedEvent.getId();
		assertTrue(savedEventId > 0);
		eventDAO.remove(savedEvent);
		Event removedEvent = eventDAO.findById(savedEventId);
		assertNull(removedEvent);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExceptionAtDeleteAll() {
		eventDAO.deleteAll();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testExceptionAtSaveNullName() {
		event.setName(null);
		eventDAO.save(event);
	}

	@Test(expected = PersistenceException.class)
	public void testExceptionAtSave101letterName() {
		String invalidName = MyUtil.generateSingleCharStringOfLength(
				maxNameLength + 1, "a");
		assertTrue(invalidName.length() == (maxNameLength + 1));
		event.setName(invalidName);
		Event savedEvent = eventDAO.save(event);
		assertNull(savedEvent);
	}

}
