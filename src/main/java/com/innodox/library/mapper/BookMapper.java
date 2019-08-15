package com.innodox.library.mapper;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book mapBookModelToBook(BookModel bookModel);
    BookModel mapBookToBookModel(Book book);
}
