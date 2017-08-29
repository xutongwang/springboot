package com.ttl.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ttl.dynamicDB.DataSourceHolder;
import com.ttl.dynamicDB.TargetDataSource;

@Aspect
@Order(-10)
@Component
public class DynamicDataSourceAspect {
	
	private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);	

	@Before("@annotation(targetDataSource)")
	public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable{
		String dataSourceID = targetDataSource.value();		
		if(!DataSourceHolder.containsDataSource(dataSourceID)){
			log.debug(" { 指定的数据源ID:"+dataSourceID+"不存在!"+point.getSignature()+" } ");			
		} else {			
			DataSourceHolder.setDataSource(dataSourceID);
		}
	}
	@After("@annotation(targetDataSource)")
	public void resotreDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable{		
		DataSourceHolder.clearDataSource();
		log.info(" { 接点的方法签名对象:"+point.getSignature()+"; 切换数据源的:"+targetDataSource.value()+" }");
	}
}