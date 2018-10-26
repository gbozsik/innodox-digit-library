package com.innodox.library.service.implementation;

import com.innodox.library.entity.User;
import com.innodox.library.repo.UserRepo;
import com.innodox.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public User getActualUser() {               //A Spring visszaadja az épp bejelentkezett user-t
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
