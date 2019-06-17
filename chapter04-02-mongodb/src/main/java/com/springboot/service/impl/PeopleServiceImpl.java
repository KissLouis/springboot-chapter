package com.springboot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.PeopleDao;
import com.springboot.entity.People;
import com.springboot.service.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleDao peopleDao;

	@Override
	public void save(People people) {
		people.setCreateTime(new Date());
		peopleDao.save(people);
	}

	@Override
	public void delete(Integer id) {
		peopleDao.delete(findById(id));
	}

	@Override
	public void update(People people) {
		// 更新查询返回结果集的第一条
		peopleDao.updateFirst(findById(people.getPeopleId()), people);
	}

	@Override
	public List<People> findAll() {
		return peopleDao.queryList(null);
	}

	@Override
	public People findById(Integer id) {
		People people = new People(id);
		return peopleDao.queryOne(people);
	}
}
