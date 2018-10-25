package com.innodox.library.core;

import com.innodox.library.controller.BookController;
import com.innodox.library.entity.User;
import com.innodox.library.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.logging.Logger;

@Component
public class DetailService implements UserDetailsService, UserDetails {

    private static final Logger logger = Logger.getLogger(BookController.class.getName());


    private UserRepo userRepository;
    private User user;

    @Autowired
    public DetailService(UserRepo userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("USERNAME: " + username);
        User user = userRepository.findByEmail(username);
        logger.info("User: " + user);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found!");
        }
        String password = user.getPassword().getPassword();
        logger.info("password: " + password);
        logger.info(user.getUsername());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                password,
                AuthorityUtils.createAuthorityList(user.getRoles())
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
//        return user.getPassword().getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

