package de.sep.innovativeoperation.taskscheduler.dao.jpa;

import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.TimeTaskDAO;
import de.sep.innovativeoperation.taskscheduler.dao.generic.jpa.GenericDAOjpa;
import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;

@Repository
public class TimeTaskDAOjpa extends GenericDAOjpa<TimeTask> implements TimeTaskDAO {

	public List<TimeTask> getTimeTaskWithNextFireTimeOlderThan(Calendar fireTime) {
				
		//CRITERIA BUILDER
		
		//create builder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//create query
		CriteriaQuery<TimeTask> cq = cb.createQuery(TimeTask.class);
		//select root element (return object)
		Root<TimeTask> timeTaskRoot = cq.from(TimeTask.class);
		
		//where
		cq.where(cb.lessThan(timeTaskRoot.<Calendar>get("nextFireTime"), fireTime) );
		cq.select(timeTaskRoot);
		
		
		return em.createQuery(cq).getResultList();
	}




}
