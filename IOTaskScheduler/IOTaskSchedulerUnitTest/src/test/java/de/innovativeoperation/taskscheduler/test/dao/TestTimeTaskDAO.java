package de.innovativeoperation.taskscheduler.test.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

import de.sep.innovativeoperation.taskscheduler.dao.TimeTaskDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestTimeTaskDAO {

	@Autowired
	TimeTaskDAO timeTaskDAO;

	private TimeTask timeTask;
	private TimeTask timeTaskNullFirstFireTime;
	private TimeTask timeTaskNullName;
	private TimeTask timeTaskNullIntervall;
	private TimeTask timeTaskNullIssueDrafts;
	private Calendar calendar;
	private IssueDraft issueDraft;
	private List<TimeTask> timeTaskList;

	@Before
	public void setUp() {
		timeTaskList = timeTaskDAO.fetchAll();
		if (!timeTaskList.isEmpty())
			for (TimeTask timeTaskElement : timeTaskList) {
				timeTaskDAO.remove(timeTaskElement);
			}
		calendar = new GregorianCalendar(2014, Calendar.MARCH, 8);
		issueDraft = new IssueDraft("newIssue", "WorkToDo", IssueType.BUG);
		Set<IssueDraft> issueDraftSet = new HashSet<IssueDraft>();
		issueDraftSet.add(issueDraft);
		timeTask = new TimeTask("timeTask");
		timeTask.setFirstFireTime(calendar);
		timeTask.setIntervall(100);
		timeTask.setIssueDrafts(issueDraftSet);
		timeTask.setId(0);

		timeTaskNullFirstFireTime = new TimeTask("timeTaskNullFirstFireTime");
		timeTaskNullFirstFireTime.setFirstFireTime(null);
		timeTaskNullFirstFireTime.setIntervall(100);
		timeTaskNullFirstFireTime.setIssueDrafts(issueDraftSet);

		timeTaskNullName = new TimeTask(null);
		timeTaskNullName.setFirstFireTime(calendar);
		timeTaskNullName.setIntervall(100);
		timeTaskNullName.setIssueDrafts(issueDraftSet);

		timeTaskNullIntervall = new TimeTask("timeTaskNullIntervall");
		timeTaskNullIntervall.setFirstFireTime(calendar);
		timeTaskNullIntervall.setIntervall(0);
		timeTaskNullIntervall.setIssueDrafts(issueDraftSet);

		timeTaskNullIssueDrafts = new TimeTask("timeTaskNullIssueDrafts");
		timeTaskNullIssueDrafts.setFirstFireTime(calendar);
		timeTaskNullIssueDrafts.setIntervall(100);
		timeTaskNullIssueDrafts.setIssueDrafts(null);

	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveTimeTasksWithNullFirstFireTimeInTimeTaskDAO() {

		timeTaskDAO.save(timeTaskNullFirstFireTime);
	}

	@Test
	public void testSaveTimeTasksWithNullIntervallInTimeTaskDAO() {

		TimeTask savedTimeTask = timeTaskDAO.save(timeTaskNullIntervall);

		assertTrue(savedTimeTask.getId() > 0);
		assertNotNull(timeTaskDAO.findById(savedTimeTask.getId()));
	}

	@Test
	public void testSaveTimeTasksWithNullIssueDraftsInTimeTaskDAO() {
		TimeTask savedTimeTask = timeTaskDAO.save(timeTaskNullIssueDrafts);
		assertNotNull(savedTimeTask);
		assertTrue(savedTimeTask.getId() > 0);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveTimeTasksWithNullNameInTimeTaskDAO() {

		timeTaskDAO.save(timeTaskNullName);

	}

	@Test
	public void testSaveTimeTaskDAO() {
		assertTrue(timeTaskDAO.fetchAll().isEmpty());
		TimeTask savedTimeTask = timeTaskDAO.save(timeTask);
		assertTrue(savedTimeTask.getId() > 0);
		assertFalse(timeTaskDAO.fetchAll().isEmpty());
		assertNotNull(timeTaskDAO.findById(savedTimeTask.getId()));
	}

	@Test
	public void testFindByIdTimeTaskDAO() {
		TimeTask savedTimeTask = timeTaskDAO.save(timeTask);
		assertTrue(savedTimeTask.getId() > 0);
		assertNotNull(timeTaskDAO.findById(savedTimeTask.getId()));
		assertNull(timeTaskDAO.findById(0));
	}

	/**
	 * Saves two different TimeTasks into TimeTaskDAO and compare to returned
	 * fetchAll() List
	 */
	@Test
	public void testFetchAllTimeTaskDAO() {
		// SETUP another TimeTask
		Set<IssueDraft> issueDraftSet = new HashSet<IssueDraft>();
		issueDraftSet.add(issueDraft);
		TimeTask otherTimeTask = new TimeTask("otherTimeTask");
		otherTimeTask.setFirstFireTime(calendar);
		otherTimeTask.setIntervall(100);
		otherTimeTask.setIssueDrafts(issueDraftSet);

		// CHECK if TimeTaskDAO is empty
		assertTrue(timeTaskDAO.fetchAll().isEmpty());

		// SAVE TimeTasks
		TimeTask savedTimeTask = timeTaskDAO.save(timeTask);

		TimeTask savedOtherTimeTask = timeTaskDAO.save(otherTimeTask);

		// CHECK if TimeTaskDAO is not empty
		assertFalse(timeTaskDAO.fetchAll().isEmpty());

		// CHECK if fetchAll gives back all saved TimeTasks
		List<TimeTask> timeTasks = timeTaskDAO.fetchAll();

		// CHECK if equalsMethod is working
		assertFalse(savedTimeTask.equals(timeTasks.get(1)));

		// CHECK compare returned List to created TimeTasks
		assertTrue(savedTimeTask.equals(timeTasks.get(0)));
		assertTrue(savedTimeTask.getName().equals(timeTask.getName()));
		assertTrue(savedOtherTimeTask.equals(timeTasks.get(1)));
		assertTrue(savedOtherTimeTask.getName().equals(otherTimeTask.getName()));
	}

}
