package de.sep.innovativeoperation.taskscheduler.dao;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.generic.GenericDAO;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;

@Repository
public interface EventTaskDAO extends GenericDAO<EventTask> {

}
