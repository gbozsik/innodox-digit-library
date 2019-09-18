package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.Author;
import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import com.innodox.library.entity.User;
import com.innodox.library.mapper.BookMapper;
import com.innodox.library.mapper.UserMapper;
import com.innodox.library.repo.AuthorRepo;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.repo.CategoryRepo;
import com.innodox.library.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    private BookServiceImpl bookServiceImpl;

    BookMapper bookMapper;

    UserMapper userMapper;

    @Mock
    BookRepo bookRepo;
    @Mock
    AuthorRepo authorRepo;
    @Mock
    UserRepo userRepo;
    @Mock
    CategoryRepo categoryRepo;
//    @Mock
//    UserServiceImpl userServiceImpl;
    @Mock
    SecurityContext securityContext;
    @Mock
    Authentication authentication;

    private Book book;
    private Book bookToRent;
    private Category category;
    private Author author;
    private List<Book> bookList;
    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookMapper = Mappers.getMapper(BookMapper.class);
        userMapper = Mappers.getMapper(UserMapper.class);
        securityContext = Mockito.mock(SecurityContext.class);
        authentication = Mockito.mock(Authentication.class);
        bookServiceImpl = new BookServiceImpl(bookRepo, authorRepo, userRepo, categoryRepo, bookMapper, userMapper, new UserServiceImpl(userRepo, userMapper, bookMapper));

        book = new Book();
        bookToRent = new Book();
        category = new Category();
        category.setId(1L);
        category.setName("categoryName");
        author = new Author();
        author.setAge(30);
        author.setBooks(new ArrayList<>());
        book.setId(1L);
        book.setCategory(category);
        book.setAuthor(author);
        book.setQuantity(3);
        bookToRent.setId(3L);
        bookToRent.setQuantity(4);
        bookList = new ArrayList<>();
        bookList.add(book);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        user = new User();
        user.setUsername("sanyi");
        user.setBookList(bookList);
    }

    @Test
    public void getBooks() {
        when(bookRepo.findAll()).thenReturn(bookList);
        when(authorRepo.save(any())).thenReturn(author);
        List<BookModel> bookModelListreturned = bookServiceImpl.getBooks();
        assertEquals(bookModelListreturned.size(), 1);
        assertEquals(bookModelListreturned.get(0).getAuthorModel().getAge(), author.getAge());
        assertEquals(bookModelListreturned.get(0).getCategoryModel().getName(), category.getName());
        verify(bookRepo, times(1)).findAll();
    }

    @Test
    public void saveBook() throws Exception {
        when(bookRepo.save(any())).thenReturn(book);
        BookModel savedBookModel = bookServiceImpl.saveBook(bookMapper.mapBookToBookModel(book));
        assertEquals(savedBookModel.getCategoryModel().getName(), book.getCategory().getName());
        assertEquals(savedBookModel.getAuthorModel().getAge(), book.getAuthor().getAge());
        verify(bookRepo, times(1)).save(any());
    }

    @Test
    public void rentBook() throws Exception {
        when(bookRepo.findById(any())).thenReturn(Optional.ofNullable(bookToRent));
        when(userRepo.findByUsername(any())).thenReturn(user);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserModel userModelReturned = bookServiceImpl.rentBook(bookToRent.getId());
        assertEquals(userModelReturned.getBookModelList().size(), 2);
        verify(bookRepo, times(1)).findById(any());
        verify(userRepo, times(1)).findByUsername(any());
    }

    @Test
    public void bringBackBook() {
    }

    @Test
    public void deleteBook() {
    }
}