package com.innodox.library.repo;

import com.innodox.library.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookRepo extends CrudRepository<Book, Long> {


    List<Book> findAll();

    Book findByTitle(String title);

    Book findByAuthor(String author);

    Book findById(long id);

    @Query(value = "SELECT * FROM users_books WHERE user_id = ?1", nativeQuery = true)
    List<Object[]> getBooksOfUser(Long userId);
}
