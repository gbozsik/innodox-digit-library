package com.innodox.library.controller;

import com.innodox.library.entity.Book;
import com.innodox.library.entity.User;
import com.innodox.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RequestMapping(value = "${api.base.path}")
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
        logger.info("ActualUser: " + SecurityContextHolder.getContext().getAuthentication().getName());
        SecurityContextHolder.clearContext();
        logger.info("ActualUser: " + userService.getActualUser().toString());
        return new ResponseEntity<>("Logged out", HttpStatus.OK);
    }

    @RequestMapping(value = "/getactualuser", method = RequestMethod.GET)
    public ResponseEntity<User> getActualUser() {
        logger.info("CONTROLLER");
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
        User actualUser = userService.getActualUser();

        return new ResponseEntity<>(actualUser, HttpStatus.OK);
    }


}
