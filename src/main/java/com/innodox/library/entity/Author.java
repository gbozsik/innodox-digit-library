package com.innodox.library.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"id", "firstName", "lastName"}, callSuper = true)
public class Author extends BaseEntity {

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "Last_NAME", nullable = false)
    private String lastName;

    @Column(name = "AGE")
    private Integer age;


}
