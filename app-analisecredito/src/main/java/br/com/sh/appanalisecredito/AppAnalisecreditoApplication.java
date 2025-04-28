package br.com.sh.appanalisecredito;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppAnalisecreditoApplication {


	public static void main(String[] args) {
		SpringApplication.run(AppAnalisecreditoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
//			analiseCreditoService.getListCalculadoras();
		};
	}

}
