package com.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringHomeApplication {
	
	//사이트 메뉴 번호
	public static final Integer SITEMENUID = 322;

	public static void main(String[] args) {
		SpringApplication.run(SpringHomeApplication.class, args);
	}
}
