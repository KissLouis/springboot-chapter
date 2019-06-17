package com.springboot.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 
 * Description: mongoDB基础方法封装
 * 
 * @author Louis
 * @version 1.0
 * @Note
 * @Date 2019年6月16日 下午7:24:49
 */
public abstract class MongoDbDao<T> {

	protected Logger logger = LoggerFactory.getLogger(MongoDbDao.class);

	/**
	 * 
	 * Description: 反射获取泛型类型
	 * 
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:22
	 */
	protected abstract Class<T> getEntityClass();

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 
	 * Description: 保存一个对象
	 * 
	 * @param t
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:30
	 */
	public void save(T t) {
		logger.info("-------------->MongoDB save start");
		this.mongoTemplate.save(t);
	}

	/**
	 * 
	 * Description:根据id从几何中查询对象
	 * 
	 * @param id
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:37
	 */
	public T queryById(Integer id) {
		Query query = new Query(Criteria.where("_id").is(id));
		logger.info("-------------->MongoDB find start");
		return this.mongoTemplate.findOne(query, this.getEntityClass());
	}

	/**
	 * 
	 * Description:根据条件查询集合
	 * 
	 * @param object
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:42
	 */
	public List<T> queryList(T object) {
		logger.info("-------------->MongoDB find start");
		Query query = getQueryByObject(object);
		return mongoTemplate.find(query, this.getEntityClass());
	}

	/**
	 * 
	 * Description:根据条件查询只返回一个文档
	 * 
	 * @param object
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:45
	 */
	public T queryOne(T object) {
		Query query = getQueryByObject(object);
		logger.info("-------------->MongoDB find start");
		return mongoTemplate.findOne(query, this.getEntityClass());
	}

	/**
	 * 
	 * Description:根据条件分页查询
	 * 
	 * @param object
	 * @param start
	 * @param size
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:48
	 */
	public List<T> getPage(T object, int start, int size) {
		Query query = getQueryByObject(object);
		query.skip(start);
		query.limit(size);
		logger.info("-------------->MongoDB queryPage start");
		return this.mongoTemplate.find(query, this.getEntityClass());
	}

	/**
	 * 
	 * Description:根据条件查询库中符合条件的记录数量
	 * 
	 * @param object
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:52
	 */
	public Long getCount(T object) {
		Query query = getQueryByObject(object);
		logger.info("-------------->MongoDB Count start");
		return this.mongoTemplate.count(query, this.getEntityClass());
	}

	/**
	 * 
	 * Description:删除对象
	 * 
	 * @param t
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:55
	 */
	public int delete(T t) {
		logger.info("-------------->MongoDB delete start");
		return (int) this.mongoTemplate.remove(t).getDeletedCount();
	}

	/**
	 * 
	 * Description:根据id删除
	 * 
	 * @param id
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:29:57
	 */
	public void deleteById(Integer id) {
		Criteria criteria = Criteria.where("_id").is(id);
		if (null != criteria) {
			Query query = new Query(criteria);
			T obj = this.mongoTemplate.findOne(query, this.getEntityClass());
			logger.info("-------------->MongoDB deleteById start");
			if (obj != null) {
				this.delete(obj);
			}
		}
	}


	/**
	 * 
	 * Description: 修改匹配到的第一条记录
	 * 
	 * @param srcObj
	 * @param targetObj
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:30:07
	 */
	public void updateFirst(T srcObj, T targetObj) {
		Query query = getQueryByObject(srcObj);
		Update update = getUpdateByObject(targetObj);
		logger.info("-------------->MongoDB updateFirst start");
		this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
	}

	/**
	 * 
	 * Description:修改所有匹配的记录
	 * 
	 * @param srcObj
	 * @param targetObj
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:30:12
	 */
	public void updateMulti(T srcObj, T targetObj) {
		Query query = getQueryByObject(srcObj);
		Update update = getUpdateByObject(targetObj);
		logger.info("-------------->MongoDB updateFirst start");
		this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}

	/**
	 * 
	 * Description:修改匹配到的记录，若不存在该记录则进行添加
	 * 
	 * @param srcObj
	 * @param targetObj
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:30:16
	 */
	public void updateInsert(T srcObj, T targetObj) {
		Query query = getQueryByObject(srcObj);
		Update update = getUpdateByObject(targetObj);
		logger.info("-------------->MongoDB updateInsert start");
		this.mongoTemplate.upsert(query, update, this.getEntityClass());
	}

	/**
	 * 
	 * Description:将查询条件对象转换为query
	 * 
	 * @param object
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:30:19
	 */
	private Query getQueryByObject(T object) {
		Query query = new Query();
		if (object != null) {
			String[] fileds = getFiledName(object);
			Criteria criteria = new Criteria();
			for (int i = 0; i < fileds.length; i++) {
				String filedName = (String) fileds[i];
				Object filedValue = getFieldValueByName(filedName, object);
				if (filedValue != null) {
					criteria.and(filedName).is(filedValue);
				}
			}
			query.addCriteria(criteria);
		}
		return query;
	}

	/**
	 * 
	 * Description: 将查询条件对象转换为update
	 * 
	 * @param object
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:30:27
	 */
	private Update getUpdateByObject(T object) {
		Update update = new Update();
		String[] fileds = getFiledName(object);
		for (int i = 0; i < fileds.length; i++) {
			String filedName = (String) fileds[i];
			Object filedValue = getFieldValueByName(filedName, object);
			if (filedValue != null) {
				update.set(filedName, filedValue);
			}
		}
		return update;
	}

	/**
	 * 
	 * Description: 获取对象属性返回字符串数组
	 * 
	 * @param o
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:31:29
	 */
	private static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];

		for (int i = 0; i < fields.length; ++i) {
			fieldNames[i] = fields[i].getName();
		}

		return fieldNames;
	}

	/**
	 * 
	 * Description: 根据属性获取对象属性值
	 * 
	 * @param fieldName
	 * @param o
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2019年6月16日 下午7:58:46
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String e = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + e + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[0]);
			return method.invoke(o, new Object[0]);
		} catch (Exception var6) {
			return null;
		}
	}

}
