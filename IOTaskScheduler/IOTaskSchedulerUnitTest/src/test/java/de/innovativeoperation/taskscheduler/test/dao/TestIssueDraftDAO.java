package de.innovativeoperation.taskscheduler.test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
import de.sep.innovativeoperation.taskscheduler.dao.IssueDraftDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestIssueDraftDAO {

	@Autowired
	IssueDraftDAO issueDraftDAO;

	private IssueDraft issueDraft;
	private int maxNameLength = 100;
	private int maxDescriptionLength = 500;

	@Before
	public void setUp() throws Exception {
		issueDraft = new IssueDraft("newIssue", "WorkToDo", IssueType.BUG);

	}

	@Test
	public void testSaveAndReadIssueEntityFromIssueDraftDAO() throws Exception {
		// SAVE
		IssueDraft issueDraftSaved = issueDraftDAO.save(issueDraft);

		// FIND BACK
		IssueDraft issueDraftDB = issueDraftDAO.findById(issueDraftSaved
				.getId());

		// CHECK

		// After Saving an Entity got a ID
		assertTrue(issueDraftSaved.getId() > 0);

		// Check ID
		assertTrue(issueDraftSaved.getId() == issueDraftDB.getId());

		// Check IssueStatus
		assertTrue(issueDraftSaved.getIssueDescription() == issueDraftDB
				.getIssueDescription());

		// Check IssueResolution
		assertTrue(issueDraftSaved.getIssueName() == issueDraftDB
				.getIssueName());
	}

	@Test
	public void testSaveAndDeleteIssueEntityFromIssueDraftDAO() {
		// SAVE
		IssueDraft issueDraftSaved = issueDraftDAO.save(issueDraft);

		// CHECK
		// After Saving an Entity got a ID
		// And Save the ID for later Check if Entity with that ID is deleted
		int issueDraftSavedID = issueDraftSaved.getId();
		assertTrue(issueDraftSavedID > 0);

		// DELETE
		issueDraftDAO.remove(issueDraftSaved);
		// CHECK
		assertNull(issueDraftDAO.findById(issueDraftSavedID));

	}

	@Test
	public void testUpdateIssueEntityOnIssueDraftDAO() {
		// SAVE
		IssueDraft issueDraftSaved = issueDraftDAO.save(issueDraft);
		issueDraftSaved.setIssueDescription("test");
		issueDraftSaved = issueDraftDAO.save(issueDraftSaved);
		// CHECK
		// After Saving an Entity got a ID
		// And Save the ID for later Check if Entity with that ID is deleted
		assertTrue(issueDraftSaved.getId() > 0);
		assertTrue(issueDraftSaved.getIssueDescription().equals("test"));

		// CHECK
		assertNotNull(issueDraftDAO.findById(issueDraftSaved.getId()));

	}

	@Test
	public void testFetchAllIssueEntitiesFromIssueDraftDAO() {
		IssueDraft issueDraft2 = new IssueDraft("newIssue2", "WorkToDo2",
				IssueType.BUG);
		
		IssueDraft issueDraft3 = new IssueDraft("newIssue3", "WorkToDo3",
				IssueType.BUG);

		// SAVE
		IssueDraft issueDraftSaved = issueDraftDAO.save(issueDraft);
		IssueDraft issueDraftSaved2 = issueDraftDAO.save(issueDraft2);
		IssueDraft issueDraftSaved3 = issueDraftDAO.save(issueDraft3);
		
		assertFalse(issueDraftDAO.fetchAll().isEmpty());

		// CHECK
		// After Saving all Entities got an ID
		List<IssueDraft> issueDrafts = issueDraftDAO.fetchAll();

		for (IssueDraft actualIssueDraft : issueDrafts) {
			assertTrue(actualIssueDraft.getId() > 0);
		}
		assertTrue(issueDrafts.contains(issueDraftSaved));
		assertTrue(issueDrafts.contains(issueDraftSaved2));
		assertTrue(issueDrafts.contains(issueDraftSaved3));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveNull() {
		issueDraftDAO.save(null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveIssueDraftNullName() {
		issueDraft.setIssueName(null);
		issueDraftDAO.save(issueDraft);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveIssueDraftNullDescription() {
		issueDraft.setIssueDescription(null);
		issueDraftDAO.save(issueDraft);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveIssueDraftNullType() {
		issueDraft.setIssueType(null);
		issueDraftDAO.save(issueDraft);
	}

	@Test
	public void testSaveIssueDraftNullIssueEntities() {
		issueDraft.setIssueEntities(null);
		IssueDraft savedIssueDraft = issueDraftDAO.save(issueDraft);
		assertTrue(savedIssueDraft.getId()>0);
	}

	@Test
	public void testSaveIssueDraftNullTimeTasks() {		
		issueDraft.setTimeTasks(null);
		IssueDraft savedIssueDraft = issueDraftDAO.save(issueDraft);
		assertTrue(savedIssueDraft.getId()>0);
	
	}

	@Test(expected = PersistenceException.class)
	public void testSaveIssueDraft101LetterName() {
		String invalidName = MyUtil.generateSingleCharStringOfLength(
				maxNameLength + 1, "a");
		assertTrue(invalidName.length() == (maxNameLength+1));
		issueDraft.setIssueName(invalidName);
		IssueDraft savedIssueDraft = issueDraftDAO.save(issueDraft);
		assertTrue(savedIssueDraft.getId()>0);
	}
	@Test(expected = PersistenceException.class)
	public void testSaveIssueDraft501LetterDescription() {
		String invalidDescription = MyUtil.generateSingleCharStringOfLength(
				maxDescriptionLength + 1, "a");
		assertTrue(invalidDescription.length() == (maxDescriptionLength+1));
		issueDraft.setIssueDescription(invalidDescription);
		issueDraft = issueDraftDAO.save(issueDraft);
		assertTrue(issueDraft.getId()>0);
	}

}
