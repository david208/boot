package com.minitech.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements CommandLineRunner, Ordered {

	public void run(String... args) {
		// Do something...
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
