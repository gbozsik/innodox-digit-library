package com.innodox.library.core;


import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import com.innodox.library.entity.Password;
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


        Category category1 = new Category("Romantikus");
        Category category2 = new Category("Sci-fi");
        Category category3 = new Category("Thriller");
        Category category4 = new Category("Novella");
        List<Category> categories = Arrays.asList(
                category1, category2, category3, category4
        );
        categoryRepo.saveAll(categories);

        Book book1 = new Book("Bengjáli tűz", "Libry", "G. Hajnóczy Rózsa", "„Minden hazugság egy párhuzamos világot teremt. Egy világot, ahol az a hazugság igaz.”\n" +
                "(Momus)", "TÖRTÉNETÜNK kezdetén egy srác ül az ágyon, és a telefonja kijelzője fölé görnyed a sötétben.\n" +
                "Riccardo éppen gépel… Azt mondta, talán a barátnője is jön.\n" +
                "Anna éppen gépel… te jó ég remélem nem\n" +
                "Anna éppen gépel... szebben énekel, és szexibb\n" +
                "Riccardo éppen gépel... 1", 7, category1);
        Book book2 = new Book("A fekete város", "Novum", "Mikszáth Kálmán", "„Minden hazugság egy párhuzamos világot teremt. Egy világot, ahol az a hazugság igaz.”\\n\" +\n" +
                "                \"(Momus) 2", "TÖRTÉNETÜNK kezdetén egy srác ül az ágyon, és a telefonja kijelzője fölé görnyed a sötétben.\n" +
                "Riccardo éppen gépel… Azt mondta, talán a barátnője is jön.\n" +
                "Anna éppen gépel… te jó ég remélem nem\n" +
                "Anna éppen gépel… nem nagyon csípem\n" +
                "Riccardo éppen gépel… Ne mondd!\n" +
                "Riccardo éppen gépel... És ne feledd, Maria De Filippi egyszer azt mondta neki, hogy ő szebben énekel.\n" +
                "Anna éppen gépel... szebben énekel, és szexibb\n" +
                "Riccardo éppen gépel... 2", 4, category2);
        Book book3 = new Book("A feleségem története", "My-book", "Füst Milán", " „Minden hazugság egy párhuzamos világot teremt. Egy világot, ahol az a hazugság igaz.”\\n\" +\n" +
                "                \"(Momus) 3", "TÖRTÉNETÜNK kezdetén egy srác ül az ágyon, és a telefonja kijelzője fölé görnyed a sötétben.\n" +
                "Riccardo éppen gépel… Azt mondta, talán a barátnője is jön.\n" +
                "Anna éppen gépel… te jó ég remélem nem\n" +
                "Anna éppen gépel… nem nagyon csípem\n" +
                "Anna éppen gépel... szebben énekel, és szexibb\n" +
                "Riccardo éppen gépel... 3", 7, category3);
        Book book4 = new Book("A gólyakalifa", "Móra", "Babits Mihály", "„Minden hazugság egy párhuzamos világot teremt. Egy világot, ahol az a hazugság igaz.”\\n\" +\n" +
                "                \"(Momus) 4", "TÖRTÉNETÜNK kezdetén egy srác ül az ágyon, és a telefonja kijelzője fölé görnyed a sötétben.\n" +
                "Riccardo éppen gépel… Azt mondta, talán a barátnője is jön.\n" +
                "Anna éppen gépel… te jó ég remélem nem\n" +
                "Anna éppen gépel… nem nagyon csípem\n" +
                "Riccardo éppen gépel... És ne feledd, Maria De Filippi egyszer azt mondta neki, hogy ő szebben énekel.\n" +
                "Anna éppen gépel... szebben énekel, és szexibb\n" +
                "Riccardo éppen gépel... 4", 9, category4);
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
                new User("skovi", "sanyi.kovacs@gmail.com","Sanyi", "kovács", new Password("pas"), bookList1, new String[]{"ROLE_ADMIN", "ROLE_USER"}),
                new User("sferi", "feri.sos@t-online.com", "Feri", "sós", new Password("pas"), bookList2, new String[]{"ROLE_USER"}),
                new User("ktomi", "tamas.kis@gmail.com", "Tomi", "kis", new Password("pas"), bookList3, new String[]{"ROLE_USER"})
                );
        userRepo.saveAll(users);
    }
}
