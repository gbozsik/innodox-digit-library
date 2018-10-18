package com.innodox.library.controller;

import com.innodox.library.entity.Book;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@Component
@RestController
public class BookController {

    private static final Logger logger = Logger.getLogger( BookController.class.getName() );

    private BookRepo bookRepo;
    private BookService bookService;

    @Autowired
    public BookController(BookRepo bookRepo, BookService bookService) {
        this.bookRepo = bookRepo;
        this.bookService = bookService;
    }


    @RequestMapping(value = "/getbooks", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookRepo.findAll();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @RequestMapping(value = "/savebooks")
    public ResponseEntity<Book> saveBooks(@RequestBody Book book){
        logger.info("CONTROLLER");
        Book newBook = bookService.saveBook(book);

        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }


}
