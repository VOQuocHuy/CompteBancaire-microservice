package fr.dauphine.miageif.msa.projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
	public class MsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaApplication.class, args);
	}
	
}