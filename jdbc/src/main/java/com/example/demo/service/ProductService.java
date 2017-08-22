package com.example.demo.service;

/**
 * Created by jinhuaquan on 2017/7/7.
 */
public interface ProductService {

    /**
     * 新建一个产品
     * @param id
     * @param name
     * @param description
     */
    void create(Long id,String username,String password);

    /**
     * 删除某个产品
     * @param id
     */
    void deleteById(Long id);


    /**
     * 获取产品总数
     * @return
     */
    Integer getProductSum();

    /**
     * 删除所有的产品
     */
    void deleteAll();

}
