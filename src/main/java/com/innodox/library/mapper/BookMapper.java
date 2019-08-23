package com.innodox.library.mapper;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mappings({
            @Mapping(target = "author", source = "authorModel")
    })
    Book mapBookModelToBook(BookModel bookModel);

    @Mappings({
            @Mapping(target = "authorModel", source = "author")
    })
    BookModel mapBookToBookModel(Book book);

}
