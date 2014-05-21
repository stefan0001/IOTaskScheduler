package de.sep.innovativeoperation.taskscheduler.model.resource.embedded;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.sep.innovativeoperation.taskscheduler.model.resource.EventResource;

public class EmbeddedEvent extends GenericEmbeddedResource<EventResource>{

	public EmbeddedEvent(){
		this(new EventResource());
	}
	public EmbeddedEvent(EventResource resource) {
		super(resource);
	}
	

	@JsonProperty(value="event")
	@Override
	public EventResource getResource(){
		return super.getResource();
	}


}
