package com.innodox.library.service;

import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    UserModel getActualUser();

//    UserModel findByEmail(String email);
}
