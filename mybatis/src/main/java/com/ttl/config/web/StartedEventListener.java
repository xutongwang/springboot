package com.ttl.config.web;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ttl.config.constant.DataConstant;

@Component
public class StartedEventListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(DataConstant.getContext() == null){
			DataConstant.setApplicationContext(event.getApplicationContext());
    	}
	}
	
}
