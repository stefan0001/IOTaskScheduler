package de.innovativeoperation.taskscheduler.test.service.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.exception.validation.ValueIsNullException;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.service.validation.IssueEntityValidationService;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class IssueEntityValidationServiceTest {
	
	private IssueEntity issueEntity;
	
	@Autowired
	IssueEntityValidationService issueEntityValidationService;

	@Before
	public void setUp() throws Exception {
		issueEntity = new IssueEntity();
		issueEntity.setIssueResolution(IssueResolution.DONE);
		issueEntity.setIssueStatus(IssueStatus.RESOLVED);
	}

	@Test(expected = ValueIsNullException.class)
	public void testCheckNullResolution() {
		issueEntity.setIssueResolution(null);
		issueEntityValidationService.checkObject(issueEntity);
	}

	@Test(expected = ValueIsNullException.class)
	public void testCheckNullIssueStatus() {
		issueEntity.setIssueStatus(null);		
		issueEntityValidationService.checkObject(issueEntity);
	}	

}
