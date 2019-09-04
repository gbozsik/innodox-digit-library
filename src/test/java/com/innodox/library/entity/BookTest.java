package com.innodox.library.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class BookTest {

    Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book();
    }

    @Test
    public void getTitle() {
        String titleValue = "title";
        book.setTitle(titleValue);
        assertEquals(titleValue, book.getTitle());
    }

    @Test
    public void getPublisher() {
        String publisherValue = "publisher";
        book.setPublisher(publisherValue);
        assertEquals(publisherValue, book.getPublisher());
    }

    @Test
    public void getPreface() {
        String prefaceValue = "prefaceValue";
        book.setPreface(prefaceValue);
        assertEquals(prefaceValue, book.getPreface());
    }

    @Test
    public void getContent() {
        String contentValue = "content";
        book.setContent(contentValue);
        assertEquals(contentValue, book.getContent());
    }

    @Test
    public void getQuantity() {
        int quantityValue = 20;
        book.setQuantity(quantityValue);
        assertEquals(Optional.of(quantityValue), Optional.of(book.getQuantity()));
    }

    @Test
    public void getAuthor() {
        Author authorValue = new Author();
        authorValue.setId(12L);
        book.setAuthor(authorValue);
        assertEquals(Optional.of(12L), Optional.of(authorValue.getId()));
    }

    @Test
    public void getCategory() {
        Category category = new Category();
        category.setId(23L);
        book.setCategory(category);
        assertEquals(Optional.of(23L), Optional.of(book.getCategory().getId()));
    }
}