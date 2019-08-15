package com.innodox.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Book extends BaseEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "PREFACE", nullable = false)
    private String preface;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private Category category;

//    public Book() {
//        super();
//    }

//    public Book(String title, String publisher, Author author, String preface, String content, Integer quantity, Category category) {
//        this.title = title;
//        this.publisher = publisher;
//        this.author = author;
//        this.preface = preface;
//        this.content = content;
//        this.quantity = quantity;
//        this.category = category;
//    }
}
