package de.sep.innovativeoperation.taskscheduler.service.assembler.generic;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import de.sep.innovativeoperation.taskscheduler.model.data.AbstractDataModel;
import de.sep.innovativeoperation.taskscheduler.model.resource.generic.AbstractGenericResourceModel;




@Service
public abstract class AbstractGenericDataResourceAssembler<D extends AbstractDataModel, F extends AbstractGenericResourceModel<D>> extends ResourceAssemblerSupport<D,F>{

	public AbstractGenericDataResourceAssembler(Class<?> controllerClass,
			Class<F> resourceType) {
		super(controllerClass, resourceType);
		
	}





	
	




	

	

	
	


}