package com.springboot.dao;

import com.springboot.entity.People;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Louis
 * @title: PeopleDao
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/28 10:36
 */
@Mapper
public interface PeopleDao {

    @Insert("insert into people(peopleName,password,salt,status) VALUES (#{peopleName},#{password},#{salt},1)")
    void save(People people);

    @Delete("delete from people where peopleId = #{id}")
    void delete(@Param("id") Integer id);

    @Update("<script> update people <set> <if test=' peopleName != null '>peopleName=#{peopleName},</if> <if test=' " +
            "salt != null '>salt=#{salt},</if><if test=' status != null '>status=#{status}</if> </set> where " +
            "peopleId=#{peopleId} </script>")
    void update(People people);

    @Select("<script> select * from people where 1 = 1 <if test = 'id != null '> and peopleId = #{id} </if> </script>")
    List<People> find(@Param("id") Integer id);

}
