package com.innodox.library.service;

import com.innodox.library.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    User getActualUser();

    User findByEmail(String email);
}
