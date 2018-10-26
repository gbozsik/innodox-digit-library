package com.innodox.library.service;

import com.innodox.library.entity.Book;

import java.util.List;

public interface BookService {

    Book saveBook(Book book) throws Exception;

    List<Book> rentBook(Book book) throws Exception;

    List<Book> bringBackBook(Book book) throws Exception;
}
