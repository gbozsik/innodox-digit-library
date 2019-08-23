package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.User;
import com.innodox.library.mapper.BookMapper;
import com.innodox.library.mapper.UserMapper;
import com.innodox.library.repo.UserRepo;
import com.innodox.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private UserMapper userMapper;
    private BookMapper bookMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, BookMapper bookMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
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
        return getUserModelWithBookModelList(user);
    }

    UserModel getUserModelWithBookModelList(User user) {

        List<BookModel> bookModelList = user.getBookList().stream()
                .map(book -> bookMapper.mapBookToBookModel(book))
                .collect(Collectors.toList());
        UserModel userModel = userMapper.mapUserToUserModel(user);
        userModel.setBookModelList(bookModelList);
        return userModel;
    }

//    @Override
//    public UserModel findByEmail(String email) {
//        User user = userRepo.findByEmail(email);
//        return UserModel.buildUserModel(user);
//    }
}
