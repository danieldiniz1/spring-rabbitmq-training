package br.com.sh.appproposta.config.web;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost/")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD");
    }
}
