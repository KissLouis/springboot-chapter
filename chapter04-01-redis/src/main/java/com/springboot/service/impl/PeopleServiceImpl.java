package com.springboot.service.impl;

import com.springboot.dao.PeopleDao;
import com.springboot.entity.People;
import com.springboot.service.PeopleService;
import com.springboot.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleServiceImpl.class);

    @Autowired
    private PeopleDao peopleDao;

    @Autowired
    private RedisUtil redisUtil;

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
    public List<Object> findAll() {
        List<Object> peopleList = new ArrayList<>();
        //从Redis中获取People信息
        String key = "findAllPeople";
        //缓存存在
        if (redisUtil.hasKey(key)) {
            peopleList = redisUtil.lGet(key, 0, -1);
            LOGGER.info("PeopleServiceImpl.findAll() : 查询全部People缓存 >> " + peopleList.toString());
            return peopleList;
        }
        // 从 DB 中获取People信息
        peopleList = peopleDao.find(null);
        //插入到缓存当中
        redisUtil.lSet(key, peopleList);
        LOGGER.info("PeopleServiceImpl.findAll() : 插入缓存 >> " + peopleList.toString());
        return peopleList;
    }

    @Override
    public Object findById(Integer id) {
        List<Object> list = peopleDao.find(id);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
