package com.minitech.boot;

import java.nio.charset.Charset;
import java.util.Collections;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.minitech.boot.monitor.impl.ZooL;
import com.minitech.boot.monitor.impl.ZooMonitor;
import com.minitech.boot.service.LogNofityObserver;

@Configuration
@EnableSolrRepositories(schemaCreationSupport = true, basePackages = "com.snowstore.log.repository.solr", multicoreSupport = true)
public class MyConfiguration extends WebMvcConfigurerAdapter {

	@Bean(name = "customConverters")
	public HttpMessageConverters customConverters() {
		HttpMessageConverter<?> additional = new StringHttpMessageConverter(Charset.forName("UTF-8"));

		HttpMessageConverter<?> another = new MappingJackson2HttpMessageConverter();
		return new HttpMessageConverters(additional, another);
	}

	// 用于处理编码问题
	/*
	 * @Bean public Filter characterEncodingFilter() { CharacterEncodingFilter
	 * characterEncodingFilter = new CharacterEncodingFilter();
	 * characterEncodingFilter.setEncoding("UTF-8");
	 * characterEncodingFilter.setForceEncoding(true); return
	 * characterEncodingFilter; }
	 */
	@Bean
	public RequestContextListener getRequestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	public BeanFactoryAware persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public FilterRegistrationBean encodingFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		registrationBean.setFilter(characterEncodingFilter);
		registrationBean.setOrder(0);
		registrationBean.setUrlPatterns(Collections.singletonList("/*"));
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean openEntityManagerInViewFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		OpenEntityManagerInViewFilter contextFilter = new OpenEntityManagerInViewFilter();
		registrationBean.setFilter(contextFilter);
		registrationBean.setOrder(1);
		return registrationBean;
	}
	
	@Autowired
	private LogNofityObserver logNofityObserver;
	
	
	@Bean
	public ZooMonitor getZooMonitor(){
		ZooMonitor zooMonitor = new ZooMonitor();
		return zooMonitor;
		
	}
	
	@Bean
	public ZooL getZooL() {
		ZooL zooL =new ZooL();
		zooL.setNofityObserver(logNofityObserver);
		return zooL;
	}
	
	
	

	/*
	 * @Bean public FreeMarkerViewResolver freeMarkerViewResolver(){
	 * FreeMarkerViewResolver freeMarkerViewResolver = new
	 * FreeMarkerViewResolver(); return freeMarkerViewResolver;
	 * 
	 * }
	 */
}
