package com.innodox.library.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getCategory() {
        Long idValue = 34L;
        String nameValue = "categoryName";
        category.setId(idValue);
        category.setName(nameValue);
        assertEquals(idValue, category.getId());
        assertEquals(nameValue, category.getName());
    }
}