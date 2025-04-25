package br.com.sh.appproposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AppPropostaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppPropostaApplication.class, args);
    }

}
