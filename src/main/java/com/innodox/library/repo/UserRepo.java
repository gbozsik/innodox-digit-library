package com.innodox.library.repo;

import com.innodox.library.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query(value = "SELECT role_id FROM users_roles WHERE user_id = ?1", nativeQuery = true)
    Set<Object[]> findRolesOfUser(Long id);

}
