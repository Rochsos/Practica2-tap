package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("Modelo")
@ComponentScan({"Controlador", "Vista"})
@EnableJpaRepositories("Repositorio")
public class Practica2LuisaRocioApplication {

	private static final Logger log = LoggerFactory.getLogger(Practica2LuisaRocioApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Practica2LuisaRocioApplication.class, args);
	}

}
