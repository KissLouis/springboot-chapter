package com.springboot.repository;

import com.springboot.entity.People;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Louis
 * @title: PeopleRepository
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 15:18
 */
public interface PeopleRepository extends JpaRepository<People, Integer> {
    @Override
    <S extends People> S saveAndFlush(S s);

    @Override
    <S extends People> S save(S s);

    @Override
    Optional<People> findById(Integer id);

    @Override
    public List<People> findAll();

    @Override
    public void deleteAll();
}
