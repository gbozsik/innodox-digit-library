package com.innodox.library.repo;

import com.innodox.library.entity.Author;
import com.innodox.library.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> {
    List<Author> findAll();
}
