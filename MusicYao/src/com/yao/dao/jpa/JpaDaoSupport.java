package com.yao.dao.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.yao.dao.DAO;
import com.yao.dao.bean.SearchOperator;
import com.yao.dao.bean.WhereCondition;
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class JpaDaoSupport implements DAO {
	
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	protected EntityManager em;
	
	@Override
	public void save(Object entity) {
		if(entity.getClass().isArray()) {
			saveEntities((Object[])entity);
			return;
		}
		em.persist(entity);
	}

	@Override
	public void saveEntities(Iterable<?> entities) {
		for(Object entity : entities) {
			em.persist(entity);
		}
	}

	@Override
	public void saveEntities(Object... entities) {
		for(Object entity : entities) {
			em.persist(entity);
		}
	}

	@Override
	public <T> void removeById(Class<T> entityClass, Object entityId) {
		removeByIds(entityClass, entityId);
	}
	
	@Override
	public <T> void removeByIds(Class<T> entityClass, Object... entityIds) {
		removeByIds(entityClass, Arrays.asList(entityIds));
	}

	@Override
	public <T> void removeByIds(Class<T> entityClass, Iterable<?> entityIds) {
		for(Object entityId : entityIds) {
			em.remove(em.getReference(entityClass, entityId));
		}
	}
	@Override
	public <T> T find(Class<T> entityClass, Object entityId) {
		return (T) em.find(entityClass, entityId);
	}
	
	@Override
	public <T> List<T> findByIds(Class<T> entityClass, Iterable<?> entityIds) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.where(root.get("id").in(1,2,3,4));
		return em.createQuery(cq).getResultList();
	}
	
	@Override
	public <T> List<T> findByIds(Class<T> entityClass, Object... entityIds) {
		return findByIds(entityClass, Arrays.asList(entityIds));
	}

	@Override
	public <T> T update(T entity) {
		return em.merge(entity);
	}
	
	@Override
	public <T> List<T> updateEntities(Iterable<T> entities) {
		List<T> mergedEntities = new ArrayList<T>();
		for(T entity : entities) {
			mergedEntities.add(em.merge(entity));
		}
		return mergedEntities;
	}
	
	@Override
	public <T> List<T> updateEntities(T[] entities) {
		return updateEntities(Arrays.asList(entities));
	}
	
	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		List<T> resultList = Collections.emptyList();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		cq.from(entityClass);
		resultList = em.createQuery(cq).getResultList();
		return resultList;
	}
	
	@Override
	public <T> List<T> find(Class<T> entityClass,List<WhereCondition> conditions,Map<String,com.yao.dao.bean.Order> orderby,int firstResult,int maxResults) {
		List<T> resultList = Collections.emptyList();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);
		if(conditions!=null){
			List<Predicate> predicates = new ArrayList<Predicate>();
			for(WhereCondition condition : conditions) {
				String fieldName = condition.getFieldName();
				SearchOperator operator = condition.getOperator();
				Object value = condition.getValue();
				if(operator == SearchOperator.EQUAL) {
					predicates.add(cb.equal(root.get(fieldName), value));
				}
				if(operator == SearchOperator.LIKE) {
					predicates.add(cb.like(root.get(fieldName).as(String.class), (String)value));
				}
				if(operator == SearchOperator.IN) {
					if(value instanceof Collection<?>)
						predicates.add(root.get(fieldName).in(value));
					if(value.getClass().isArray())
						predicates.add(root.get(fieldName).in((Object[])value));
				}
				if(operator == SearchOperator.IS_NULL) {
					predicates.add(root.get(fieldName).isNull());
				}
			}
			cq.where(predicates.toArray(new Predicate[conditions.size()]));
		}
		if(orderby!=null) {
			List<Order> orderList = new ArrayList<Order>();
			for(Entry<String, com.yao.dao.bean.Order> o : orderby.entrySet()) {
				String fieldName = o.getKey();
				com.yao.dao.bean.Order order = o.getValue();
				if(order == com.yao.dao.bean.Order.ASC)
					orderList.add(cb.asc(root.get(fieldName)));
				if(order == com.yao.dao.bean.Order.DESC)
					orderList.add(cb.desc(root.get(fieldName)));
			}
			cq.orderBy(orderList);
		}
		TypedQuery<T> query = em.createQuery(cq);
		if(firstResult>-1)
			query.setFirstResult(firstResult);
		if(maxResults>-1)
			query.setMaxResults(maxResults);
		resultList = query.getResultList();
		return resultList;
	}
	
	protected <T> String getEntityName(Class<T> entityClass) {
		String entityName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if(entity.name()!=null && !"".equals(entityName)) {
			entityName = entity.name();
		}
		return entityName;
	}
}
