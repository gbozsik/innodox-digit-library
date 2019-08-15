package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.User;
import com.innodox.library.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserModel getActualUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username.equals("anonymousUser")){
            User anonymousUser = new User();
            anonymousUser.setFirstName("Nincs bejelentkezve");
            return userMapper.mapUserToUserModel(anonymousUser);
        }
        User user = userRepo.findByUsername(username);
        return userMapper.mapUserToUserModel(user);
    }

//    @Override
//    public UserModel findByEmail(String email) {
//        User user = userRepo.findByEmail(email);
//        return UserModel.buildUserModel(user);
//    }
}
