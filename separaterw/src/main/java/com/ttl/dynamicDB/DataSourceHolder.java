package com.ttl.dynamicDB;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceHolder {
	
	private static final Logger log = LoggerFactory.getLogger(DataSourceHolder.class);
	
	private static final ThreadLocal<String> local = new ThreadLocal<String>();
    
	public static Set<String> setDataSourceIDs = new HashSet<>();
	
    public static void setDataSource(String dataSourceType){
    	local.set(dataSourceType);
    }
    
    public static String getDataSource(){
    	log.info(" { 获取数据源 :"+local.get()+" } ");
    	return local.get();
    }
    
    public static boolean containsDataSource(String dataSourceID){
    	log.info(" { 验证数据源是否存在 } ");
    	return setDataSourceIDs.contains(dataSourceID);
    }
    
    public static void clearDataSource(){
    	log.info("{ 移除数据源项 }");
    	local.remove();
    }
}
