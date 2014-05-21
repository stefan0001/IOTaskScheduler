package de.sep.innovativeoperation.taskscheduler.model.resource;

import de.sep.innovativeoperation.taskscheduler.model.data.Event;
import de.sep.innovativeoperation.taskscheduler.model.resource.generic.AbstractGenericResourceModel;

public class EventResource  extends AbstractGenericResourceModel<Event>{
	public EventResource() {
		this(new Event() );
	}
	
	
	public EventResource(Event content) {
		super(content);
	}

}
