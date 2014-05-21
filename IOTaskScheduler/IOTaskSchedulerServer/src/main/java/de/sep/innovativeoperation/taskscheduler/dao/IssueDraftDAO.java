package de.sep.innovativeoperation.taskscheduler.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.generic.GenericDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;

@Repository
public interface IssueDraftDAO extends GenericDAO<IssueDraft>{

	public List<IssueDraft> filterIssueDraft(String issueName, String issueDescription, IssueType issueType);
	
}
