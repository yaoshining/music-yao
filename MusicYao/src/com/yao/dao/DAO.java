package com.yao.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.TransactionAttribute;

import com.yao.dao.bean.Order;
import com.yao.dao.bean.WhereCondition;

public interface DAO {
	/**
	 * 保存实体对象
	 * @param entity 可以是实体对象或实体对象的数组
	 */
	public void save(Object entity);
	/**
	 * 批量保存实体对象
	 * @param entities 实体对象的集合
	 */
	public void saveEntities(Iterable<?> entities);
	/**
	 * 批量保存实体对象
	 * @param entities 多个实体对象或实体对象的数组
	 */
	public void saveEntities(Object...entities);
	/**
	 * 删除记录
	 * @param entityId 要删除的实体对象的主键
	 */
	public <T> void removeById(Class<T> entityClass, Object entityId);
	/**
	 * 批量删除多个记录
	 * @param entityIds 要删除的多个实体对象的主键
	 */
	public <T> void removeByIds(Class<T> entityClass, Object...entityIds);
	/**
	 * 批量删除多个记录
	 * @param entityIds 要删除的多个实体对象的主键的集合
	 */
	public <T> void removeByIds(Class<T> entityClass, Iterable<?> entityIds);
	/**
	 * 查询某条记录
	 * @param entityId 实体对象的主键
	 * @return 查询到的实体对象
	 */
	public <T> T find(Class<T> entityClass, Object entityId);
	public <T> List<T> findByIds(Class<T> entityClass, Iterable<?> entityIds);
	public <T> List<T> findByIds(Class<T> entityClass, Object...entityIds);
	public <T> T update(T entity);
	public <T> List<T> updateEntities(Iterable<T> entities);
	public <T> List<T> updateEntities(T[] entities);
	public <T> List<T> find(Class<T> entityClass,List<WhereCondition> conditions,Map<String,Order> orderby,int firstResult,int maxResults);
	public <T> List<T> findAll(Class<T> entityClass);
}
