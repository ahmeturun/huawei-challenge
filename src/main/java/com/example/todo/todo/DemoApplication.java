package com.example.todo.todo;

import com.example.todo.todo.repository.ToDoListRepository;
import com.example.todo.todo.model.ToDoList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaRepositories("com.example.todo.todo.repository") 
@SpringBootApplication
@RestController
public class DemoApplication {

private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ToDoListRepository repository){
		return (args) -> {
			repository.save(new ToDoList("test", 1));
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (ToDoList customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

		};
	}

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
