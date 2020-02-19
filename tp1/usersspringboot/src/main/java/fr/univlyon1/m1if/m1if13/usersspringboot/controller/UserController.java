package fr.univlyon1.m1if.m1if13.usersspringboot.controller;

import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    
    private final UserDao u = UserDao.createInstance();
    
    /**
     * GET request
     * produces HTML
     * @param id
     * @param model
     * @return 
     */
    @GetMapping(path="/users/{login}",produces=MediaType.TEXT_HTML_VALUE)
    public String getOne(@PathVariable(value = "login",required = false) String id, Model model) {
        if (id==null || id.isEmpty()){
            model.addAttribute("list", new ArrayList<String>(u.getAll()) );
            return "users";
        }else {
            model.addAttribute("user", u.get(id).get());
            return "user";
        }
    }
}
