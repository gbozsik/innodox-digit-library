package com.innodox.library.repo;

import com.innodox.library.entity.Author;
import com.innodox.library.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {


    List<Book> findAll();

    List<Book> findAllByTitle(String title);

    Book findByAuthor(Author author);

    Book findById(long id);

    @Query(value = "SELECT * FROM users_books WHERE user_id = ?1", nativeQuery = true)
    List<Object[]> getBooksOfUser(Long userId);
}
