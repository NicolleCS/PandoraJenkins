package br.com.pandora.projetopandora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableFeignClients
@EnableWebMvc
public class ProjetoPandoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPandoraApplication.class, args);
	}

}
