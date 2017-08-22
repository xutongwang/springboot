package com.ttl.config.constant;

import org.springframework.context.ApplicationContext;

public class DataConstant {
	
	private static ApplicationContext applicationContext ;
	
	
	public static void setApplicationContext(ApplicationContext context){
		applicationContext = context ;
	}
	
	public static ApplicationContext getContext(){
		return applicationContext ;
	}



}
