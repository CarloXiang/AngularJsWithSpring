package com.comolroy.saajs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource("classpath:/spring-config.xml")
@EnableAutoConfiguration
@EnableTransactionManagement // Enabling the Transactional properties of DAO entities
public class SpringAndAngularJsApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringAndAngularJsApplication.class);
	
	public static void main(String[] args) {
		
		
		ApplicationContext ctx = SpringApplication.run(SpringAndAngularJsApplication.class, args);
		
		/*
		 * Loading and printing created bean in applicaiton context.
		 */
		
		String beanNames[] = ctx.getBeanDefinitionNames();
		
		for (String beanName : beanNames) {
			logger.info(">*[ " + beanName + " ]*<");
		}
	}
}
