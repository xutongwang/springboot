package com.ttl.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttl.dao.IUserDao;
import com.ttl.dynamicDB.TargetDataSource;
import com.ttl.entity.User;

@Service("userService") 
public class IUserService implements UserService{

	@Autowired  
    private IUserDao userDao;
	
	@TargetDataSource("slave")
	@Override
	public User findByNickname(String nickname) {
		return userDao.findByNickname(nickname);
	}

}
