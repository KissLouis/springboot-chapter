package com.springboot.dao;

import org.springframework.stereotype.Repository;

import com.springboot.entity.People;
import com.springboot.util.MongoDbDao;

@Repository
public class PeopleDao extends MongoDbDao<People> {

	@Override
	protected Class<People> getEntityClass() {
		// TODO Auto-generated method stub
		return People.class;
	}

}
