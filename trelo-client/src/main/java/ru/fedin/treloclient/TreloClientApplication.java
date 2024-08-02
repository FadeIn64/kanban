package ru.fedin.treloclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "ru.fedin.treloclient.repositories.redis")
public class TreloClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreloClientApplication.class, args);
    }

}
