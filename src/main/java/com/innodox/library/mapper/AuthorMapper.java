package com.innodox.library.mapper;

import com.innodox.library.dataobject.AuthorModel;
import com.innodox.library.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//@Mapper(componentModel = "spring")
public interface AuthorMapper {



    Author authorModelToAuthor(AuthorModel authorModel);
    AuthorModel authorToAuthorModel(Author author);
}
