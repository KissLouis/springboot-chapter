package com.louis.springbootmybatisannotation.mapper;

import com.louis.springbootmybatisannotation.entity.People;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PeopleMapper {


    @Insert("<script> INSERT INTO people ( NAME, age, remark )VALUES (#{name},#{age},#{remark}) </script>")
    Integer save(People people);

    @Delete("delete from people where id = #{id}")
    void delete(@Param("id") Integer id);

    @Update("<script> update people <set> <if test=' name != null '>name=#{name},</if> <if test=' " +
            "age != null '>age=#{age},</if><if test=' remark != null '>remark=#{remark}</if> </set> where " +
            "id=#{id} </script>")
    void update(People people);

    @Select("<script> select * from people where 1 = 1 <if test = 'id != null '> and id = #{id} </if> </script>")
    List<People> findById(@Param("id") Integer id);

}
