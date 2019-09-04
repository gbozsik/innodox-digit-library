package com.innodox.library.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class AuthorTest {

    Author author;

    @Before
    public void setUp() {
        author = new Author();
    }

    @Test
    public void getauthor() {
        Long idValue = 421L;
        String firstNameValue = "Kal";
        String lastNameValue = "El";
        int ageValue = 33;
        Book book1 = new Book();
        Book book2 = new Book();
        List<Book> bookList = Arrays.asList(book1, book2);
        author.setId(idValue);
        author.setFirstName(firstNameValue);
        author.setLastName(lastNameValue);
        author.setAge(ageValue);
        author.setBooks(bookList);
        assertEquals(idValue, author.getId());
        assertEquals(firstNameValue, author.getFirstName());
        assertEquals(lastNameValue, author.getLastName());
        assertEquals(Optional.of(ageValue), Optional.of(author.getAge()));
        assertEquals("Has two element", author.getBooks().size(), 2);
    }
}
