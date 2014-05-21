package de.sep.innovativeoperation.taskscheduler.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.generic.GenericDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;

@Repository
public interface IssueEntityDAO extends GenericDAO<IssueEntity> {

	public List<IssueEntity> filterIssueEntity(IssueStatus issueStatus, IssueResolution issueResolution, String issueName, String issueDescription, IssueType issueType);
}
