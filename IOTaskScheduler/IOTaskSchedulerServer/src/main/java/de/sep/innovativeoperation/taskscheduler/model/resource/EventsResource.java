package de.sep.innovativeoperation.taskscheduler.model.resource;

import de.sep.innovativeoperation.taskscheduler.model.resource.generic.AbstractGenericResourcesModel;

public class EventsResource extends AbstractGenericResourcesModel<EventResource>{

	public EventsResource() {
		super();
		
	}

	public EventsResource(Iterable<EventResource> content) {
		super(content);
	}

}
