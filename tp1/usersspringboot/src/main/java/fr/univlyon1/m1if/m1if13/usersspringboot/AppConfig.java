package fr.univlyon1.m1if.m1if13.usersspringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;

@Configuration
public class AppConfig {

    private UserDao userdao = new UserDao();

    @Bean
    public UserDao myUserDao() {
        return userdao;
    }
}