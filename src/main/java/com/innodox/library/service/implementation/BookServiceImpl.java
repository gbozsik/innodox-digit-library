package com.innodox.library.service.implementation;

import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.repo.CategoryRepo;
import com.innodox.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private static final Logger logger = Logger.getLogger( BookServiceImpl.class.getName() );

    private BookRepo bookRepo;
    private CategoryRepo categoryRepo;


    @Autowired
    public BookServiceImpl(BookRepo bookRepo, CategoryRepo categoryRepo) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
    }


    @Override
    public Book saveBook(Book book) {
        if (!Objects.isNull(book.getId()))
            throw new EntityNotFoundException("Id already exists");

        Book checkBook = bookRepo.findByTitle(book.getTitle());
        if (checkBook != null) {
            if (!Objects.isNull(bookRepo.findByAuthor(book.getAuthor()))) {
                throw new EntityNotFoundException("This book already exists");
            }
        }

        Category categoryfromDb = categoryRepo.findById(book.getCategory().getId()).orElse(null);
        if (Objects.isNull(categoryfromDb))
            throw new EntityNotFoundException("Category not found");
        book.setCategory(categoryfromDb);

        Book savedBook = bookRepo.save(book);

        return savedBook;
    }
}
