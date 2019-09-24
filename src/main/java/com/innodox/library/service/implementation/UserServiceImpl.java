package com.innodox.library.service.implementation;

import com.innodox.library.dataobject.BookModel;
import com.innodox.library.dataobject.UserModel;
import com.innodox.library.entity.User;
import com.innodox.library.mapper.BookMapper;
import com.innodox.library.mapper.UserMapper;
import com.innodox.library.repo.RoleRepo;
import com.innodox.library.repo.UserRepo;
import com.innodox.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private UserRepo userRepo;

    private UserMapper userMapper;
    private BookMapper bookMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo,
                           UserMapper userMapper,
                           BookMapper bookMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }


    @Override
    public UserModel getActualUser() {
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email.equals("anonymousUser")) {
            User anonymousUser = new User();
            anonymousUser.setFirstName("Nincs bejelentkezve");
            return userMapper.mapUserToUserModel(anonymousUser);
        }
        User user = userRepo.findByEmail(email);
        return getUserModelWithBookModelList(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public String[] getRolesOfUser(Long userId) {
        User userFromDb = userRepo.findById(userId).orElse(null);

//        Set<Object[]> roleIdsOfUser = userRepo.findRolesOfUser(userId);
//        List<Long> roleIdList = new ArrayList<>();
//        for (Object[] obj : roleIdsOfUser) {
//            BigInteger idOfRole = (BigInteger) obj[0];
//            roleIdList.add(idOfRole.longValue());
//        }
//        List<String> roles = new ArrayList<>();
//        for (Long roleId : roleIdList) {
//            roles.add(roleRepo.findById(roleId).orElse(null).getRoleName());
//        }
//        roleIdList.forEach(roleId -> roles.add(roleRepo.findById(roleId).orElse(null).getRoleName()));
//        return  (String[]) roles.toArray();
        return null;
    }

    UserModel getUserModelWithBookModelList(User user) {
        List<BookModel> bookModelList = user.getBookList().stream()
                .map(book -> bookMapper.mapBookToBookModel(book))
                .collect(Collectors.toList());
        UserModel userModel = userMapper.mapUserToUserModel(user);
        userModel.setBookModelList(bookModelList);
        return userModel;
    }
}
