package de.innovativeoperation.taskscheduler.test.service.timetask;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNotValidException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNullException;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.service.issuedraft.IssueDraftDataService;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityDataService;
import de.sep.innovativeoperation.taskscheduler.service.timetask.TimeTaskDataService;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TimeTaskDataServiceTest {



		@Autowired
		TimeTaskDataService timeTaskDataService;
		@Autowired
		IssueEntityDataService issueEntityDataService;
		
		@Autowired
		IssueDraftDataService issueDraftDataService;

		@Test
		public void testCreateGoodTimeTask() {
			TimeTask timeTask = new TimeTask("name", Calendar.getInstance(), Calendar.getInstance(),3600);
			TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);
			assertNotNull(savedTimeTask);
			assertTrue(savedTimeTask.getId()>0);
		}
		@Test(expected = ValueIsNotValidException.class)
		public void testCreateTimeTaskWithOnlyName() {
			TimeTask timeTask = new TimeTask("name");
			timeTaskDataService.createTimeTask(timeTask);
		}
		
		@Test(expected = ValueIsNullException.class)
		public void testCreateBlankTimeTask() {
			TimeTask timeTask = new TimeTask();
			timeTaskDataService.createTimeTask(timeTask);
		}
		
		@Test
		public void testCreateTimeTaskWithNameAndIntervall() {
			TimeTask timeTask = new TimeTask("name");
			timeTask.setIntervall(3600);
			TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);
			assertNotNull(savedTimeTask);
			assertNotNull(savedTimeTask.getFirstFireTime());
			assertNotNull(savedTimeTask.getNextFireTime());
			assertTrue(savedTimeTask.getId()>0);
		}
		
		@Test
		public void testCreateTimeTaskWithNextFireTimeBehindFirstFireTime() {
			Calendar firstFireTime = Calendar.getInstance();
			firstFireTime.add(Calendar.HOUR, 1);
			TimeTask timeTask = new TimeTask("name",firstFireTime , Calendar.getInstance(),3600);
			TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);
			assertNotNull(savedTimeTask);
			assertTrue(savedTimeTask.getId()>0);
		}
		
		@Test
		public void testCreateTimeTaskHugeIntervall() {
			TimeTask timeTask = new TimeTask("name");
			timeTask.setIntervall(Integer.MAX_VALUE);
			TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);
			assertNotNull(savedTimeTask);
			assertNotNull(savedTimeTask.getFirstFireTime());
			assertNotNull(savedTimeTask.getNextFireTime());
			assertTrue(savedTimeTask.getId()>0);
		}	
	
	@Test
	public void testUpdateTimeTask() {
		TimeTask timeTask = new TimeTask("name", Calendar.getInstance(), Calendar.getInstance(),3600);
		TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);
		Calendar fireTime2 = Calendar.getInstance();
		fireTime2.add(Calendar.HOUR,1);
		TimeTask timeTask2 = new TimeTask("name2", fireTime2, null,7200);
		
		TimeTask updatedTimeTask = timeTaskDataService.updateTimeTask(savedTimeTask.getId(), timeTask2);
		assertTrue(updatedTimeTask.getFirstFireTime().equals(timeTask2.getFirstFireTime()));
		assertTrue(updatedTimeTask.getIntervall()==timeTask2.getIntervall());
		assertTrue(updatedTimeTask.getName()==timeTask2.getName());
		assertTrue(updatedTimeTask.getIssueDrafts().containsAll(timeTask2.getIssueDrafts()));
		assertTrue(savedTimeTask.getFireCount()==(timeTask2.getFireCount()));
	}

	@Test
	public void testCreateRelationTimeTaskIssueDraft() {
		TimeTask timeTask = new TimeTask("name", Calendar.getInstance(), Calendar.getInstance(),3600);
		TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);
		IssueDraft issueDraft = new IssueDraft( "name", "linkMe", IssueType.BUG);
		IssueDraft savedIssueDraft = issueDraftDataService.createIssueDraft(issueDraft);

		IssueDraft relatedIssueDraft = timeTaskDataService.createRelationTimeTaskIssueDraft(savedTimeTask.getId(), savedIssueDraft);
		assertTrue(relatedIssueDraft.getTimeTasks().contains(savedTimeTask));
	}
	
	@Test(expected = NullPointerException.class)
	public void testCreateRelationTimeTaskNullIssueDraft() {
		TimeTask timeTask = new TimeTask("name", Calendar.getInstance(), Calendar.getInstance(),3600);
		TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);

		IssueDraft relatedIssueDraft = timeTaskDataService.createRelationTimeTaskIssueDraft(savedTimeTask.getId(), null);
		assertTrue(relatedIssueDraft.getTimeTasks().contains(savedTimeTask));
	}

	@Test
	public void testDeleteRelationTimeTaskIssueDraft() {
		TimeTask timeTask = new TimeTask("name", Calendar.getInstance(), Calendar.getInstance(),3600);
		TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);
		IssueDraft issueDraft = new IssueDraft( "name", "linkMe", IssueType.BUG);
		IssueDraft savedIssueDraft = issueDraftDataService.createIssueDraft(issueDraft);

		IssueDraft relatedIssueDraft = timeTaskDataService.createRelationTimeTaskIssueDraft(savedTimeTask.getId(), savedIssueDraft);
		assertTrue(relatedIssueDraft.getTimeTasks().contains(savedTimeTask));
		
		timeTaskDataService.deleteRelationTimeTaskIssueDraft(savedTimeTask.getId(), savedIssueDraft.getId());
		assertFalse(savedIssueDraft.getTimeTasks().contains(savedTimeTask));
	}

	@Test
	public void testRemoveBidirctionalRelationsTimeTask() {
		TimeTask timeTask = new TimeTask("name", Calendar.getInstance(), Calendar.getInstance(),3600);
		TimeTask savedTimeTask = timeTaskDataService.createTimeTask(timeTask);
		IssueDraft issueDraft = new IssueDraft( "name", "linkMe", IssueType.BUG);
		IssueDraft savedIssueDraft = issueDraftDataService.createIssueDraft(issueDraft);

		IssueDraft relatedIssueDraft = timeTaskDataService.createRelationTimeTaskIssueDraft(savedTimeTask.getId(), savedIssueDraft);
		assertTrue(relatedIssueDraft.getTimeTasks().contains(savedTimeTask));
		
		timeTaskDataService.removeBidirctionalRelations(savedTimeTask);
		assertFalse(savedIssueDraft.getTimeTasks().contains(savedTimeTask));
	}

}
