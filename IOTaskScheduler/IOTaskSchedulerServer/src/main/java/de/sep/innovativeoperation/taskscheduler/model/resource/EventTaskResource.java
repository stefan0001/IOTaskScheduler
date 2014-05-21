package de.sep.innovativeoperation.taskscheduler.model.resource;

import de.sep.innovativeoperation.taskscheduler.model.data.EventTask;
import de.sep.innovativeoperation.taskscheduler.model.resource.embedded.EmbeddedEvent;
import de.sep.innovativeoperation.taskscheduler.model.resource.generic.AbstractGenericResourceModel;

public class EventTaskResource extends AbstractGenericResourceModel<EventTask>{

	private EmbeddedEvent embedded;
	
	
	/**
	 * Create a new EventTaskResource with a default EventTask and a Default Event
	 */
	public EventTaskResource() {
		this(new EventTask() );
	}
	
	/**
	 * Create a new EventTaskResource with a given EventTask as Content
	 * @param content  including Event
	 */
	public EventTaskResource(EventTask content) {
		super(content);
		this.embedded = new EmbeddedEvent();
	}
	
	/**
	 * Create a new EventTaskResource with a given EventTask as content and a EventResource as embedded Resource
	 * @param content
	 * @param embedded
	 */
	public EventTaskResource(EventTask content, EventResource embedded) {
		super(content);
		this.embedded = new EmbeddedEvent( embedded );
	}
	
	
	
	public EmbeddedEvent getEmbedded() {
		return embedded;
	}
	

}
