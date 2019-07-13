package com.springboot.db.mapper;

import com.springboot.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Louis
 * @title: PeopleRowMapper
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/7/13 14:36
 */
public class PeopleRowMapper implements RowMapper<People> {

    @Override
    public People mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new People(rs.getInt("peopleId"), rs.getString("peopleName"), rs.getString("password"), rs.getString(
                "salt"), rs.getInt("status"), rs.getDate("modifyTime"), rs.getDate("createTime"));
    }
}
