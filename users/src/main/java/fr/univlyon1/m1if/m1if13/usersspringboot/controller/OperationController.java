package fr.univlyon1.m1if.m1if13.usersspringboot.controller;

import java.util.Optional;

import javax.naming.AuthenticationException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import fr.univlyon1.m1if.m1if13.usersspringboot.DAO.UserDao;
import fr.univlyon1.m1if.m1if13.usersspringboot.model.User;

@Controller
public class OperationController {

    @Autowired
    private ApplicationContext ctx;

    /**
     * Procédure de login "simple" d'un utilisateur
     * @param login Le login de l'utilisateur. L'utilisateur doit avoir été créé préalablement et son login doit être présent dans le DAO.
     * @param password Le password à vérifier.
     * @return Une ResponseEntity avec le JWT dans le header "Authentication" si le login s'est bien passé, et le code de statut approprié (204, 401 ou 404).
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestParam("login") String login,
    @RequestParam("password") String password,
    @RequestHeader("Origin") String origin) {
        UserDao dao = ctx.getBean(UserDao.class);
        
        Optional<User> user = dao.get(login);
        
        if (user.isPresent()) {
            User u = user.get();
            if (u.isConnected()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);    
            } else {
                try {
                    u.authenticate(password);
                } catch(AuthenticationException e) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                HttpHeaders headers = new HttpHeaders();
                try {
                    Algorithm algorithm = Algorithm.HMAC256("secret");
                    String token = JWT.create()
                        .withIssuer("auth0")
                        .withClaim("login", login)
                        .withClaim("origin", origin)
                        .sign(algorithm);
                    headers.add("Authentication", token);
                } catch (JWTCreationException exception){
                    //Invalid Signing configuration / Couldn't convert Claims.
                }
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Réalise la déconnexion
     */
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authentication") String token) {
        UserDao dao = ctx.getBean(UserDao.class);

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            String claimlogin = jwt.getClaim("login").asString();

            Optional<User> user = dao.get(claimlogin);
            if (user.isPresent()) {
                user.get().disconnect();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    
        } catch (JWTVerificationException exception){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * Méthode destinée au serveur Node pour valider l'authentification d'un utilisateur.
     * @param token Le token JWT qui se trouve dans le header "Authentication" de la requête
     * @param origin L'origine de la requête (pour la comparer avec celle du client, stockée dans le token JWT)
     * @return Une réponse vide avec un code de statut approprié (204, 400, 401).
     */
    @GetMapping("/authenticate")
    public ResponseEntity<Void> authenticate(@RequestHeader("Authentication") String token, @RequestHeader("Origin") String origin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            String claimorigin = jwt.getClaim("origin").asString();
            String claimlogin = jwt.getClaim("login").asString();

            System.out.println(claimlogin + " " + claimorigin);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authentication", token);
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        } catch (JWTVerificationException exception){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}