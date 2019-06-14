package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.cluster.PetDao;
import com.springboot.dao.master.PeopleDao;
import com.springboot.entity.People;
import com.springboot.entity.Pet;
import com.springboot.service.PeopleService;

/**
 * @author Louis
 * @title: PeopleServiceImpl
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/28 10:39
 */
@Service
public class PeopleServiceImpl implements PeopleService {

	// master数据源
	@Autowired
	private PeopleDao peopleDao;

	// cluster数据源
	@Autowired
	private PetDao petDao;

	@Override
	public List<People> findAll() {
		return peopleDao.find();
	}

	@Override
	public People findById(Integer id) {
		// 多数据源查询
		People people = peopleDao.find(id);
		Pet pet = petDao.findById(id);
		
		people.setPet(pet);
		return people;
	}

}
