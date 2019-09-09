package com.innodox.library.mapper;

import com.innodox.library.dataobject.AuthorModel;
import com.innodox.library.entity.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.Assert.*;

//@ContextConfiguration(classes = AuthorMapperTest.SpringTestConfig.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorMapperTest {

//    @Configuration
//    @ComponentScan(basePackageClasses = AuthorMapperTest.class)
//    public static class SpringTestConfig {
//    }
//    @Autowired
//    private AuthorMapper authorMapper;
    private AuthorMapper authorMapper;

    @Before
    public void setup() {
        authorMapper = Mappers.getMapper(AuthorMapper.class); // Initialization of the mapper
    }

    @Test
    public void authorModelToAuthor() {

    }

    @Test
    public void authorToAuthorModel() {
        Author author = new Author();
        author.setAge(10);

        AuthorModel authorModelReturned = authorMapper.authorToAuthorModel(author);
        assertEquals(Optional.ofNullable(authorModelReturned.getAge()), Optional.of(10));
    }
}