package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.Author;
import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import com.innodox.library.entity.User;
import com.innodox.library.mapper.BookMapper;
import com.innodox.library.mapper.UserMapper;
import com.innodox.library.repo.AuthorRepo;
import com.innodox.library.repo.BookRepo;
import com.innodox.library.repo.CategoryRepo;
import com.innodox.library.repo.UserRepo;
import com.innodox.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private static final Logger logger = Logger.getLogger(BookServiceImpl.class.getName());

    private BookRepo bookRepo;
    private AuthorRepo authorRepo;
    private UserRepo userRepo;
    private CategoryRepo categoryRepo;
    private BookMapper bookMapper;
    private UserMapper userMapper;

    private UserServiceImpl userService;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo,
                           AuthorRepo authorRepo, UserRepo userRepo,
                           CategoryRepo categoryRepo, BookMapper bookMapper,
                           UserMapper userMapper, UserServiceImpl userService) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
        this.userService = userService;
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
        if (!Objects.isNull(bookModel.getId())) {
            return updateBook(bookModel);
        }
        if (checkTitleAndAuthorMatch(bookModel)) {
            throw new EntityExistsException("This book is already on the list");
        }
        Book newBook = bookMapper.mapBookModelToBook(bookModel);
        Author author = setAuthorFromBookModel(bookModel);
        newBook.setAuthor(author);
        newBook.setCategory(setCategoryFromBookModel(bookModel));
        Book savedBook = bookRepo.save(newBook);
        setAuthorsBookList(author, savedBook);
        return bookMapper.mapBookToBookModel(savedBook);
    }

    private BookModel updateBook(BookModel bookModel) throws Exception {
        Book bookFromDB = bookRepo.findById(bookModel.getId()).orElse(null);
        if (Objects.isNull(bookFromDB)) {
            throw new EntityNotFoundException("Book not found");
        }
        bookFromDB.setTitle(bookModel.getTitle());
        bookFromDB.setAuthor(setAuthorFromBookModel(bookModel));
        bookFromDB.setCategory(setCategoryFromBookModel(bookModel));
        bookFromDB.setQuantity(bookModel.getQuantity());
        bookFromDB.setContent(bookModel.getContent());
        bookFromDB.setPreface(bookModel.getPreface());
        bookFromDB.setPublisher(bookModel.getPublisher());
        return bookMapper.mapBookToBookModel(bookFromDB);
    }

    private Category setCategoryFromBookModel(BookModel bookModel) throws Exception {
        if (Objects.isNull(bookModel.getCategoryModel()) || Objects.isNull(bookModel.getCategoryModel().getId())) {
            throw new Exception("Category not found!");
        }
        return categoryRepo.findById(bookModel.getCategoryModel().getId()).orElse(null);
    }

    private void setAuthorsBookList(Author author, Book savedBook) {
        if (Objects.isNull(author.getBooks())) {
            List<Book> bookList = new ArrayList<>();
            bookList.add(savedBook);
            author.setBooks(bookList);
        } else {
            author.getBooks().add(savedBook);
        }
        authorRepo.save(author);
    }

    private Author setAuthorFromBookModel(BookModel bookModel) {
        if (Objects.isNull(bookModel.getAuthorModel().getId())) {
            Author newAuthor = new Author();
            newAuthor.setFirstName(bookModel.getAuthorModel().getFirstName());
            newAuthor.setLastName(bookModel.getAuthorModel().getLastName());
            newAuthor.setAge(bookModel.getAuthorModel().getAge());
            authorRepo.save(newAuthor);
            return newAuthor;
        } else {
            Author authorFromDB = (authorRepo.findById(bookModel.getAuthorModel().getId()).orElse(null));
            return authorFromDB;
        }
    }

    private boolean checkTitleAndAuthorMatch(BookModel bookModel) {
        if (!Objects.isNull(bookModel.getId())) {
            return true;
        }
        List<Book> bookListFromDB = bookRepo.findAllByTitle(bookModel.getTitle());
        for (Book book : bookListFromDB) {
            if (book.getTitle().equals(bookModel.getTitle())
                    && book.getAuthor().getFirstName().equals(bookModel.getAuthorModel().getFirstName())
                    && book.getAuthor().getLastName().equals(bookModel.getAuthorModel().getLastName())) {
                return true;
            }
        }
        return false;
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
        UserModel userModel = userService.getUserModelWithBookModelList(actualUser);
        return userModel;
    }

    private User getUserFromDB() {
        return userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
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
        return userService.getUserModelWithBookModelList(actualUser);
    }

    @Override
    public BookModel deleteBook(Long bookId) {
        Book bookFromDB = bookRepo.findById(bookId).orElse(null);
        if (Objects.isNull(bookFromDB)) {
            throw new EntityNotFoundException("Book not found!");
        } else {
            bookRepo.delete(bookFromDB);
        }
        return null;
    }

    private void validatBookIsEvenRented(Book bookFromDB, List<Book> usersBooks) throws Exception {
        if (!usersBooks.contains(bookFromDB)) {
            throw new Exception("This book is not even rented");
        }
    }
}
