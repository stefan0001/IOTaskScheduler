package de.innovativeoperation.taskscheduler.test.service.validation;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.innovativeoperation.taskscheduler.test.MyUtil;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNotValidException;
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNullException;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.service.validation.TimeTaskValidationService;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TimeTaskValidationServiceTest {

	private TimeTask timeTask;
	private int maxNameLength = 100;
	
	@Autowired
	TimeTaskValidationService timeTaskValidationService;

	@Before
	public void setUp() {
		timeTask = new TimeTask();
		timeTask.setName("foo");
		Calendar firstFireTime = Calendar.getInstance();
		timeTask.setFirstFireTime(firstFireTime);
		timeTask.setIntervall(3600);
	}

	
	@Test
	public void testCheckGoodName() {
		timeTask.setName(MyUtil.generateSingleCharStringOfLength(maxNameLength,"a"));
		assertTrue(timeTask.getName().length() == maxNameLength);
		
		timeTaskValidationService.checkObject(timeTask);		
	}
	
	@Test(expected = ValueIsNullException.class)
	public void testCheckNullName() {
		timeTask.setName(null);
		timeTaskValidationService.checkObject(timeTask);
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCheckOverlength101Name() {
		timeTask.setName(MyUtil.generateSingleCharStringOfLength(maxNameLength+1,"a"));
		timeTaskValidationService.checkObject(timeTask);
	}

	@Test
	public void testCheckEmptyName() {
		timeTask.setName("");
		timeTaskValidationService.checkObject(timeTask);
	}

	@Test(expected = ValueIsNullException.class)
	public void testCheckNullFirstFireTime() {
		timeTask.setFirstFireTime(null);
		timeTaskValidationService.checkObject(timeTask);
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCheckIntervallUnder3600() {
		timeTask.setIntervall(3599);
		timeTaskValidationService.checkObject(timeTask);
	}	

	@Test//(expected = ValueIsNotValidException.class)
	public void testCheckIntervallMAX_VALUE() {
		timeTask.setIntervall(Integer.MAX_VALUE);
		timeTaskValidationService.checkObject(timeTask);
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCheckIntervallMIN_VALUE() {
		timeTask.setIntervall(Integer.MIN_VALUE);
		timeTaskValidationService.checkObject(timeTask);
	}

}
