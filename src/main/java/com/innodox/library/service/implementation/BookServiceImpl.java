package com.innodox.library.service.implementation;

import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import com.innodox.library.entity.User;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.repo.CategoryRepo;
import com.innodox.library.repo.UserRepo;
import com.innodox.library.service.BookService;
import com.innodox.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private static final Logger logger = Logger.getLogger(BookServiceImpl.class.getName());

    private BookRepo bookRepo;
    private CategoryRepo categoryRepo;
    private UserService userService;
    private UserRepo userRepo;


    @Autowired
    public BookServiceImpl(BookRepo bookRepo, CategoryRepo categoryRepo, UserService userService, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
        this.userService = userService;
        this.userRepo = userRepo;
    }


    @Override                                                   //Menti a felvett könyvet
    public Book saveBook(Book book) throws Exception {
        if (!Objects.isNull(book.getId()))
            throw new EntityNotFoundException("Már van ilyen id-vel könyv a listában");

        /**
         * Lent a mezők kitöltését validálja, hogy pontosan vissza tudja adni, hogy melyi mező nincs töltve
         *
         * és  Validator-t még nem használtam
         */
        if (((Objects.isNull(book.getTitle()) || book.getTitle().equals("")))){
            throw new EntityNotFoundException("Cím nem lett kitöltve");
        }
        if ((Objects.isNull(book.getAuthor())) || (book.getAuthor().equals(""))){
            throw new EntityNotFoundException("Szerző nem lett kitöltve");
        }
        if ((Objects.isNull(book.getPublisher())) || (book.getPublisher().equals(""))){
            throw new EntityNotFoundException("Kiadó nem lett kitöltve");
        }
        if ((Objects.isNull(book.getCategory())) || (book.getCategory().equals(""))){
            throw new EntityNotFoundException("Kategória nem lett kitöltve");
        }
        if ((Objects.isNull(book.getQuantity())) || (book.getQuantity().equals(""))){
            throw new EntityNotFoundException("Darabszám nem lett kitöltve");
        }
        if ((Objects.isNull(book.getPreface())) || (book.getPreface().equals(""))){
            throw new EntityNotFoundException("Előszó nem lett kitöltve");
        }
        if ((Objects.isNull(book.getContent())) || (book.getContent().equals(""))){
            throw new EntityNotFoundException("Könyv tartalma nem lett kitöltve");
        }

        /****************Max darabszám validálása***********************/
        if ((book.getQuantity() > 10) && (book.getQuantity() != null))
            throw new Exception("Tíznél több könyvet nem lehet felvételezni");
        if (book.getQuantity() < 1)
            throw new Exception("1 és 10 közötti mennyiségű könyvet lehet felvételezni");
        /**************************************************************************************************************/

        List<Book> checkBook = bookRepo.findAllByTitle(book.getTitle());
        if (!checkBook.isEmpty() && (!Objects.isNull(bookRepo.findByAuthor(book.getAuthor())))) {
            checkBook.clear();
            throw new EntityNotFoundException("Ez a könyv már szerepel a listában");
        }

        Category categoryfromDb = categoryRepo.findById(book.getCategory().getId()).orElse(null);
        if (Objects.isNull(categoryfromDb))
            throw new EntityNotFoundException("Kategória nem található");
        book.setCategory(categoryfromDb);

        checkBook.clear();
        Book savedBook = bookRepo.save(book);

        return savedBook;
    }

    @Override                                               //Kölcsönzés és elvesz egyet a könyv darabszámából
    public List<Book> rentBook(Book book) throws Exception {
        if (Objects.isNull(book.getId()))
            throw new EntityNotFoundException("Nem találtam Id-t");

        Book bookFromDb = bookRepo.findById(book.getId()).orElse(null);
        if (bookFromDb.getQuantity() < 1)
            throw new Exception("Jelenleg nincs egy darab se a könyvtárban ebből a könyvből");
        User actualUser = userService.getActualUser();
        List<Book> usersBooks = actualUser.getBookList();
        for (Book book1 : usersBooks) {
            if (book1.getTitle().equals(book.getTitle()) && book1.getAuthor().equals(book.getAuthor())) {
                throw new Exception("Jelenleg kölcsönzi már ezt a könyvet");
            }
        }
        bookFromDb.setQuantity(bookFromDb.getQuantity() - 1);
        usersBooks.add(bookFromDb);
        actualUser.setBookList(usersBooks);
        bookRepo.save(bookFromDb);
        userRepo.save(actualUser);

        return bookRepo.findAll();
    }

    @Override                               //könyv visszavétel
    public List<Book> bringBackBook(Book book) throws Exception {
        if (Objects.isNull(book.getId()))
            throw new EntityNotFoundException("Nem találtam Id-t");
        Book bookFromDb = bookRepo.findById(book.getId()).orElse(null);
        User actualUser = userService.getActualUser();
        List<Book> usersBooks = actualUser.getBookList();
            boolean hasBook = false;
        for (Book book1 : usersBooks) {
            if ((book1.getTitle().equals(book.getTitle()) && book1.getAuthor().equals(book.getAuthor()))) {
                hasBook = true;
            }
        }
            if (hasBook == false)
                throw new Exception("Ez a könyv nincs kikölcsönözve");
        bookFromDb.setQuantity(bookFromDb.getQuantity() + 1);
        usersBooks.remove(bookFromDb);
        actualUser.setBookList(usersBooks);

        bookRepo.save(bookFromDb);
        userRepo.save(actualUser);
        return bookRepo.findAll();
    }
}
