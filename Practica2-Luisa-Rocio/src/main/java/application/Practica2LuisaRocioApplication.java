package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"Controlador"})
public class Practica2LuisaRocioApplication {

	public static void main(String[] args) {
		SpringApplication.run(Practica2LuisaRocioApplication.class, args);
	}

}
