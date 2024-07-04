package ru.fedin.deskswagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.fedin.deskswagger.schema.Desk;

@SpringBootApplication
public class DeskSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeskSwaggerApplication.class, args);
        System.out.println(Desk.SAMPLE);
    }

}
