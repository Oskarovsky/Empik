package com.oskarro.empik.controller;

import com.oskarro.empik.gateway.GithubGateway;
import com.oskarro.empik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller for handling requests
 *
 * */

@Controller
@RestController
public class EmpikController {

    public GithubGateway gateway;
    public UserService userService;

    @Autowired
    public EmpikController(GithubGateway gateway, UserService userService) {
        this.gateway = gateway;
        this.userService = userService;
    }

    @RequestMapping(value = "users/{login}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserDataFromGithub(@PathVariable String login) {
        Mono<String> message = gateway.getUserDataFromGithub(login);
        return ResponseEntity.ok().body(userService.getUser(login));
    }
}
