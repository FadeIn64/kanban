package ru.fedin.trelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.fedin.trelo.mappers.DeskMapper;
import ru.fedin.trelo.repositories.jpa.DeskRepository;

@EnableJpaRepositories(basePackages = "ru.fedin.trelo.repositories.jpa")
@SpringBootApplication
public class TreloApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(TreloApplication.class, args);

//		var repo = ctx.getBean(DeskRepository.class);
//		var mapper = ctx.getBean(DeskMapper.class);
//		System.out.println(mapper.toDto(repo.findById(1).get()).getAuthor());
	}

}
