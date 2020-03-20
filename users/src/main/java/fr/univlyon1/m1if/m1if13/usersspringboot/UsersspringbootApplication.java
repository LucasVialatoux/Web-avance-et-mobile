package fr.univlyon1.m1if.m1if13.usersspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UsersspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersspringbootApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("http://localhost", "http://192.168.75.38")
				.allowedHeaders("Authentication", "Origin")
				.exposedHeaders("Authentication");
			}
		};
	}
}
