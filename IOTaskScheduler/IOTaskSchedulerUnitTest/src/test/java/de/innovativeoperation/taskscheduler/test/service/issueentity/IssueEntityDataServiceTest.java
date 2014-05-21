package de.innovativeoperation.taskscheduler.test.service.issueentity;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.exception.http.ResourceNotFoundException;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;
import de.sep.innovativeoperation.taskscheduler.service.issuedraft.IssueDraftDataService;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityDataService;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class IssueEntityDataServiceTest {

	@Autowired
	IssueEntityDataService issueEntityDataService;

	@Autowired
	private IssueDraftDataService issueDraftService;

	@Before
	public void setUp() {

	}

	@Test
	public void testGetByIdEntityDataService() {
		IssueDraft savedIssueDraft = issueDraftService
				.createIssueDraft(new IssueDraft("savedIssueDraft",
						"describeMe", IssueType.TASK));
		IssueEntity savedIssueEntity = issueEntityDataService
				.createIssueEntity(savedIssueDraft.getId(), new IssueEntity(
						IssueStatus.NEW, IssueResolution.UNRESOLVED,
						savedIssueDraft));

		IssueEntity receivedIssueEntity = issueEntityDataService
				.getById(savedIssueEntity.getId());
		assertNotNull(receivedIssueEntity);
		assertTrue(receivedIssueEntity.equals(savedIssueEntity));
	}

	@Test
	public void testDeleteByIdEntityDataService() {
		IssueDraft savedIssueDraft = issueDraftService
				.createIssueDraft(new IssueDraft("savedIssueDraft",
						"describeMe", IssueType.TASK));
		IssueEntity savedIssueEntity = issueEntityDataService
				.createIssueEntity(savedIssueDraft.getId(), new IssueEntity(
						IssueStatus.NEW, IssueResolution.UNRESOLVED,
						savedIssueDraft));
		issueEntityDataService.deleteById(savedIssueEntity.getId());

		assertFalse(issueEntityDataService.getAll().contains(savedIssueEntity));
	}

	@Test
	public void testCreateIssueEntity() {
		IssueDraft savedIssueDraft = issueDraftService
				.createIssueDraft(new IssueDraft("savedIssueDraft",
						"describeMe", IssueType.TASK));
		IssueEntity savedIssueEntity = issueEntityDataService
				.createIssueEntity(savedIssueDraft.getId(), new IssueEntity(
						IssueStatus.NEW, IssueResolution.UNRESOLVED,
						savedIssueDraft));

		assertNotNull(savedIssueEntity);
		assertTrue(savedIssueEntity.getId() > 0);
		assertTrue(savedIssueEntity.getIssueDraft().equals(savedIssueDraft));
	}

	@Test
	public void testUpdateIssueEntityByEntityDataService() {
		IssueDraft savedIssueDraft = issueDraftService
				.createIssueDraft(new IssueDraft("savedIssueDraft",
						"describeMe", IssueType.TASK));
		IssueEntity savedIssueEntity = issueEntityDataService
				.createIssueEntity(savedIssueDraft.getId(), new IssueEntity(
						IssueStatus.NEW, IssueResolution.UNRESOLVED,
						savedIssueDraft));

		IssueEntity desiredIssueEntity = new IssueEntity();
		desiredIssueEntity.setIssueResolution(IssueResolution.DUPLICATE);
		desiredIssueEntity.setIssueStatus(IssueStatus.IN_PROGRESS);

		IssueEntity updatedIssueEntity = savedIssueEntity;

		assertFalse(updatedIssueEntity.getIssueResolution().equals(
				desiredIssueEntity.getIssueResolution()));
		assertFalse(updatedIssueEntity.getIssueStatus().equals(
				desiredIssueEntity.getIssueStatus()));

		updatedIssueEntity = issueEntityDataService.updateIssueEntity(
				savedIssueEntity.getId(), desiredIssueEntity);

		assertTrue(updatedIssueEntity.getIssueResolution().equals(
				desiredIssueEntity.getIssueResolution()));
		assertTrue(updatedIssueEntity.getIssueStatus().equals(
				desiredIssueEntity.getIssueStatus()));
		assertFalse(updatedIssueEntity.equals(desiredIssueEntity));

	}

	@Test
	public void testFilterIssueEntity() {
		IssueDraft firstSavedIssueDraft = issueDraftService
				.createIssueDraft(new IssueDraft("savedIssueDraft aab",
						"describeMe", IssueType.TASK));
		IssueEntity firstSavedIssueEntity = issueEntityDataService
				.createIssueEntity(firstSavedIssueDraft.getId(),
						new IssueEntity(IssueStatus.NEW,
								IssueResolution.UNRESOLVED,
								firstSavedIssueDraft));
		IssueDraft secondSavedssueDraft = issueDraftService
				.createIssueDraft(new IssueDraft("savedIssueDraft aaa",
						"describeYou", IssueType.BUG));
		IssueEntity secondSavedIssueEntity = issueEntityDataService
				.createIssueEntity(secondSavedssueDraft.getId(),
						new IssueEntity(IssueStatus.IN_PROGRESS,
								IssueResolution.UNRESOLVED,
								secondSavedssueDraft));
		IssueDraft thirdSavedIssueDraft = issueDraftService
				.createIssueDraft(new IssueDraft("savedIssueDraft ab",
						"describeUs", IssueType.IMPROVEMENT));
		IssueEntity thirdSavedIssueEntity = issueEntityDataService
				.createIssueEntity(thirdSavedIssueDraft.getId(),
						new IssueEntity(IssueStatus.RESOLVED,
								IssueResolution.WONTFIX, thirdSavedIssueDraft));

		IssueEntity filterIssueEntity = new IssueEntity(IssueStatus.OPEN,
				IssueResolution.DUPLICATE, null);
		IssueDraft filterIssueDraft = new IssueDraft("savedIssueDraft",
				"describe", IssueType.BUG);

		List<IssueEntity> issueEntities = issueEntityDataService
				.filterIssueEntity(filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.isEmpty());
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(IssueStatus.NEW, null, null);
		filterIssueDraft = new IssueDraft(null, null, null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.contains(firstSavedIssueEntity));
		assertFalse(issueEntities.contains(secondSavedIssueEntity));
		assertFalse(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(IssueStatus.IN_PROGRESS, null, null);
		filterIssueDraft = new IssueDraft(null, null, null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertFalse(issueEntities.contains(firstSavedIssueEntity));
		assertTrue(issueEntities.contains(secondSavedIssueEntity));
		assertFalse(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(IssueStatus.RESOLVED, null, null);
		filterIssueDraft = new IssueDraft(null, null, null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertFalse(issueEntities.contains(firstSavedIssueEntity));
		assertFalse(issueEntities.contains(secondSavedIssueEntity));
		assertTrue(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft(null, null, IssueType.BUG);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertFalse(issueEntities.contains(firstSavedIssueEntity));
		assertTrue(issueEntities.contains(secondSavedIssueEntity));
		assertFalse(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft(null, null, IssueType.IMPROVEMENT);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertFalse(issueEntities.contains(firstSavedIssueEntity));
		assertFalse(issueEntities.contains(secondSavedIssueEntity));
		assertTrue(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft(null, null, IssueType.TASK);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.contains(firstSavedIssueEntity));
		assertFalse(issueEntities.contains(secondSavedIssueEntity));
		assertFalse(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft("savedIssueDraft a", null, null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.contains(firstSavedIssueEntity));
		assertTrue(issueEntities.contains(secondSavedIssueEntity));
		assertTrue(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft("savedIssueDraft aa", null, null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.contains(firstSavedIssueEntity));
		assertTrue(issueEntities.contains(secondSavedIssueEntity));
		assertFalse(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft("savedIssueDraft aab", null, null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.contains(firstSavedIssueEntity));
		assertFalse(issueEntities.contains(secondSavedIssueEntity));
		assertFalse(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft(null, "describe", null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.contains(firstSavedIssueEntity));
		assertTrue(issueEntities.contains(secondSavedIssueEntity));
		assertTrue(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft(null, "describeM", null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.contains(firstSavedIssueEntity));
		assertFalse(issueEntities.contains(secondSavedIssueEntity));
		assertFalse(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft(null, "describeY", null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertFalse(issueEntities.contains(firstSavedIssueEntity));
		assertTrue(issueEntities.contains(secondSavedIssueEntity));
		assertFalse(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft(null, "describeU", null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertFalse(issueEntities.contains(firstSavedIssueEntity));
		assertFalse(issueEntities.contains(secondSavedIssueEntity));
		assertTrue(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();

		filterIssueEntity = new IssueEntity(null, null, null);
		filterIssueDraft = new IssueDraft(null, null, null);

		issueEntities = issueEntityDataService.filterIssueEntity(
				filterIssueEntity, filterIssueDraft);
		assertTrue(issueEntities.contains(firstSavedIssueEntity));
		assertTrue(issueEntities.contains(secondSavedIssueEntity));
		assertTrue(issueEntities.contains(thirdSavedIssueEntity));
		issueEntities.clear();
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testExceptionIsThrownForGetByIdIntMAX_VALUEIssueEntityDataService() {
		issueEntityDataService.getById(Integer.MAX_VALUE);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testExceptionIsThrownForGetByIdIntMIN_VALUEIssueEntityDataService() {
		issueEntityDataService.getById(Integer.MIN_VALUE);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testExceptionIsThrownForGetByIdZeroIssueEntityDataService() {
		issueEntityDataService.getById(Integer.MIN_VALUE);
	}

}
