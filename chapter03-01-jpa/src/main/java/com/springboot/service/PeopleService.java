package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.People;
import com.springboot.repository.PeopleRepository;
import com.springboot.util.Utils;

/**
 * @author Louis
 * @title: PeopleService
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 16:22
 */
@Service
public class PeopleService {

	@Autowired
	private PeopleRepository peopleRepository;

	public List findAll() {
		return peopleRepository.findAll();
	}

	public void delete(Integer integer) {
		peopleRepository.deleteById(integer);
	}

	public People flush(People people) {
		// 获取原对象
		People updatePeople = peopleRepository.findById(people.getPeopleId()).get();
		// 通过赋值修改的新的字段值。
		BeanUtils.copyProperties(people, updatePeople, Utils.getNullPropertyNames(people));
		return peopleRepository.saveAndFlush(updatePeople);
	}

	public People save(People people) {
		return peopleRepository.save(people);
	}

	public Optional<People> findById(Integer id) {
		return peopleRepository.findById(id);
	}

}
