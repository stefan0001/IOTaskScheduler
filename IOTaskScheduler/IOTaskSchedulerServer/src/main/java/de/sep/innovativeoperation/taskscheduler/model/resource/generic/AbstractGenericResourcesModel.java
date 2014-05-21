package de.sep.innovativeoperation.taskscheduler.model.resource.generic;

import org.springframework.hateoas.Resources;

public abstract class AbstractGenericResourcesModel<R extends AbstractGenericResourceModel<?>> extends Resources<R> {

	
	public AbstractGenericResourcesModel() {
		super();
		
	}


	public AbstractGenericResourcesModel(Iterable<R> content) {
		super(content);
		
	}


}
