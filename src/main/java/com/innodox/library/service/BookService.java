package com.innodox.library.service;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.Book;

import java.util.List;

public interface BookService {

    List<BookModel> getBooks();

    BookModel saveBook(BookModel bookModel) throws Exception;

    UserModel rentBook(Long bookId) throws Exception;

    UserModel bringBackBook(Long bookId) throws Exception;

    BookModel deleteBook(Long bookId);
}
