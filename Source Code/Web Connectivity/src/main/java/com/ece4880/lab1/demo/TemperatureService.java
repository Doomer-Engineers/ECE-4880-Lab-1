package com.ece4880.lab1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TemperatureService {

    @Autowired
    JdbcTemplate template;

    public List<Temperature> findAll(){
        String sql = "select * from temperature";
        RowMapper<Temperature> rm = new RowMapper<Temperature>() {
            @Override
            public Temperature mapRow(ResultSet resultSet, int i) throws SQLException {
                Temperature row = new Temperature(resultSet.getLong("id"),
                        resultSet.getFloat("temp"),
                        resultSet.getString("datetime"));
                return row;
            }
        };
        return template.query(sql, rm);
    }
}
