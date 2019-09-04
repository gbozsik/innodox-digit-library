package com.innodox.library.entity;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserTest {

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void getUsername() {
        String usernameValue = "username";
        user.setUsername(usernameValue);
        assertEquals(usernameValue, user.getUsername());
    }

    @Test
    public void getEmail() {
        String emailValue = "email";
        user.setEmail(emailValue);
        assertEquals(emailValue, user.getEmail());
    }

    @Test
    public void getFirstName() {
        String firstNameValue = "firstname";
        user.setFirstName(firstNameValue);
        assertEquals(firstNameValue, user.getFirstName());
    }

    @Test
    public void setRoles() {
        String[] roles = new String[] {"USER", "ADMIN"};
        user.setRoles(roles);
        assertEquals(user.getRoles().length, 2);
    }

    @Test
    public void getLastName() {
        String lastNameValue = "lastname";
        user.setLastName(lastNameValue);
        assertEquals(lastNameValue, user.getLastName());
    }

    @Test
    public void getPassword() {
        Password password = new Password();
        password.setId(12L);
        user.setPassword(password);
        assertEquals(Optional.of(12L), Optional.of(user.getPassword().getId()));
    }

    @Test
    public void getBookList() {
        Book book = new Book();
        Book book2 = new Book();
        List<Book> bookList = Arrays.asList(book, book2);
        user.setBookList(bookList);
        assertEquals(2, user.getBookList().size());
    }
}