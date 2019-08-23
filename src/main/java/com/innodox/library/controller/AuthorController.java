package com.innodox.library.controller;

import com.innodox.library.dataobject.AuthorModel;
import com.innodox.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/api")
@RestController
public class AuthorController {

    private static final Logger logger = Logger.getLogger( AuthorController.class.getName());

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ResponseEntity<List<AuthorModel>> getAuthors() {
        List<AuthorModel> authorModelList = authorService.getAuthors();
        logger.info(authorModelList.toString());
        return new ResponseEntity<>(authorModelList, HttpStatus.OK);
    }
}
