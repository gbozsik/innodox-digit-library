package com.innodox.library.repo;

import com.innodox.library.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

}
