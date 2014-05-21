package de.innovativeoperation.taskscheduler.test.service.validation;

import java.util.HashSet;
import java.util.Set;

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
import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.service.validation.EventTaskValidationService;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EventTaskValidationServiceTest {
	
@Autowired
EventTaskValidationService eventTaskValidationService;

private EventTask eventTask;
private Event event;
private Set<IssueDraft> issueDrafts;
private int maxNameLength = 100;

	@Before
	public void setUp() throws Exception {
		event = new Event("eventName");
		eventTask = new EventTask();
		issueDrafts=new HashSet<IssueDraft>();
		eventTask.setId(42);
		eventTask.setName("startingTestName");
		eventTask.setEvent(event);
		eventTask.setIssueDrafts(issueDrafts);
	}
	
	@Test(expected = ValueIsNotValidException.class)
	public void testCheckNullNameObject() {		
		eventTask.setName(null);
		eventTaskValidationService.checkObject(eventTask);
	}
	
	@Test(expected = ValueIsNotValidException.class)
	public void testCheckOverlengthNameObject() {		
		eventTask.setName(MyUtil.generateSingleCharStringOfLength(maxNameLength +1,"a"));
		eventTaskValidationService.checkObject(eventTask);
	}	

	@Test
	public void testCheckMaxlengthNameObject() {		
		eventTask.setName(MyUtil.generateSingleCharStringOfLength(maxNameLength ,"a"));
		eventTaskValidationService.checkObject(eventTask);
	}	
	
	@Test
	public void testCheckEmptyNameObject() {		
			eventTask.setName("");
			eventTaskValidationService.checkObject(eventTask);
		
	}
}
