package de.sep.innovativeoperation.taskscheduler.model.resource;

import de.sep.innovativeoperation.taskscheduler.model.data.IssueDraft;
import de.sep.innovativeoperation.taskscheduler.model.resource.generic.AbstractGenericResourceModel;

public class IssueDraftResource extends AbstractGenericResourceModel<IssueDraft> {

	public IssueDraftResource() {
		this(new IssueDraft());
	}
	
	public IssueDraftResource(IssueDraft content) {
		super(content);
	}



}
