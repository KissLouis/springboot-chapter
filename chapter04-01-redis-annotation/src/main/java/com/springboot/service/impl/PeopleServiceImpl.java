package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.dao.PeopleDao;
import com.springboot.entity.People;
import com.springboot.service.PeopleService;

/**
 * @author Louis
 * @title: PeopleServiceImpl
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/28 10:39
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleDao peopleDao;

    @Override
    public void save(People people) {
        peopleDao.save(people);
    }

    @Override
    @CacheEvict(value = "people", key = "#id")
    public void delete(Integer id) {
        peopleDao.delete(id);
    }

    @Override
    @CachePut(value = "people", key = "#people.peopleId", unless = "#result == null")
    public People update(People people) {
        //DB更新操作
        peopleDao.update(people);
        //更新成功之后将值重新放入缓存
        List<People> list = peopleDao.find(people.getPeopleId());
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    @Cacheable(value = "people", key = "methodName", unless = "#result == null || #result.size() == 0")
    public List<People> findAll() {
        return peopleDao.find(null);
    }

    @Override
    @Cacheable(value = "people", key = "#id", unless = "#result == null")
    public People findById(Integer id) {
        List<People> list = peopleDao.find(id);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
