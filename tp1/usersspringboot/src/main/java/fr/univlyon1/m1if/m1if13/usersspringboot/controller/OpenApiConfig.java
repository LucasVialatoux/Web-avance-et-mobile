package fr.univlyon1.m1if.m1if13.usersspringboot.controller;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
            info = @Info(
                    title = "User API [M1IF13]",
                    version = "0.0",
                    description = "University project",
                    contact = @Contact(url = "https://forge.univ-lyon1.fr/p1600354/m1if13-2020", name = "Paul & Lucas project")
            ),
            security = {
                    @SecurityRequirement(name = "req 1", scopes = {"a", "b"}),
                    @SecurityRequirement(name = "req 2", scopes = {"b", "c"})
            },
            servers = {
                    @Server(
                            description = "API",
                            url = "http://192.168.75.38:8080"
                            )
            }
    )
public class OpenApiConfig {
    
}
