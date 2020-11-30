package com.innodox.library.controller;

import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.User;
import com.innodox.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RequestMapping("/api")
@Component
@RestController
public class LoginContorller {

    private static final Logger logger = Logger.getLogger(LoginContorller.class.getName());

    private UserService userService;

    @Autowired
    public LoginContorller(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/loggingout", method = RequestMethod.GET)
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logged out", HttpStatus.OK);
    }

    @RequestMapping(value = "/getactualuser", method = RequestMethod.GET)
    public ResponseEntity<UserModel> getActualUser() {
         UserModel actualUser = userService.getActualUser();
        return new ResponseEntity<>(actualUser, HttpStatus.OK);
    }

    @GetMapping("/ping")
    public String getPing() {
        return "OK";
    }


}
