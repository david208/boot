package com.minitech.boot;

import java.nio.charset.Charset;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class MyConfiguration {

	@Bean(name = "customConverters")
	public HttpMessageConverters customConverters() {
		HttpMessageConverter<?> additional = new StringHttpMessageConverter(Charset.forName("UTF-8"));

		HttpMessageConverter<?> another = new MappingJackson2HttpMessageConverter();
		return new HttpMessageConverters(additional, another);
	}
}
