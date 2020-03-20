package fr.univlyon1.m1if.m1if13.usersspringboot.controller;

import fr.univlyon1.m1if.m1if13.usersspringboot.model.User;
import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.ArrayList;
import java.util.Optional;
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

import io.swagger.v3.oas.annotations.Parameter;

/**
 *
 * UserRestController
 G�re les requ�tes suivantes : GET ; PUT ; POST ; DELETE;
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
    @Operation(
            summary = "Get one User by login",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "ok",
                        content = {
                            @Content(mediaType="application/xml",
                                    schema = @Schema(implementation = User.class)),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                        }
                ),
                @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                @ApiResponse(
                            responseCode = "406",
                            description = "Not acceptable",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    ),
                @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    @GetMapping(path="/user/{login}"
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public User getNormal(@Parameter(description="User login", required = true) @PathVariable String login){
        return u.get(login).get();
    }
    
    /**
    * GET request
     * produces JSON | XML
     * @return
    */
    @Operation(
            summary = "Get user list",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "ok",
                        content = {
                            @Content(mediaType="application/xml",
                                    schema = @Schema(implementation = User.class)),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                        }
                )
            }
    )
    @GetMapping(path="/users"
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
    @Operation(
            summary = "Create new user",
            responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "created",
                        content = {
                            @Content(mediaType="application/xml",
                                    schema = @Schema(implementation = User.class)),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                        }
                ),
                @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    @PostMapping(path="/users"
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
    @Operation(
            summary = "Create new user",
            responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "created",
                        content = {
                            @Content(mediaType="application/xml",
                                    schema = @Schema(implementation = User.class)),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                        }
                ),
                @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    @PostMapping(path="/users")
    public ResponseEntity<Void> postNormal(@Parameter(description = "User login", required = true) @RequestParam(value = "login") String login,
            @Parameter(description = "User password", required = true) @RequestParam(value = "password") String password){
        User user = new User(login,password);
        u.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
    * PUT request
     * consumes JSON | XML
     * @param json
     * @return 
     * @throws org.json.JSONException 
    */
    @Operation(
            summary = "Create or update user",
            responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "created",
                        content = {
                            @Content(mediaType="application/xml",
                                    schema = @Schema(implementation = User.class)),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                        }
                ),
                @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    @PutMapping(path="/user/{login}"
            ,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> put(@Parameter(description = "User actual login", required = true) @PathVariable String login, 
                @RequestBody String json) throws JSONException{
        JSONObject jsonString = new JSONObject(json);
        String newLogin = jsonString.getString("login");
        String newPassword = jsonString.getString("password");
        if (u.get(login).isPresent()) {
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
    * PUT request
     * consumes URL-encoded 
     * produces JSON
     * @param login
     * @param newLogin
     * @param newPassword
     * @return 
    */
    @Operation(
            summary = "Create or update user",
            responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "created",
                        content = {
                            @Content(mediaType="x-www-form-url-encoded",
                                    schema = @Schema(implementation = User.class))
                        }
                ),
                @ApiResponse(
                        responseCode = "200",
                        description = "ok",
                        content = {
                            @Content(mediaType="x-www-form-url-encoded",
                                    schema = @Schema(implementation = User.class))
                        }
                )
            }
    )
    @PutMapping(path="/user/{login}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> putNormal(@Parameter(description = "User actual login", required = true) @PathVariable String login,
            @Parameter(description = "User new login", required = true) @RequestParam (value="login") String newLogin
            ,@Parameter(description = "User new password", required = true) @RequestParam (value="password") String newPassword){
        if (u.get(login).isPresent()){
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
    @Operation(
            summary = "Delete user",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "ok",
                        content = {
                            @Content(mediaType="application/xml",
                                    schema = @Schema(implementation = User.class)),
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                        }
                ),
                @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    @DeleteMapping(path="/user/{login}")
    public ResponseEntity<Void> deleteNormal(@Parameter(description = "User login", required = true) @PathVariable(value = "login") String login){
        Optional<User> user = u.get(login);
        if (user.isPresent()){
            u.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
