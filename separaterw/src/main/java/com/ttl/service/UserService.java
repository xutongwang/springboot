package com.ttl.service;

import com.ttl.entity.User;

public interface UserService {
	User findByNickname(String nickname);
}
