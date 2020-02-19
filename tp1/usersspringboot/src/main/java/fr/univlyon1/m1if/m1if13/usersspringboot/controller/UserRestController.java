package fr.univlyon1.m1if.m1if13.usersspringboot.controller;

import fr.univlyon1.m1if.m1if13.usersspringboot.model.User;
import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * produces JSON | XML
     * @param login
     * @return
    */
    @GetMapping(value="/user/{login}"
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public User getNormal(@PathVariable String login){
        return u.get(login).get();
    }
    
    /**
    * GET request
     * produces JSON | XML
     * @return
    */
    @GetMapping(value="/users"
    ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ArrayList<String> getNormal(){
        return new ArrayList<>(u.getAll());
    }
    
    /**
    * POST request
     * consumes JSON | XML
     * @param json
     * @return 
     * @throws org.json.JSONException 
    */
    @PostMapping(value="/users"
            ,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> post(@RequestBody String json) throws JSONException{
        JSONObject jsonString = new JSONObject(json);
        User user = new User(jsonString.getString("login"),jsonString.getString("password"));
        u.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    /**
    * POST request
     * @param login
     * @param password
     * @return 
    */
    @PostMapping(value="/users")
    public ResponseEntity<Void> postNormal(@RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password){
        User user = new User(login,password);
        u.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    /**
    * PUT request
     * consumes URL-encoded 
     * produces JSON
     * @param login
     * @param newLogin
     * @param newPassword
     * @return 
    */
    @PutMapping(value="/user/{login}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> putNormal(@PathVariable String login,
            @RequestParam (value="login") String newLogin
            ,@RequestParam (value="password") String newPassword){
        System.out.println(u.get(login));
        if (!u.get(login).isEmpty()){
            User toChange = u.get(login).get();
            String[] params={newLogin,newPassword};
            u.update(toChange,params);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            User user = new User(newLogin,newPassword);
            u.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    
    /**
    * DELETE request
     * @param login
     * @return 
    */
    @DeleteMapping(value="/user/{login}")
    public ResponseEntity<Void> deleteNormal(@PathVariable(value = "login") String login){
        Optional<User> user = u.get(login);
        if (user.isPresent()){
            u.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
