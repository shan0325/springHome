package com.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringHomeApplication extends SpringBootServletInitializer {
	
	//사이트 메뉴 번호
	public static final Integer SITEMENUID = 322;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringHomeApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringHomeApplication.class, args);
	}
}
