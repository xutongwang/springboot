package com.ttl.dao;

import com.ttl.domain.UserDomain;  
import org.springframework.data.jpa.repository.JpaRepository;  
   
public interface UserDao extends JpaRepository<UserDomain, Long> {  
    /** 
     * 根据用户名称查询用户 
     * @param userNo 
     * @return 
     */  
    UserDomain findByUserNo(String userNo);  
}