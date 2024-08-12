package ru.fedin.trelorefactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "ru.fedin.trelorefactor.repositories.jpa")
@SpringBootApplication
public class TreloRefactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreloRefactorApplication.class, args);
    }

}
