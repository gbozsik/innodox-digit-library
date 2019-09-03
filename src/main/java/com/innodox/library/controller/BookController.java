package com.innodox.library.controller;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.Book;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/api")
@RestController
public class BookController {

    private static final Logger logger = Logger.getLogger( BookController.class.getName() );

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<BookModel>> getBooks(){
        List<BookModel> bookModels = bookService.getBooks();
        return new ResponseEntity<>(bookModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/book")
    public ResponseEntity<BookModel> saveBooks(@RequestBody BookModel bookModel) throws Exception {
        BookModel newBookModel = bookService.saveBook(bookModel);
        return new ResponseEntity<>(newBookModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<BookModel> deleteBooks(@PathVariable("bookId") Long bookId) throws Exception {
        BookModel newBookModel = bookService.deleteBook(bookId);
        return new ResponseEntity<>(newBookModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/rent/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<UserModel> rentBook(@PathVariable("bookId") Long bookId) throws Exception {
        UserModel userModel = bookService.rentBook(bookId);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/bringback/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<UserModel> bringBackBook(@PathVariable("bookId") Long bookId) throws Exception {
        UserModel userModel = bookService.bringBackBook(bookId);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
}
