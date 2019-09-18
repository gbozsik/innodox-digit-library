package com.innodox.library.entity;

import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Component
@Entity
@Table(name = "USER", schema = "public")
public class User extends BaseEntity {

    @Column(name = "USER_NAME", nullable = false)
    private String username;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "ROLES", nullable = false)
    private String[] roles;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PASSWORD_ID", referencedColumnName = "ID")
    private Password password;


    @ManyToMany
    @JoinTable(
        name = "USERS_BOOKS",
        joinColumns = {@JoinColumn(name = "USER_ID")},
        inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")}
    )
    private List<Book> bookList;


    public User() {
        super();
    }

    public User(String username, String email, String firstName, String lastName, Password password, List<Book> bookList, String[] roles) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.bookList = bookList;
        this.roles = roles;
    }
    public User(String username, String email, String firstName, String lastName, Password password, String[] roles) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
