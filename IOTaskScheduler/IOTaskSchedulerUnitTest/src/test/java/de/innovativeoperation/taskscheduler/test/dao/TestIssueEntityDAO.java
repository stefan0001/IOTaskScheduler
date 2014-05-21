package de.innovativeoperation.taskscheduler.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.dao.IssueEntityDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestIssueEntityDAO {

	@Autowired
	IssueEntityDAO issueEntityDAO;

	private IssueEntity issueEntity;

	private IssueDraft issueDraft;

	@Before
	public void setUp() throws Exception {
		issueDraft = new IssueDraft("newIssue", "WorkToDo", IssueType.BUG);
		issueEntity = new IssueEntity(IssueStatus.NEW,
				IssueResolution.CANNOT_REPRODUCE, issueDraft);	
	}

	@Test
	public void testSaveAndReadIssueEntityFromIssueEntityDAO() throws Exception {
		// SAVE
		IssueEntity issueEntitySaved = issueEntityDAO.save(issueEntity);

		// FIND BACK
		IssueEntity issueEntityDB = issueEntityDAO.findById(issueEntitySaved
				.getId());

		// CHECK

		// After Saving an Entity got a ID
		assertTrue(issueEntitySaved.getId() > 0);

		// Check IssueStatus
		assertTrue(issueEntity.getIssueStatus() == issueEntityDB
				.getIssueStatus());

		// Check IssueResolution
		assertTrue(issueEntity.getIssueResolution() == issueEntityDB
				.getIssueResolution());
	}

	@Test
	public void testSaveAndDeleteIssueEntityFromIssueEntityDAO() {
		// SAVE
		IssueEntity issueEntitySaved = issueEntityDAO.save(issueEntity);

		// CHECK
		// After Saving an Entity got a ID
		// And Save the ID for later Check if Entity with that ID is deleted
		int issueEntitySavedID = issueEntitySaved.getId();
		assertTrue(issueEntitySavedID > 0);

		// DELETE
		issueEntityDAO.remove(issueEntitySaved);
		// CHECK
		assertNull(issueEntityDAO.findById(issueEntitySavedID));

	}

	@Test
	public void testUpdateIssueEntityOnIssueEntityDAO() {
		// SAVE
		IssueEntity issueEntitySaved = issueEntityDAO.save(issueEntity);
		issueEntitySaved.setIssueStatus(IssueStatus.REOPENED);
		issueEntitySaved = issueEntityDAO.save(issueEntitySaved);
		// CHECK
		// After Saving an Entity got a ID
		// And Save the ID for later Check if Entity with that ID is deleted
		assertTrue(issueEntitySaved.getId() > 0);
		assertTrue(issueEntitySaved.getIssueStatus().equals(
				IssueStatus.REOPENED));

		// CHECK
		assertNotNull(issueEntityDAO.findById(issueEntitySaved.getId()));

	}

	@Test
	public void testFetchAllIssueEntitiesFromIssueEntityDAO() {
		IssueDraft issueDraft2 = new IssueDraft("newIssue2", "WorkToDo2",
				IssueType.BUG);
		IssueEntity issueEntity2 = new IssueEntity(IssueStatus.NEW,
				IssueResolution.DONE, issueDraft2);
		IssueDraft issueDraft3 = new IssueDraft("newIssue3", "WorkToDo3",
				IssueType.BUG);
		IssueEntity issueEntity3 = new IssueEntity(IssueStatus.NEW,
				IssueResolution.DUPLICATE, issueDraft3);

		if (!issueEntityDAO.fetchAll().isEmpty())
			deleteAllIssuesInIssueEntityDAO();
		
		assertTrue(issueEntityDAO.fetchAll().isEmpty());

		// SAVE
		IssueEntity issueEntitySaved = issueEntityDAO.save(issueEntity);
		IssueEntity issueEntitySaved2 = issueEntityDAO.save(issueEntity2);
		IssueEntity issueEntitySaved3 = issueEntityDAO.save(issueEntity3);

		assertFalse(issueEntityDAO.fetchAll().isEmpty());

		// CHECK
		// After Saving all Entities got an ID
		List<IssueEntity> issueEntities = issueEntityDAO.fetchAll();

		for (IssueEntity issueEntity : issueEntities) {
			assertTrue(issueEntity.getId() > 0);
		}

		// Save the ID for later Check if Entity with ID is deleted
		int issueEntitySavedID = issueEntitySaved.getId();
		int issueEntitySavedID2 = issueEntitySaved2.getId();
		int issueEntitySavedID3 = issueEntitySaved3.getId();

		// CHECK for ID in result of fetchAll()
		assertTrue(issueEntities.get(0).getId() == (issueEntitySavedID));
		assertTrue(issueEntities.get(0).getIssueDraft()
				.equals(issueEntitySaved.getIssueDraft()));
		assertTrue(issueEntities.get(0).getIssueResolution()
				.equals(issueEntitySaved.getIssueResolution()));
		assertTrue(issueEntities.get(0).getIssueStatus()
				.equals(issueEntitySaved.getIssueStatus()));

		assertTrue(issueEntities.get(1).getId() == (issueEntitySavedID2));
		assertTrue(issueEntities.get(1).getIssueDraft()
				.equals(issueEntitySaved2.getIssueDraft()));
		assertTrue(issueEntities.get(1).getIssueResolution()
				.equals(issueEntitySaved2.getIssueResolution()));
		assertTrue(issueEntities.get(1).getIssueStatus()
				.equals(issueEntitySaved2.getIssueStatus()));

		assertTrue(issueEntities.get(2).getId() == (issueEntitySavedID3));
		assertTrue(issueEntities.get(2).getIssueDraft()
				.equals(issueEntitySaved3.getIssueDraft()));
		assertTrue(issueEntities.get(2).getIssueResolution()
				.equals(issueEntitySaved3.getIssueResolution()));
		assertTrue(issueEntities.get(2).getIssueStatus()
				.equals(issueEntitySaved3.getIssueStatus()));

		issueEntityDAO.remove(issueEntitySaved2);
		assertNull(issueEntityDAO.findById(issueEntitySavedID2));

		issueEntities = issueEntityDAO.fetchAll();
		
		assertTrue(issueEntities.get(0).getId() == (issueEntitySavedID));
		assertTrue(issueEntities.get(1).getId() == (issueEntitySavedID3));

	}

	

	private void deleteAllIssuesInIssueEntityDAO() {
		List<IssueEntity> issueEntities = issueEntityDAO.fetchAll();
		for (IssueEntity issueEntity : issueEntities) {
			
			issueEntityDAO.remove(issueEntityDAO.findById(issueEntity.getId()));
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveNullInIssueEntityDAO() {
		issueEntityDAO.save(null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveNullIssueDraftInIssueEntityDAO() {
		issueEntity.setIssueDraft(null);
		issueEntityDAO.save(issueEntity);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveNullIssueResolutionInIssueEntityDAO() {
		issueEntity.setIssueResolution(null);
		issueEntityDAO.save(issueEntity);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveNullIssueStatusInIssueEntityDAO() {
		issueEntity.setIssueStatus(null);
		issueEntityDAO.save(issueEntity);
	}
}
