package com.example.todo.todo;

import com.example.todo.todo.repository.ToDoListRepository;
import com.example.todo.todo.model.Status;
import com.example.todo.todo.model.ToDoItem;
import com.example.todo.todo.model.ToDoList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
			ToDoItem item1 = new ToDoItem("innerItem", "innerdesc", "1563996931", Status.NotComplete);
			ToDoItem item2 = new ToDoItem("dependencyItem", "item with dependency", "1563996931", Status.NotComplete);
			// item2.addDependent(item1);
			ToDoList toDoList = new ToDoList("testList", Long.valueOf(1), item1, item2);

			repository.save(toDoList);
			// fetch all lists
			log.info("lists found with findAll():");
			log.info("-------------------------------");
			for (ToDoList customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

		};
	}
}
