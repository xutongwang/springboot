package com.ttl.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ttl.entity.User;

public interface IUserDao extends JpaRepository<User, String> {
	User findByNickname(String nickname);
}
