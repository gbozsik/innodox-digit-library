package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import com.innodox.library.entity.User;
import com.innodox.library.mapper.BookMapper;
import com.innodox.library.mapper.UserMapper;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.repo.CategoryRepo;
import com.innodox.library.repo.UserRepo;
import com.innodox.library.service.BookService;
import com.innodox.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private static final Logger logger = Logger.getLogger(BookServiceImpl.class.getName());

    private BookRepo bookRepo;
    private CategoryRepo categoryRepo;
    private UserService userService;
    private UserRepo userRepo;
    private BookMapper bookMapper;
    private UserMapper userMapper;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo,
                           CategoryRepo categoryRepo,
                           UserService userService,
                           UserRepo userRepo, BookMapper bookMapper, UserMapper userMapper) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
        this.userService = userService;
        this.userRepo = userRepo;
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<BookModel> getBooks() {
        List<Book> bookList = bookRepo.findAll();
        return bookList.stream()
                .map(book -> bookMapper.mapBookToBookModel(book))
                .collect(Collectors.toList());
    }

    @Override
    public BookModel saveBook(BookModel bookModel) throws Exception {
        if (checkTitleAndAuthorMatch(bookModel)) {
            throw new EntityExistsException("This book is already on the list");
        }
        Book newBook = bookMapper.mapBookModelToBook(bookModel);
        Book savedBook = bookRepo.save(newBook);
        return bookMapper.mapBookToBookModel(savedBook);
    }

    private boolean checkTitleAndAuthorMatch(BookModel bookModel) {
        if (!Objects.isNull(bookModel.getId())) {
            return true;
        }
//            throw new EntityExistsException("This book is already on the list");
        List<Book> bookListFromDB = bookRepo.findAllByTitle(bookModel.getTitle());
        for (Book book : bookListFromDB) {
            if (book.getTitle().equals(bookModel.getTitle())
                    && book.getAuthor().equals(bookModel.getAuthorModel().getFirstName())
                    && book.getAuthor().equals(bookModel.getAuthorModel().getLastName())) {
                return true;
//            throw new EntityExistsException("This book is already on the list");
        }
//        if ((!Objects.isNull(bookRepo.findAllByTitle(book.getTitle())))
//                && (!Objects.isNull(bookRepo.findByAuthor(book.getAuthor())))) {
        }
        return false;
    }

    private void validateQuatity(Book book) throws Exception {
        if ((book.getQuantity() > 10) && (book.getQuantity() != null))
            throw new Exception("You can not have more than 10, from a book");
        if (book.getQuantity() < 1)
            throw new Exception("You can enroll between 1 and 10 from a book");
    }

    private void setCategory(Category category) {
//        Category categoryfromDb = categoryRepo.findById(book.getCategory().getId()).orElse(null);
        if (Objects.isNull(category)) {
            throw new EntityNotFoundException("Category not found!");
        }
//        book.setCategory(categoryfromDb);
    }

    @Override
    public UserModel rentBook(Long bookId) throws Exception {
        Book bookFromDB = bookRepo.findById(bookId).orElse(null);
        User actualUser = getUserFromDB();
        List<Book> userBooks = actualUser.getBookList();
        validateBookIsRentable(bookFromDB, userBooks);
        bookFromDB.setQuantity(bookFromDB.getQuantity() - 1);
        userBooks.add(bookFromDB);
        bookRepo.save(bookFromDB);
        userRepo.save(actualUser);
        return userMapper.mapUserToUserModel(actualUser);
    }

    private User getUserFromDB() {
        return userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    private void validateBookIsRentable(Book bookFromDB, List<Book> usersBooks) throws Exception {
        validateBookExists(bookFromDB);
        validateStorageNotEmpty(bookFromDB);
        validateUserRentedThisBook(bookFromDB, usersBooks);
    }

    private void validateBookExists(Book bookFromDB) {
        if (Objects.isNull(bookFromDB)) {
            throw new EntityNotFoundException("Book not found!");
        }
    }

    private void validateUserRentedThisBook(Book bookFromDB, List<Book> usersBooks) throws Exception {
        for (Book book1 : usersBooks) {
            if (book1.getId().equals(bookFromDB.getId())) {
                throw new Exception("You are already renting this book right now!");
            }
        }
    }

    private void validateStorageNotEmpty(Book bookFromDB) throws Exception {
        if (bookFromDB.getQuantity() < 1)
            throw new Exception("All this book has rented at the moment");
    }

    @Override
    public UserModel bringBackBook(Long bookId) throws Exception {
        Book bookFromDB = bookRepo.findById(bookId).orElse(null);
        validateBookExists(bookFromDB);
        User actualUser = getUserFromDB();
        List<Book> usersBooks = actualUser.getBookList();
        validatBookIsEvenRented(bookFromDB, usersBooks);
        bookFromDB.setQuantity(bookFromDB.getQuantity() + 1);
        usersBooks.remove(bookFromDB);
        actualUser.setBookList(usersBooks);
        bookRepo.save(bookFromDB);
        userRepo.save(actualUser);
        return userMapper.mapUserToUserModel(actualUser);
    }

    private void validatBookIsEvenRented(Book bookFromDB, List<Book> usersBooks) throws Exception {
        if (!usersBooks.contains(bookFromDB)) {
            throw new Exception("This book is not even rented");
        }
    }
}
