package com.oskarro.empik.controller;

import com.oskarro.empik.gateway.GithubGateway;
import com.oskarro.empik.service.RequestService;
import com.oskarro.empik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public RequestService requestService;

    @Autowired
    public EmpikController(GithubGateway gateway, UserService userService, RequestService requestService) {
        this.gateway = gateway;
        this.userService = userService;
        this.requestService = requestService;
    }

    @RequestMapping(value = "users/{login}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserDataFromGithub(@PathVariable String login) {
        requestService.upsertRequest(login);
        return ResponseEntity.ok().body(userService.getUser(login));
    }
}
