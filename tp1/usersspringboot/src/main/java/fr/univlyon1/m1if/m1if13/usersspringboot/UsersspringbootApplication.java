package fr.univlyon1.m1if.m1if13.usersspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import fr.univlyon1.m1if.m1if13.usersspringboot.model.User;

@SpringBootApplication
public class UsersspringbootApplication {

	//@Autowired
	private static ApplicationContext ctx;

	public static void main(String[] args) {
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		UserDao dao = ctx.getBean(UserDao.class);

		dao.save(new User("test", "test"));

		System.out.println(dao.get("test"));

		SpringApplication.run(UsersspringbootApplication.class, args);
	}

}
