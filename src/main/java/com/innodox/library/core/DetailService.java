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


/**Implementálja a spring inerface-eket és megváltoztatja ami a mi felhasználónk igénye szerint,
 *hogy mit adunk vissza a Spring-nek a a loadByUserName metódusával
 */



@Component
public class DetailService implements UserDetailsService, UserDetails {

    private static final Logger logger = Logger.getLogger(DetailService.class.getName());

    private UserRepo userRepository;
    private User user;

    @Autowired
    public DetailService(UserRepo userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found!");
        }
        String password = user.getPassword().getPassword();
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
    }

    @Override
    public String getUsername() {           //a username helyett az email-t adja vissza így azt kezeli a Spring felhasználónéként
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

