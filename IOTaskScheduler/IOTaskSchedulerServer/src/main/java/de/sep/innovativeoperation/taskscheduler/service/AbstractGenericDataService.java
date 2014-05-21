package de.sep.innovativeoperation.taskscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.sep.innovativeoperation.taskscheduler.dao.generic.jpa.GenericDAOjpa;
import de.sep.innovativeoperation.taskscheduler.exception.http.ResourceNotFoundException;
import de.sep.innovativeoperation.taskscheduler.model.data.AbstractDataModel;
import de.sep.innovativeoperation.taskscheduler.service.validation.AbstractGenericValidationService;


@Service
@Transactional
public abstract class AbstractGenericDataService<T extends AbstractDataModel> {
	@Autowired
	protected GenericDAOjpa<T> dao;
	
	@Autowired
	protected AbstractGenericValidationService<T> validationService;
	
	
	

	public T getById(int id){
		T entity = dao.findById(id);
		if(entity == null){
			throw new ResourceNotFoundException();
		}
		
		return entity;
	}

	public void deleteById(int id){
		T entity = this.getById(id);
		this.removeBidirctionalRelations(entity);
		
		dao.remove(entity);
	}
	
	protected abstract void removeBidirctionalRelations(T entity);
	
	public List<T> getAll(){
		return dao.fetchAll();
	}
	
}
