package com.example.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.service.MemberService;
import com.example.demo.service.ProductService;

@Service
@Transactional
public class MemberServiceImp implements MemberService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Integer id, String username, String password) {
        jdbcTemplate.update("INSERT INTO users(id,username,password) VALUES(?,?,?)",id,username,password);
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?",id);
    }

    @Override
    public Integer getProductSum() {
        return  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE 1=1",Integer.class);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM users WHERE 1=1");
    }
}