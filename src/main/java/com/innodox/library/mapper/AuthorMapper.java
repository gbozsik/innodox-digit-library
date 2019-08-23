package com.innodox.library.mapper;

import com.innodox.library.dataobject.AuthorModel;
import com.innodox.library.entity.Author;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author authorModelToAuthor(AuthorModel authorModel);
    AuthorModel authorToAuthorModel(Author author);
}
