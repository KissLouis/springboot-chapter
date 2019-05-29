package com.springboot.service.impl;

import com.springboot.dao.PeopleDao;
import com.springboot.entity.People;
import com.springboot.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void delete(Integer id) {
        peopleDao.delete(id);
    }

    @Override
    public void update(People people) {
        peopleDao.update(people);
    }

    @Override
    public List<People> findAll() {
        return peopleDao.find(null);
    }

    @Override
    public People findById(Integer id) {
        List<People> list = peopleDao.find(id);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
