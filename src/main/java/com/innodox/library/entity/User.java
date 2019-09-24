package com.innodox.library.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USER", schema = "public")
@SequenceGenerator(name = "default_gen", sequenceName = "seq_user")
public class User extends BaseEntity {

    @Column(name = "USER_NAME", nullable = false)
    private String username;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PASSWORD_ID", referencedColumnName = "ID")
    private Password password;

    @ManyToMany
    @JoinTable(
        name = "USERS_BOOKS", schema = "public",
        joinColumns = {@JoinColumn(name = "USER_ID")},
        inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")}
    )
    private List<Book> bookList;

    @ManyToMany
    @JoinTable(
        name = "USERS_ROLES", schema = "public",
        joinColumns = {@JoinColumn(name = "ROLE_ID")},
        inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private List<Role> roleList;

    public User() {
        super();
    }

    public User(String username, String email, String firstName, String lastName, Password password, List<Book> bookList) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.bookList = bookList;
    }
    public User(String username, String email, String firstName, String lastName, Password password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
