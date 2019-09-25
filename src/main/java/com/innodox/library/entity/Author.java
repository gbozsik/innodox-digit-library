package com.innodox.library.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"id", "firstName", "lastName"}, callSuper = true)
@Table(name = "AUTHOR", schema = "public")
@SequenceGenerator(name = "default_gen", sequenceName = "author_seq", allocationSize = 1)
public class Author extends BaseEntity {

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "AGE")
    private Integer age;
}
