package fr.univlyon1.m1if.m1if13.usersspringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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