package com.ttl.dynamicDB;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 *spirng3.1提供EnvironmentAware接口，用于获取和改变配置文件
 *spirng3.1提供ImportBeanDefinitonRegistrar接口，用于运行时自动添加Bean到已有的配置类
 */
public class DataSourceConfig implements ImportBeanDefinitionRegistrar, EnvironmentAware{
	private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);
	private DataSource defaultDataSource;
	private static final String DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
	private ConversionService conversionService = new DefaultConversionService();
	private PropertyValues dataSourcePropertyValues;
	private Map<String, DataSource> customDataSources = new HashMap<>();

	@Override
	public void setEnvironment(Environment environment) {
		log.info("设置环境变量");
		//默认数据源加载
		initDefaultDataSource(environment);
		initCustomDataSource(environment);
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// TODO Auto-generated method stub
		log.info("运行时自动添加Bean到已有的配置类");
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		
		// --- 将主数据源添加到更多数据源中
		targetDataSources.put("dataSource", defaultDataSource);
		DataSourceHolder.setDataSourceIDs.add("dataSource");
		
		// --- 添加更多数据源
		targetDataSources.putAll(customDataSources);
		for(String key : customDataSources.keySet()){
			DataSourceHolder.setDataSourceIDs.add(key);
		}
		
		// --- 创建DynamicDataSource
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
       
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        //添加属性：AbstractRoutingDataSource.defaultTargetDataSource
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);
	}
	
	/**
	 * 默认数据源配置
	 * @param environment
	 */
	private void initDefaultDataSource(Environment environment){
		log.info("设置默认数据源配置");
		RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(environment);
		Map<String, Object> properties = relaxedPropertyResolver.getSubProperties("spring.datasource.");
		if (properties == null || properties.isEmpty()) {
			log.info("以spring.datasource.为前缀的属性在默认数据源配置文件中不存在");
			return;
		}
        defaultDataSource = buildDataSource(properties);
        dataBinder(defaultDataSource, environment);
	}
	
	private void initCustomDataSource(Environment environment){
		log.info("设置自定义数据源配置");
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, "custom.datasource.");
        String dsPrefixs = propertyResolver.getProperty("names");
        for (String dsPrefix : dsPrefixs.split(",")) {
            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
            dataBinder(ds, environment);
        }
	}
	
	@SuppressWarnings("unchecked")
	public DataSource buildDataSource(Map<String, Object> properties){
		
		Object objType = properties.get("type");
		if(objType==null){
			objType = DATASOURCE_TYPE_DEFAULT;
		}
		
		Class<? extends DataSource> dataSourceType;
        try {
        	dataSourceType = (Class<? extends DataSource>)Class.forName((String) objType);
        	String driverClassName = properties.get("driverClassName").toString();
        	String url = properties.get("url").toString();
        	String username = properties.get("username").toString();
        	String password = properties.get("password").toString();
        	DataSourceBuilder factory =   DataSourceBuilder
        		.create()
        		.driverClassName(driverClassName)
        		.url(url)
        		.username(username)
        		.password(password)
        		.type(dataSourceType);
        	
        	return factory.build();
        	
        }catch(ClassNotFoundException e) {
        	e.printStackTrace();
        }
		
		return null;
	}
	
	private void dataBinder(DataSource dataSource, Environment environment){
		RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);
        dataBinder.setIgnoreInvalidFields(false);
        dataBinder.setIgnoreUnknownFields(true);
        if(dataSourcePropertyValues == null){
        	Map<String, Object> rpr = new RelaxedPropertyResolver(environment, "spring.datasource").getSubProperties(".");
        	Map<String, Object> values = new HashMap<>(rpr);
			values.remove("type");
			values.remove("driverClassName");
			values.remove("url");
			values.remove("username");
			values.remove("password");
			dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
	}
}
