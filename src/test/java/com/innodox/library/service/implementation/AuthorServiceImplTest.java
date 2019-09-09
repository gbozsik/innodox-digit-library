package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.AuthorModel;
import com.innodox.library.entity.Author;
import com.innodox.library.mapper.AuthorMapper;
import com.innodox.library.repo.AuthorRepo;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorServiceImplTest {

    private AuthorServiceImpl authorService;

    private AuthorMapper authorMapper;

    @Mock
    AuthorRepo authorRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authorMapper = Mappers.getMapper(AuthorMapper.class);
        authorService = new AuthorServiceImpl(authorRepo, authorMapper);
    }

    @Test
    public void getAuthors() {
        Author author = new Author();
        author.setAge(10);
        List<Author> authorList = new ArrayList<>();
        authorList.add(author);

        when(authorRepo.findAll()).thenReturn(authorList);
        List<AuthorModel> authorListReturned = authorService.getAuthors();
        assertEquals("Has one element", authorListReturned.size(), 1);
        assertEquals(authorListReturned.get(0).getAge(), author.getAge());
        verify(authorRepo, times(1)).findAll();
    }
}