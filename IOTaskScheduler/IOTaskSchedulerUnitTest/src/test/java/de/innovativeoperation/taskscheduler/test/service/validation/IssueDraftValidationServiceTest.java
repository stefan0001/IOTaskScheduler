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
import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNullException;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;
import de.sep.innovativeoperation.taskscheduler.service.validation.IssueDraftValidationService;

/**
 * Tests the proper validation by IssueDraftValidationService
 * 
 * @author Joscha Zander
 *
 */
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class IssueDraftValidationServiceTest {

	private IssueDraft issueDraft;
	private int maxNameLenght = 100;
	private int maxDescriptionLenght = 500;

	@Autowired
	IssueDraftValidationService issueDraftValidationService;

	@Before
	public void setUp() throws Exception {
		issueDraft = new IssueDraft();
		issueDraft.setIssueName("foo");
		issueDraft.setIssueDescription("foo");
		issueDraft.setIssueType(IssueType.BUG);
	}

	@Test
	public void testSetUpObject() {
		issueDraftValidationService.checkObject(issueDraft);
	}

	@Test(expected = ValueIsNullException.class)
	public void testCheckNullNameObject() {
		issueDraft.setIssueName(null);
		issueDraftValidationService.checkObject(issueDraft);
	}

	@Test
	public void testCheckMaxlength101IssueNameObject() {
		issueDraft.setIssueName(MyUtil.generateSingleCharStringOfLength(
				maxNameLenght, "a"));
		issueDraftValidationService.checkObject(issueDraft);
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCheckOverlength101IssueNameObject() {
		issueDraft.setIssueName(MyUtil.generateSingleCharStringOfLength(
				maxNameLenght + 1, "a"));
		issueDraftValidationService.checkObject(issueDraft);
	}

	@Test(expected = ValueIsNullException.class)
	public void testCheckNullIssueDescription() {
		issueDraft.setIssueDescription(null);
		issueDraftValidationService.checkObject(issueDraft);
	}

	@Test
	public void testCheckMaxlength501IssueDescription() {
		issueDraft.setIssueDescription(MyUtil.generateSingleCharStringOfLength(
				maxDescriptionLenght, "a"));
		issueDraftValidationService.checkObject(issueDraft);
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testCheckOverlength501IssueDescription() {
		issueDraft.setIssueDescription(MyUtil.generateSingleCharStringOfLength(
				maxDescriptionLenght + 1, "a"));
		issueDraftValidationService.checkObject(issueDraft);
	}

	@Test(expected = ValueIsNullException.class)
	public void testCheckNullIssueType() {
		issueDraft.setIssueType(null);
		issueDraftValidationService.checkObject(issueDraft);
	}
}
