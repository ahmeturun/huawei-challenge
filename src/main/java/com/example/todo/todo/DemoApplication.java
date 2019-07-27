package com.example.todo.todo;

import com.example.todo.todo.repository.ToDoListRepository;
import com.example.todo.todo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import com.example.todo.todo.entity.Status;
import com.example.todo.todo.entity.ToDoItem;
import com.example.todo.todo.entity.ToDoList;
import com.example.todo.todo.entity.User;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableJpaRepositories("com.example.todo.todo.repository") 
@SpringBootApplication
@RestController
public class DemoApplication {

private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ToDoListRepository repository,
		UserRepository userRepository
	){
		return (args) -> {
			User user = new User(Long.valueOf(0), "test", "passwrd", "sessionid", Long.valueOf(0));
			userRepository.save(user);


			ToDoItem item1 = new ToDoItem(Long.valueOf(0), "innerItem", "innerdesc", "1563996931", Status.NotComplete, null);
			Set<ToDoItem> dependents = new HashSet<ToDoItem>();
			dependents.add(item1);
			ToDoItem item2 = new ToDoItem(Long.valueOf(0), "dependencyItem", "item with dependency", "1563996931", Status.NotComplete, dependents);
			
			Set<ToDoItem> items = new HashSet<ToDoItem>();
			items.add(item1);
			items.add(item2);
			ToDoList toDoList = new ToDoList(Long.valueOf(0), "testList", Long.valueOf(1), items);

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
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }
}
