package fr.univlyon1.m1if.m1if13.usersspringboot.controller;

import fr.univlyon1.m1if.m1if13.usersspringboot.model.User;
import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * UserController
 * Gère les requêtes suivantes : GET ; PUT ; POST ; DELETE;
 * 
 */
@RestController
public class UserController {
    
    @Autowired
    private ApplicationContext ctx;
    
    /**
    * GET request
     * @param id
     * @return 
    */
    @GetMapping("/user")
    public Optional<User> get(@RequestParam(value = "id") String id){
        UserDao u = ctx.getBean(UserDao.class);
        return u.get(id);
    }
    
    /**
    * POST request
     * @param login
     * @param password
     * @return 
    */
    @PostMapping("/user")
    public User post(@RequestParam(value = "login") String login,
    @RequestParam(value = "password") String password){
        UserDao u = ctx.getBean(UserDao.class);
        User user = new User(login,password);
        u.save(user);
        return user;
    }
    
    /**
    * PUT request
     * @param id
     * @param login
     * @param password
     * @return 
    */
    @PutMapping("/user")
    public User put(@RequestParam(value = "id") String id,
    @RequestParam(value = "login") String login,
    @RequestParam(value = "password") String password){
        UserDao u = ctx.getBean(UserDao.class);
        String[] params = null;
        params[0]=login;
        params[1]=password;
        Optional<User> user = u.get(id);
        u.update(user.get(),params);
        return user.get();
    }
    
    /**
    * DELETE request
     * @param id
    */
    @DeleteMapping("/user")
    public void delete(@RequestParam(value = "id") String id){
        UserDao u = ctx.getBean(UserDao.class);
        Optional<User> user = u.get(id);
        u.delete(user.get());
    }
}
