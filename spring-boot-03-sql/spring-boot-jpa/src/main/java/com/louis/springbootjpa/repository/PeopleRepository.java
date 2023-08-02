package com.louis.springbootjpa.repository;

import com.louis.springbootjpa.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Louis
 * @title: PeopleRepository
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 15:18
 */
public interface PeopleRepository extends JpaRepository<People, Integer> {
}
