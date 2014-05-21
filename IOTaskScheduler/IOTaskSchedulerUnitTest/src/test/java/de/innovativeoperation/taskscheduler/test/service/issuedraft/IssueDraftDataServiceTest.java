package de.innovativeoperation.taskscheduler.test.service.issuedraft;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
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
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.service.issuedraft.IssueDraftDataService;
import de.sep.innovativeoperation.taskscheduler.service.issueentity.IssueEntityDataService;
import de.sep.innovativeoperation.taskscheduler.service.timetask.TimeTaskDataService;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class IssueDraftDataServiceTest {

	@Autowired
	IssueDraftDataService issueDraftDataService;

	@Autowired
	IssueEntityDataService issueEntityDataService;

	@Autowired
	TimeTaskDataService timeTaskDataService;

	int maxDescriptionLength = 500;
	int maxNameLength = 100;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCreateIssueDraft() {
		IssueDraft issueDraft = new IssueDraft("foo", "baa", IssueType.BUG);
		IssueDraft savedIssueDraft = issueDraftDataService
				.createIssueDraft(issueDraft);
		assertTrue(savedIssueDraft.getId() > 0);
		assertNotNull(issueDraftDataService.getById(savedIssueDraft.getId()));
	}

	@Test
	public void testUpdateIssueDraft() {
		IssueDraft issueDraft = new IssueDraft("foo", "baa", IssueType.BUG);
		IssueDraft otherIssueDraft = new IssueDraft("baa", "foo",
				IssueType.IMPROVEMENT);

		IssueDraft savedIssueDraft = issueDraftDataService
				.createIssueDraft(issueDraft);

		IssueDraft updatedIssueDraft = issueDraftDataService.updateIssueDraft(
				savedIssueDraft.getId(), otherIssueDraft);

		assertTrue(savedIssueDraft.getId() > 0);
		assertTrue(updatedIssueDraft.getId() > 0);

		assertTrue(updatedIssueDraft.getIssueDescription().equals(
				otherIssueDraft.getIssueDescription()));
		assertTrue(updatedIssueDraft.getIssueName().equals(
				otherIssueDraft.getIssueName()));
		assertTrue(updatedIssueDraft.getIssueType().equals(
				otherIssueDraft.getIssueType()));

	}

	@Test
	public void testGetIssueEntitiesForIssueDraft() {
		IssueDraft issueDraft = new IssueDraft("foo", "baa", IssueType.BUG);
		IssueDraft savedIssueDraft = issueDraftDataService
				.createIssueDraft(issueDraft);

		assertTrue(savedIssueDraft.getId() > 0);

		IssueEntity newIssueEntity = new IssueEntity(IssueStatus.NEW,
				IssueResolution.UNRESOLVED, issueDraft);
		IssueEntity savedIssueEntity = issueEntityDataService
				.createIssueEntity(savedIssueDraft.getId(), newIssueEntity);

		assertTrue(savedIssueEntity.getId() > 0);

		Set<IssueEntity> issueEntities = issueDraftDataService
				.getIssueEntitiesForIssueDraft(savedIssueDraft.getId());
		assertTrue(savedIssueEntity.getId() > 0);

		assertTrue(issueEntities.contains(savedIssueEntity));

	}

	@Test
	public void testGetTimeTasksForIssueDraft() {
		IssueDraft issueDraft = new IssueDraft("foo", "baa", IssueType.BUG);
		IssueDraft savedIssueDraft = issueDraftDataService
				.createIssueDraft(issueDraft);

		TimeTask newTimeTask = new TimeTask("M�ll rausbringen",
				Calendar.getInstance(), Calendar.getInstance(), 0, 3600, false);

		TimeTask savedTimeTask = timeTaskDataService
				.createTimeTask(newTimeTask);

		assertTrue(savedTimeTask.getId() > 0);

		IssueDraft relatedIssueDraft = timeTaskDataService
				.createRelationTimeTaskIssueDraft(savedTimeTask.getId(),
						savedIssueDraft);

		assertNotNull(relatedIssueDraft);

		Set<TimeTask> timeTasks = issueDraftDataService
				.getTimeTasksForIssueDraft(savedIssueDraft.getId());

		assertFalse(timeTasks.isEmpty());
		assertTrue(relatedIssueDraft.getTimeTasks().contains(savedTimeTask));
		assertTrue(timeTasks.contains(savedTimeTask));

	}

	@Test
	public void testFilterIssueDraft() {
		IssueDraft issueDraft = new IssueDraft("a", "baa", IssueType.BUG);
		IssueDraft savedFirstIssueDraft = issueDraftDataService
				.createIssueDraft(issueDraft);
		assertTrue(savedFirstIssueDraft.getId() > 0);
		IssueDraft secondIssueDraft = new IssueDraft("aab", "foo",
				IssueType.IMPROVEMENT);
		IssueDraft savedSecondIssueDraft = issueDraftDataService
				.createIssueDraft(secondIssueDraft);
		assertTrue(savedSecondIssueDraft.getId() > 0);
		IssueDraft thirdIssueDraft = new IssueDraft("aba", "foa",
				IssueType.TASK);
		IssueDraft savedThirdIssueDraft = issueDraftDataService
				.createIssueDraft(thirdIssueDraft);
		assertTrue(savedThirdIssueDraft.getId() > 0);
		List<IssueDraft> unfilteredIssueDrafts = new LinkedList<IssueDraft>();

		unfilteredIssueDrafts.add(savedFirstIssueDraft);
		unfilteredIssueDrafts.add(savedSecondIssueDraft);
		unfilteredIssueDrafts.add(savedThirdIssueDraft);

		List<IssueDraft> issueDrafts = issueDraftDataService
				.filterIssueDraft(issueDraft);
		assertTrue(issueDrafts.contains(savedFirstIssueDraft));

		issueDrafts = new LinkedList<IssueDraft>();
		IssueDraft umlauteIssueDraft = new IssueDraft();
		umlauteIssueDraft.setIssueName("c");
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.isEmpty());

		issueDrafts = new LinkedList<IssueDraft>();
		umlauteIssueDraft = new IssueDraft(null, "bab", null);
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.isEmpty());

		issueDrafts = new LinkedList<IssueDraft>();
		umlauteIssueDraft = new IssueDraft(null, null, IssueType.BUG);
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.contains(savedFirstIssueDraft));

		issueDrafts = new LinkedList<IssueDraft>();
		umlauteIssueDraft = new IssueDraft(null, null, IssueType.IMPROVEMENT);
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.contains(savedSecondIssueDraft));
		
		issueDrafts = new LinkedList<IssueDraft>();
		umlauteIssueDraft = new IssueDraft(null, null, IssueType.TASK);
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.contains(savedThirdIssueDraft));
		
		issueDrafts = new LinkedList<IssueDraft>();
		umlauteIssueDraft = new IssueDraft(null, null, null);
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.containsAll(unfilteredIssueDrafts));

		issueDrafts = new LinkedList<IssueDraft>();
		umlauteIssueDraft = new IssueDraft(null, "fo", null);
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.contains(savedThirdIssueDraft));
		assertTrue(issueDrafts.contains(savedSecondIssueDraft));
		
		issueDrafts = new LinkedList<IssueDraft>();
		umlauteIssueDraft = new IssueDraft(null, "foa", null);
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.contains(savedThirdIssueDraft));
		assertFalse(issueDrafts.contains(savedSecondIssueDraft));

		issueDrafts = new LinkedList<IssueDraft>();
		umlauteIssueDraft = new IssueDraft(null, "b", null);
		issueDrafts = issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
		assertTrue(issueDrafts.contains(savedFirstIssueDraft));

	}

	@Test
	public void testFilterRightSizedName() {
		String nameToFilterFor = MyUtil.generateSingleCharStringOfLength(
				maxNameLength,"a");
		IssueDraft hugeDescriptionIssueDraft = new IssueDraft(nameToFilterFor,
				"hugeDescription", IssueType.BUG);
		IssueDraft savedIssueDraft = issueDraftDataService
				.createIssueDraft(hugeDescriptionIssueDraft);
		List<IssueDraft> issueDrafts = issueDraftDataService
				.filterIssueDraft(new IssueDraft(nameToFilterFor, null, null));
		assertTrue(issueDrafts.contains(savedIssueDraft));

	}

	@Test(expected = ValueIsNotValidException.class)
	public void testFilterOversizedName() {

		IssueDraft umlauteIssueDraft = new IssueDraft(
				MyUtil.generateSingleCharStringOfLength(maxNameLength + 1,"a"), null,
				null);
		issueDraftDataService.filterIssueDraft(umlauteIssueDraft);
	}

	@Test
	public void testFilterRightSizedDescription() {
		IssueDraft hugeDescriptionIssueDraft = new IssueDraft(
				"hugeDescription", MyUtil.generateSingleCharStringOfLength(
						maxDescriptionLength, "B"), IssueType.BUG);
		IssueDraft savedIssueDraft = issueDraftDataService
				.createIssueDraft(hugeDescriptionIssueDraft);
		List<IssueDraft> issueDrafts = issueDraftDataService
				.filterIssueDraft(new IssueDraft(null, MyUtil
						.generateSingleCharStringOfLength(500, "B"), null));
		assertTrue(issueDrafts.contains(savedIssueDraft));
	}

	@Test(expected = ValueIsNotValidException.class)
	public void testFilterOversizedDescription() {

		IssueDraft issueDraft = new IssueDraft(
				null,
				MyUtil.generateSingleCharStringOfLength(maxDescriptionLength + 1,"a"),
				null);
		issueDraftDataService.filterIssueDraft(issueDraft);
	}

}
