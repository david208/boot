package com.minitech.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minitech.boot.service.CityService;

@RestController
@RequestMapping("/")
public class CityController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CityService cityService;

	@RequestMapping
	String test(@RequestParam(defaultValue = "0") int i) {
		 cityService.addCity(i); 
		return "Hello World! test";

	}

	@RequestMapping("/home1")
	String test1(@RequestParam(defaultValue = "1") int i) {
		cityService.findCity(i);
		return "Hello World! test1 ";

	}

}
