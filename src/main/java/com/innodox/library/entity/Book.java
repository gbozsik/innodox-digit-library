package com.innodox.library.entity;

//import lombok.Getter;
//import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

//@Getter
//@Setter
@Entity
public class Book extends BaseEntity {


    private String title;
    private String publisher;
    private String author;
    @Column(length = 200)
    private String preface;
    @Column(length = 5000)
    private String content;
    private Integer quantity;

    @ManyToOne
    private Category category;

    public Book() { super();  }

    public Book(String title, String publisher, String author, String preface, String content, Integer quantity, Category category) {
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.preface = preface;
        this.content = content;
        this.quantity = quantity;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
