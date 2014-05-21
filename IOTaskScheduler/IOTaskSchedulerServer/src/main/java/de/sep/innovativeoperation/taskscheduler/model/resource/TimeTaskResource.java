package de.sep.innovativeoperation.taskscheduler.model.resource;

import de.sep.innovativeoperation.taskscheduler.model.data.TimeTask;
import de.sep.innovativeoperation.taskscheduler.model.resource.generic.AbstractGenericResourceModel;

public class TimeTaskResource extends AbstractGenericResourceModel<TimeTask> {

	/**
	 * Create a new TimetaskResource with a default TimeTask
	 */
	public TimeTaskResource(){
		this(new TimeTask() );
	}
	
	/**
	 * Create a new TimeTaskResource with a given TimeTask as Content
	 * @param content
	 */
	public TimeTaskResource(TimeTask content) {
		super(content);
	}



}
