package fr.univlyon1.m1if.m1if13.usersspringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
            info = @Info(
                    title = "User API [M1IF13]",
                    version = "0.0",
                    description = "University project",
                    contact = @Contact(url = "https://forge.univ-lyon1.fr/p1600354/m1if13-2020", name = "Paul & Lucas project")
            ),
            servers = {
                    @Server(
                            description = "API",
                            url = "http://192.168.75.38:8080"
                            )
            }
    )
@Configuration
@EnableWebMvc
@ComponentScan("com.thymeleafexamples")
public class AppConfig implements WebMvcConfigurer {
    
    private ApplicationContext applicationContext;
    public static final String MEDIA_TYPE_JSONLD  = "application/ld+json";
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.valueOf(MEDIA_TYPE_JSONLD));
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public UserDao myUserDao() {
        return UserDao.createInstance();
    }
}