package de.sep.innovativeoperation.taskscheduler.model.resource.embedded;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.sep.innovativeoperation.taskscheduler.model.resource.IssueDraftResource;

public class EmbeddedIssueDraft extends GenericEmbeddedResource< IssueDraftResource > {

	
	public EmbeddedIssueDraft(){
		this(new IssueDraftResource() );
	}

	public EmbeddedIssueDraft(IssueDraftResource issueDraftResource) {
		super(issueDraftResource);
	}
	
	@JsonProperty(value="issueDraft")
	@Override
	public IssueDraftResource getResource(){
		return super.getResource();
	}

}
