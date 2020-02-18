package fr.univlyon1.m1if.m1if13.usersspringboot.controller;

import fr.univlyon1.m1if.m1if13.usersspringboot.model.User;
import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import java.io.IOException;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * UserRestController
 Gère les requêtes suivantes : GET ; PUT ; POST ; DELETE;
 * 
 */
@RestController
public class UserRestController {
    private final UserDao u = UserDao.createInstance();
    
    /**
    * GET request
     * produces JSON
     * @param id
     * @return
    */
    @GetMapping(value="/users")
    public User getNormal(@RequestParam(value = "id") String id){
        Optional<User> user = u.get(id);
        return user.get();
    }
    
    /**
    * GET request
     * consumes JSON
     * produces XML | JSON
     * @param id
     * @return
     * @throws java.io.IOException
     * @throws org.json.JSONException
    */
    @GetMapping(value="/users",consumes = MediaType.APPLICATION_JSON_VALUE
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public User get(@RequestBody String id) throws IOException, JSONException{
        JSONObject jsonstring = new JSONObject(id);
        Optional<User> user = u.get(jsonstring.getString("id"));
        return user.get();
    }
    
    /**
    * GET request
     * consumes URL-encoded
     * produces XML | JSON
     * @param id
     * @return 
    */
    @GetMapping(value="/users",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public User getURL(@RequestParam(value = "id") String id){
        Optional<User> user = u.get(id);
        return user.get();
    }

    
    
    
    /**
    * POST request
     * consumes JSON
     * produces XML | JSON
     * @param json
     * @return 
     * @throws org.json.JSONException 
    */
    @PostMapping(value="/users",consumes = MediaType.APPLICATION_JSON_VALUE
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public User post(@RequestBody String json) throws JSONException{
        JSONObject jsonString = new JSONObject(json);
        User user = new User(jsonString.getString("login"),jsonString.getString("password"));
        u.save(user);
        return user;
    }
    
    /**
    * POST request
     * produces JSON
     * @param login
     * @param password
     * @return 
    */
    @PostMapping(value="/users")
    public User postNormal(@RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password){
        User user = new User(login,password);
        u.save(user);
        return user;
    }
    
    /**
    * POST request
     * consumes URL-encoded
     * produces XML | JSON
     * @param login
     * @param password
     * @return 
    */
    @PostMapping(value="/users",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public User postURL(@RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password){
        User user = new User(login,password);
        u.save(user);
        return user;
    }
    
    /**
    * PUT request
     * consumes JSON
     * produces XML | JSON
     * @param json
     * @return 
     * @throws org.json.JSONException 
    */
    @PutMapping(value="/user/login",consumes = MediaType.APPLICATION_JSON_VALUE
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String[] put(@RequestBody String json) throws JSONException{
        JSONObject jsonString = new JSONObject(json);
        String[] params={jsonString.getString("login"),jsonString.getString("password")};
        Optional<User> user = u.get(jsonString.getString("id"));
        u.update(user.get(),params);
        return params;
    }
    
    /**
    * PUT request
     * produces JSON
     * @param id
     * @param login
     * @param password
     * @return 
    */
    @PutMapping(value="/user/login")
    public String[] putNormal(@RequestParam(value = "id") String id,
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password){
        String[] params={login,password};
        Optional<User> user = u.get(id);
        u.update(user.get(),params);
        return params;
    }
    
    
    /**
    * PUT request
     * consumes URL-encoded
     * produces XML | JSON
     * @param id
     * @param login
     * @param password
     * @return 
    */
    @PutMapping(value="/user/login",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String[] putURL(@RequestParam(value = "id") String id,
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password){
        String[] params={login,password};
        Optional<User> user = u.get(id);
        u.update(user.get(),params);
        return params;
    }
    
    /**
    * DELETE request
     * consumes JSON
     * @param json
     * @throws org.json.JSONException
    */
    @DeleteMapping(value="/user/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody String json) throws JSONException{
        JSONObject jsonString = new JSONObject(json);
        Optional<User> user = u.get(jsonString.getString("id"));
        u.delete(user.get());
    }
    
    /**
    * DELETE request
     * @param id
    */
    @DeleteMapping(value="/user/login")
    public void deleteNormal(@RequestParam(value = "id") String id){
        Optional<User> user = u.get(id);
        u.delete(user.get());
    }
    
    /**
    * DELETE request
     * consumes URL-encoded
     * @param id
    */
    @DeleteMapping(value="/user/login",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void deleteURL(@RequestParam(value = "id") String id){
        Optional<User> user = u.get(id);
        u.delete(user.get());
    }
}
