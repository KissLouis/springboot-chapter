package com.springboot.dao.cluster;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.entity.Pet;

/**
 * @author Louis
 * @title: PetDao
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/28 10:36
 */
@Mapper
public interface PetDao {

    Pet findById(@Param("id") Integer id);

}
