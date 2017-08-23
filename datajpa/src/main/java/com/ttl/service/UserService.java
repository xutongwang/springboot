package com.ttl.service;

import com.ttl.domain.UserDomain;  

public interface UserService {  
    /** 
     * 根据用户编号获取用户信息 
     * @param userNo 
     * @return 
     */  
    UserDomain findUserByUserNo(String userNo);  
}  
