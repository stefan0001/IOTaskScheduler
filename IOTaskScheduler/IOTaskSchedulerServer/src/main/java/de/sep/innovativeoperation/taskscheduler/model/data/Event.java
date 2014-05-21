package de.sep.innovativeoperation.taskscheduler.model.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@JsonIgnoreProperties("eventTasks")
public class Event extends AbstractDataModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(length=100) 
	private String name;

	@OneToMany( mappedBy = "event", cascade = {CascadeType.ALL})
	private Set<EventTask> eventTasks = new HashSet<EventTask>();
	
	/**
	 * Creates an Event with no Name and and no tasks
	 */
	public Event() {
		this(null);
	}
	
	/**
	 * Creates an Event with only a name and empty task list
	 * @param name Name of the eventtask
	 */
	public Event(String name) {
		this(name,new HashSet<EventTask>());
	}
	
	
	
	
	/**
	 * Create a Event with a name and all Tasks that use this event
	 * @param name
	 * @param eventTasks
	 */
	public Event(String name, Set<EventTask> eventTasks) {
		this.name = name;
		this.eventTasks = eventTasks;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<EventTask> getEventTasks() {
		return eventTasks;
	}



	public void setEventTasks(Set<EventTask> eventTasks) {
		this.eventTasks = eventTasks;
	}


	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
