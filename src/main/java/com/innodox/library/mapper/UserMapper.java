package com.innodox.library.mapper;

import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "bookModelList", source = "bookList")
    })
    UserModel mapUserToUserModel(User user);
}
