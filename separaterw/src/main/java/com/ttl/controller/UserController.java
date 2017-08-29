package com.ttl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.ttl.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Gson gson = new Gson();
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findByNickname")
	public String findByNickname(@RequestParam(value = "nickname", required = true) String nickname){
		System.out.println(gson.toJson(userService.findByNickname(nickname)==null?"查询结果为空!":userService.findByNickname(nickname)));
		return gson.toJson(userService.findByNickname(nickname)==null?"查询结果为空!":userService.findByNickname(nickname));
	}
}
