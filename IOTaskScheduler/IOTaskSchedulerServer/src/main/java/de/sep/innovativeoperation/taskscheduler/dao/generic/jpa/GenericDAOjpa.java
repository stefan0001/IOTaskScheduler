package de.sep.innovativeoperation.taskscheduler.dao.generic.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import de.sep.innovativeoperation.taskscheduler.dao.generic.GenericDAO;

@Repository
public abstract class GenericDAOjpa <T> implements GenericDAO <T>{
	
	/**
	 * Inject the EntityManager
	 */
	@PersistenceContext(unitName = "H2Connection")
	protected EntityManager em;

	/**
	 * the name of the persistent class (IssueEntity, IssueEntity.... )
	 */
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	protected GenericDAOjpa() {
		// Initial the persistent class
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		this.persistentClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}


	public T findById(int id) {
		return em.find(persistentClass, id);
	}



	public T save(T entity) {
		return em.merge(entity);

	}



	public List<T> fetchAll() {
		// CREATE QUERY AND EXECUTE THE QUERY
		CriteriaQuery<T> selectQuery = em.getCriteriaBuilder().createQuery(persistentClass);
		selectQuery.from(persistentClass);
		return em.createQuery(selectQuery).getResultList();
	}
	

	public void remove(T entity) {
		em.remove(entity);

	}
	


	public void deleteAll() {
		throw new UnsupportedOperationException();

	}
	

	
}
