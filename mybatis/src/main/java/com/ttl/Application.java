package com.ttl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//import groovy.util.logging.Slf4j;


@SpringBootApplication
//扫描mybatis接口
@MapperScan("com.ttl.mapper")
//@Slf4j
public class Application {
    
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
}