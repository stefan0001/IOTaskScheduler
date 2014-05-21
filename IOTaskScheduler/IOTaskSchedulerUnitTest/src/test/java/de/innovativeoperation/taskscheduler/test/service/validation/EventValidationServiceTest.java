package de.innovativeoperation.taskscheduler.test.service.validation;


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
import de.sep.innovativeoperation.taskscheduler.service.validation.EventValidationService;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EventValidationServiceTest {

	
	@Autowired
	EventValidationService eventValidationService;

	private Event event;
	private int maxNameLength = 100;
	
	@Before
	public void setUp() throws Exception {
		event = new Event("eventName");
		event.setId(0);
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCheckNullNameObject() {
		event.setName(null);
		eventValidationService.checkObject(event);
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCheckOverlengthNameObject() {
		event.setName(MyUtil.generateSingleCharStringOfLength(maxNameLength+1, "a"));		
		eventValidationService.checkObject(event);
	}
	
	@Test
	public void testCheckMaxlengthNameObject() {
		event.setName(MyUtil.generateSingleCharStringOfLength(maxNameLength, "a"));		
		eventValidationService.checkObject(event);
	}

	@Test
	public void testCheckEmptyNameObject() {
		event.setName("");		
		eventValidationService.checkObject(event);
	}

}
