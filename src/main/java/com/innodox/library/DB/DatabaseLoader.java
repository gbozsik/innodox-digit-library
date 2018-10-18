package com.innodox.library.DB;


import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import com.innodox.library.entity.User;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.repo.CategoryRepo;
import com.innodox.library.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseLoader implements ApplicationRunner {

    private UserRepo userRepo;
    private BookRepo bookRepo;
    private CategoryRepo categoryRepo;

    @Autowired
    public DatabaseLoader(UserRepo userRepo, BookRepo bookRepo, CategoryRepo categoryRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


        Category category1 = new Category("Romantic");
        Category category2 = new Category("Sci-fi");
        Category category3 = new Category("Thriller");
        Category category4 = new Category("Novel");
        List<Category> categories = Arrays.asList(
                category1, category2, category3, category4
        );
        categoryRepo.saveAll(categories);

        Book book1 = new Book("Book title 1", "Libry", "Author1", "preface 1", "content 1", 10, category1);
        Book book2 = new Book("Book title 2", "Novum", "Author1", "preface 2", "content 2", 20, category2);
        Book book3 = new Book("Book title 3", "My-book", "Author1", " prefce 3", "content 3", 30, category3);
        Book book4 = new Book("Book title 4", "Móra", "Author1", "preface 4", "content 4", 40, category4);
        List<Book> books = Arrays.asList(
                book1, book2, book3, book4
        );
        bookRepo.saveAll(books);

        List<Book> bookList1 = Arrays.asList(
                book1, book2, book3
        );

        List<Book> bookList2 = Arrays.asList(
                book4, book2, book3
        );

        List<Book> bookList3 = Arrays.asList(
                book1, book2, book4
        );

        List<User> users = Arrays.asList(
                new User("skovi", "sanyi", "kovács", "pas", bookList1),
                new User("sferi", "feri", "sós", "pas", bookList2),
                new User("ktomi", "tomi", "kis", "pas", bookList3)
                );
        userRepo.saveAll(users);
    }
}
