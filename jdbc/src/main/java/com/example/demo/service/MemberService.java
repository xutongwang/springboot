package com.example.demo.service;

public interface MemberService {

    /**
     * 新建一个产品
     * @param id
     * @param name
     * @param description
     */
    void create(Integer id,String name,String description);

    /**
     * 删除某个产品
     * @param id
     */
    void deleteById(Integer id);


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