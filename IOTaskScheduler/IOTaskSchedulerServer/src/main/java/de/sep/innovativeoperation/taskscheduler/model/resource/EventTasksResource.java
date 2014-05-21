package de.sep.innovativeoperation.taskscheduler.model.resource;

import de.sep.innovativeoperation.taskscheduler.model.resource.generic.AbstractGenericResourcesModel;

public class EventTasksResource extends AbstractGenericResourcesModel<EventTaskResource>{

	public EventTasksResource() {
		super();
	}

	public EventTasksResource(Iterable<EventTaskResource> content) {
		super(content);
	}

}
