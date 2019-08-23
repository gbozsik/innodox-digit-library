package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.AuthorModel;
import com.innodox.library.entity.Author;
import com.innodox.library.mapper.AuthorMapper;
import com.innodox.library.repo.AuthorRepo;
import com.innodox.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepo authorRepo;
    private AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepo authorRepo, AuthorMapper authorMapper) {
        this.authorRepo = authorRepo;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorModel> getAuthors() {
        List<Author> authorList = authorRepo.findAll();
        return authorList.stream()
                .map(author -> authorMapper.authorToAuthorModel(author))
                .collect(Collectors.toList());
    }
}
