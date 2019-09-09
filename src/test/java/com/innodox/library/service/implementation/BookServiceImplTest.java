package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.entity.Book;
import com.innodox.library.mapper.BookMapper;
import com.innodox.library.mapper.UserMapper;
import com.innodox.library.repo.AuthorRepo;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.repo.CategoryRepo;
import com.innodox.library.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    private BookServiceImpl bookServiceImpl;

    @Mock
    BookRepo bookRepo;
    @Mock
    AuthorRepo authorRepo;
    @Mock
    UserRepo userRepo;
    @Mock
    CategoryRepo categoryRepo;
    @Mock
    BookMapper bookMapper;
    @Mock
    UserMapper userMapper;
    @Mock
    UserServiceImpl userServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookServiceImpl = new BookServiceImpl(bookRepo, authorRepo, userRepo, categoryRepo, bookMapper, userMapper, userServiceImpl);
    }

    @Test
    public void getBooks() {
        Book book = new Book();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        when(bookRepo.findAll()).thenReturn(bookList);
        List<BookModel> bookModelListreturned = bookServiceImpl.getBooks();
        assertEquals(bookModelListreturned.size(), 1);
        verify(bookRepo, times(1)).findAll();
    }

    @Test
    public void saveBook() {
    }

    @Test
    public void rentBook() {
    }

    @Test
    public void bringBackBook() {
    }

    @Test
    public void deleteBook() {
    }
}