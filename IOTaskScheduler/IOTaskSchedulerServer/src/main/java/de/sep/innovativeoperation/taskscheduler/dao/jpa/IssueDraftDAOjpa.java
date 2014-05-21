package de.sep.innovativeoperation.taskscheduler.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.IssueDraftDAO;
import de.sep.innovativeoperation.taskscheduler.dao.generic.jpa.GenericDAOjpa;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.data.IssueType;

@Repository
public class IssueDraftDAOjpa extends GenericDAOjpa<IssueDraft> implements IssueDraftDAO {

	@Override
	public List<IssueDraft> filterIssueDraft(String issueName,String issueDescription, IssueType issueType) {
		//CRITERIA BUILDER
		
		//create builder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//create query
		CriteriaQuery<IssueDraft> cq = cb.createQuery(IssueDraft.class);
		//select root element (returned object)
		Root<IssueDraft> issueDraftRoot = cq.from(IssueDraft.class);
		

		
		//where
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		
		
		//where issuedraftname
		if(issueName != null){
			Predicate predicate = cb.like(cb.lower(issueDraftRoot.<String>get("issueName")),issueName.toLowerCase()+"%");
			predicates.add(predicate );
		}
		//where issuedraftdescription
		if(issueDescription!= null){
			Predicate predicate  = cb.like(cb.lower(issueDraftRoot.<String>get("issueDescription")),issueDescription.toLowerCase()+"%");
			predicates.add(predicate);
		}

		
		//where issuedrafttype
		if(issueType != null){
			Predicate predicate  = cb.equal(issueDraftRoot.<IssueType>get("issueType"),issueType);
			predicates.add(predicate);
		}
		

		//sum of the predicates
		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()]) ));

		cq.select(issueDraftRoot);
		

		
		return em.createQuery(cq).getResultList();
	}




}
