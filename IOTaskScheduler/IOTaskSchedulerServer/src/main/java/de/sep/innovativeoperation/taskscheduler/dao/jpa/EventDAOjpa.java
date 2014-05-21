package de.sep.innovativeoperation.taskscheduler.dao.jpa;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.EventDAO;
import de.sep.innovativeoperation.taskscheduler.dao.generic.jpa.GenericDAOjpa;
import de.sep.innovativeoperation.taskscheduler.model.data.Event;

@Repository
public class EventDAOjpa extends GenericDAOjpa<Event> implements EventDAO {

}
