package com.innodox.library.controller;

import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import com.innodox.library.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/api")
@RestController
public class CategoriController {

    private static final Logger logger = Logger.getLogger( BookController.class.getName() );

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoriController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = categoryRepo.findAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }
}
