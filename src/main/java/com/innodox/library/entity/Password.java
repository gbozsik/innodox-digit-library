package com.innodox.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

/**
 * Azért nem a USER táblában van a jelszó mert így lehetne extrázni a jelszóval pl. lejáratot beállítani
 */



@Component
@Entity
public class Password extends BaseEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String password;

    public Password() {
        super();
    }

    public Password(String password) {
       setPassword(password);
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String getPassword() {
        return password;
    }

    public static PasswordEncoder getPasswordEncoder() {
        return PASSWORD_ENCODER;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
