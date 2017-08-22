package com.example.demo.serviceImp;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jinhuaquan on 2017/7/7.
 */
@Service
@Transactional
public class ProductServiceImp implements ProductService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Long id, String name, String description) {
        jdbcTemplate.update("INSERT INTO product(id,name,description) VALUES(?,?,?)",id,name,description);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?",id);
    }

    @Override
    public Integer getProductSum() {
        return  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product WHERE 1=1",Integer.class);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM product WHERE 1=1");
    }
}
