package com.minitech.boot;

/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@Import(MyConfiguration.class)
@EntityScan(basePackages="com.minitech.boot.entity")
@EnableAspectJAutoProxy
public class SampleWebFreeMarkerApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication app = new SpringApplication(SampleWebFreeMarkerApplication.class);
		app.setShowBanner(false);
		app.setWebEnvironment(true);
		/* app.setAdditionalProfiles("dev"); */
		app.run();

		/* SpringApplication.run(SampleWebFreeMarkerApplication.class, args); */
	}

}
