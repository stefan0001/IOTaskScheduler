package de.sep.innovativeoperation.taskscheduler.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.IssueEntityDAO;
import de.sep.innovativeoperation.taskscheduler.dao.generic.jpa.GenericDAOjpa;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueEntity;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueResolution;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueStatus;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;

@Repository
public class IssueEntityDAOjpa extends GenericDAOjpa<IssueEntity> implements IssueEntityDAO{

	public List<IssueEntity> filterIssueEntity(IssueStatus issueStatus, IssueResolution issueResolution, String issueName, String issueDescription, IssueType issueType) {
		
		//CRITERIA BUILDER
		
		//create builder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//create query
		CriteriaQuery<IssueEntity> cq = cb.createQuery(IssueEntity.class);
		//select root element (returned object)
		Root<IssueEntity> issueEntityRoot = cq.from(IssueEntity.class);
		
		Join<IssueEntity,IssueDraft> issueDrafts = issueEntityRoot.<IssueEntity,IssueDraft>join("issueDraft"); 

		
		//where
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		
		//where issuestatus is
		if(issueStatus != null){
			Predicate predicate = cb.equal(issueEntityRoot.<IssueStatus>get("issueStatus"),issueStatus);
			predicates.add(predicate);
		}
		
		//where issueresolution is
		if(issueResolution != null){
			Predicate predicate = cb.equal(issueEntityRoot.<IssueResolution>get("issueResolution"),issueResolution);
			predicates.add(predicate );
		}
		
		//where issuedraftname
		if(issueName != null){
			Predicate predicate = cb.like(cb.lower(issueDrafts.<String>get("issueName")),issueName.toLowerCase()+"%");
			predicates.add(predicate );
		}
		//where issuedraftdescription
		if(issueDescription!= null){
			Predicate predicate  = cb.like(cb.lower(issueDrafts.<String>get("issueDescription")),issueDescription.toLowerCase()+"%");
			predicates.add(predicate);
		}

		//where issuedrafttype
		if(issueType != null){
			Predicate predicate  = cb.equal(issueDrafts.<IssueType>get("issueType"),issueType);
			predicates.add(predicate);
		}
		
		
		
		
		//find only non archived entities
		Predicate predicate  = cb.equal(issueEntityRoot.<Boolean>get("archived"),false);
		predicates.add(predicate);

		
		//sum of the predicates
		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()]) ));

		//where
		cq.select(issueEntityRoot);
		

		
		return em.createQuery(cq).getResultList();
	}
}
