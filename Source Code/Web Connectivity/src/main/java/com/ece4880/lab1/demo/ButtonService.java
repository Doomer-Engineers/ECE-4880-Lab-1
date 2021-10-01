package com.ece4880.lab1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ButtonService {

    @Autowired
    JdbcTemplate template;

    public List<Button> findAll(){
        String sql = "select * from button";
        RowMapper<Button> rm = new RowMapper<Button>() {
            @Override
            public Button mapRow(ResultSet resultSet, int i) throws SQLException {
                Button row = new Button(resultSet.getLong("id"),
                        resultSet.getString("screen_bool"),
                        resultSet.getString("datetime"));
                return row;
            }
        };
        return template.query(sql, rm);
    }
}
