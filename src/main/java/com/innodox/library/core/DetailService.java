package com.innodox.library.core;

import com.innodox.library.entity.User;
import com.innodox.library.repo.UserRepo;
import com.innodox.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**Implementálja a spring inerface-eket és megváltoztatja ami a mi felhasználónk igénye szerint,
 *hogy mit adunk vissza a Spring-nek a a loadByUserName metódusával
 */



@Component
public class DetailService implements UserDetailsService, UserDetails {

    private UserRepo userRepository;
    private UserService userService;
    private User user;

    @Autowired
    public DetailService(UserRepo userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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
                AuthorityUtils.createAuthorityList(user.getRoleList().toString())
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

