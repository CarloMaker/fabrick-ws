package it.fabrick.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"it.fabrick.model"})
public class FabrickWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabrickWsApplication.class, args);
	}

}
