package com.louis.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louis.springbootmybatisplus.entity.People;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PeopleMapper extends BaseMapper<People> {


}
