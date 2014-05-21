package de.sep.innovativeoperation.taskscheduler.model.resource;

import de.sep.innovativeoperation.taskscheduler.model.resource.generic.AbstractGenericResourcesModel;

public class TimeTasksResource extends AbstractGenericResourcesModel<TimeTaskResource>{

	/**
	 * Create a new TimeTasksResource with no TimeTaskResources included
	 */
	public TimeTasksResource() {
		super();
	}

	/**
	 * Create A TimetasksResource with including multiple TimeTaskResources as content
	 * @param content including TimeTaskResouerces
	 */
	public TimeTasksResource(Iterable<TimeTaskResource> content) {
		super(content);
	}

	
}
