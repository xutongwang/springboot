package com.ttl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.ttl.domain.UserDomain;
import com.ttl.service.UserService;  
   
@RestController  
@RequestMapping(value = "/")  
public class HelloWorldController {  
	@Autowired  
    private UserService userService;
    @RequestMapping(value = "/hello")  
    public String sayHello(){  
        return "Hello word!";  
    }  
    /** 
     * 根据用户编号获取用户信息 
     * @param userNo 
     * @return 
     */  
    @RequestMapping(value = "/getUserByNo")  
    public UserDomain getUserByNo(String userNo){  
        UserDomain user=null;  
        try {  
            user=userService.findUserByUserNo(userNo);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return user;  
    }
}