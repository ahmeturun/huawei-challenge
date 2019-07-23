package com.example.todo.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String hello() {
	   return "Hello World";
	}

	@RequestMapping(value = "/SignIn")
	public String signIn() {
	   return "Hello World";
	}

	@RequestMapping(value = "/LogIn")
	public String logIn() {
	   return "Hello World";
	}

	@RequestMapping(value = "/CreateList")
	public String createList() {
	   return "Hello World";
	}
}
