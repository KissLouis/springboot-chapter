package com.louis.springbootmybatisflex.mapper;

import com.louis.springbootmybatisflex.entity.People;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PeopleMapper extends BaseMapper<People> {


}
