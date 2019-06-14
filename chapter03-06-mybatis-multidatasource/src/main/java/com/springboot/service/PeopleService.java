package com.springboot.service;

import java.util.List;

import com.springboot.entity.People;

/**
 * @author Louis
 * @title: PeopleService
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/28 10:38
 */
public interface PeopleService {

	List<People> findAll();

	People findById(Integer id);

}
