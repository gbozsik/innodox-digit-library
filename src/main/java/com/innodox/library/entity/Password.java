package com.innodox.library.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




@Getter
@Setter
@Entity
@Table(name = "PASSWORD", schema = "public")
@SequenceGenerator(name = "default_gen", sequenceName = "password_seq", allocationSize = 1)
public class Password extends BaseEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

//    @JsonIgnore
//    private Long id;
//    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false)
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

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
}
