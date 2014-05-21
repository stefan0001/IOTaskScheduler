package de.sep.innovativeoperation.taskscheduler.dao.jpa;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.EventTaskDAO;
import de.sep.innovativeoperation.taskscheduler.dao.generic.jpa.GenericDAOjpa;
import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;

@Repository
public class EventTaskDAOjpa extends GenericDAOjpa<EventTask> implements EventTaskDAO{






}
