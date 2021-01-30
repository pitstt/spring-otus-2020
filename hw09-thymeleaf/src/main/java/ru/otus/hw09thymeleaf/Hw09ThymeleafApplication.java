package ru.otus.hw09thymeleaf;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Hw09ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hw09ThymeleafApplication.class, args);
	}

}
