package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

	private static final Logger logger = LoggerFactory.getLogger(PeopleService.class);

	// 这里的单引号不能少，否则会报错，被识别是一个对象
	private static final String CACHE_VALUE = "people";

	@Autowired
	private PeopleRepository peopleRepository;
	
	@Cacheable(value = "people", key = "methodName", unless = "#result == null || #result.size() == 0")
	public List<People> findAll() {
		logger.error("未进入缓存！");
		return peopleRepository.findAll();
	}

	@CacheEvict(value = CACHE_VALUE, key = "#id")
	public void delete(Integer id) {
		peopleRepository.deleteById(id);
	}

	@CachePut(value = CACHE_VALUE, key = "#people.peopleId", unless = "#result == null")
	public People flush(People people) {
		// 获取原对象
		People updatePeople = peopleRepository.findById(people.getPeopleId()).get();
		// 通过赋值修改的新的字段值。
		BeanUtils.copyProperties(people, updatePeople, Utils.getNullPropertyNames(people));
		return peopleRepository.saveAndFlush(updatePeople);
	}

	@CacheEvict(value = CACHE_VALUE, key = "#people.peopleId")
	public People save(People people) {
		return peopleRepository.save(people);
	}

	@Cacheable(value = "people", key = "#id", unless = "#result == null")
	public Optional<People> findById(Integer id) {
		logger.error("未进入缓存！" + id);
		return peopleRepository.findById(id);
	}

}
