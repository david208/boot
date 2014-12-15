package com.minitech.boot;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableGlobalMethodSecurity
/* (basePackages = "com.minitech.boot") */
@RestController
public class Application extends WebMvcConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * @Bean public DataSource dataSource() { Config config = new
	 * HikariConfig(getClass
	 * ().getClassLoader().getResource("db.properties").getPath()); return new
	 * HikariDataSource(config); }
	 */

	@RequestMapping("/")
	String home() {
		
		logger.debug(a + "");
		return "Hello World!";

	}

	@Value("${my.number}")
	private int a;

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(Application.class);
		app.setShowBanner(false);
		app.setWebEnvironment(true);
		app.setAdditionalProfiles("dev");
		app.run();
	}

}
