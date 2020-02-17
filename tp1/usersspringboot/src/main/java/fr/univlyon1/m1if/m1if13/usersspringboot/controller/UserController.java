package fr.univlyon1.m1if.m1if13.usersspringboot.controller;

import fr.univlyon1.m1if.m1if13.usersspringboot.model.User;
import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
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
    private UserDao u = UserDao.createInstance();
    
    /**
    * GET request
     * @param id
     * @return 
    */
    @GetMapping(value="/users")
    public Optional<User> get(@RequestParam(value = "id") String id){
        return u.get(id);
    }
    
    /**
    * GET request
     * @param id
     * @return 
    */
    @GetMapping(value="/users",produces=MediaType.APPLICATION_XML_VALUE)
    public Optional<User> getXML(@RequestParam(value = "id") String id){
        return u.get(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value="/users",produces=MediaType.TEXT_HTML_VALUE)
    public void getHTML (@RequestParam(value = "id") String id, Model model) {
        Optional<User> user = u.get(id);
        User us = user.get();
        model.addAttribute("login", us.getLogin());
        model.addAttribute("connected", us.isConnected());
    }
    
    /**
    * POST request
     * @param login
     * @param password
     * @return 
    */
    @PostMapping("/users")
    public User post(@RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password){
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
    @PutMapping("/user/login")
    public String[] put(@RequestParam(value = "id") String id,
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password){
        String[] params={login,password};
        Optional<User> user = u.get(id);
        u.update(user.get(),params);
        return params;
    }
    
    /**
    * DELETE request
     * @param id
    */
    @DeleteMapping("/user/login")
    public void delete(@RequestParam(value = "id") String id){
        Optional<User> user = u.get(id);
        u.delete(user.get());
    }
}
