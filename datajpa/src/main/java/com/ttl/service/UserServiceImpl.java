package com.ttl.service;

import com.ttl.dao.UserDao;  
import com.ttl.domain.UserDomain;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  

@Service("userService")  
public class UserServiceImpl implements UserService{  
    @Autowired  
    private UserDao userDao;  
    @Override  
    public UserDomain findUserByUserNo(String userNo) {  
        return userDao.findByUserNo(userNo);  
    }  
}
