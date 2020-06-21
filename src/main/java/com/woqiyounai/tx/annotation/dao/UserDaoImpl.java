package com.woqiyounai.tx.annotation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addMoney() {
        String sql = "update t_account set money=money+? where username=?";
        int mary = jdbcTemplate.update(sql, 100, "mary");
        System.out.println(mary);
    }

    public void reduceMoney() {
        String sql = "update t_account set money=money-? where username=?";
        int lucy = jdbcTemplate.update(sql, 100, "lucy");
        System.out.println(lucy);
    }
}
