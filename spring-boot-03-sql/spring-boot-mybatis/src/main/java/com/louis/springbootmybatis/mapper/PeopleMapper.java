package com.louis.springbootmybatis.mapper;

import com.louis.springbootmybatis.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PeopleMapper {


    void save(People people);

    void delete(@Param("id") Integer id);

    void update(People people);

    List<People> findById(@Param("id") Integer id);

}
