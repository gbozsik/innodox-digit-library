package com.innodox.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Setter
@Getter
@Entity
public class Storage extends BaseEntity {

    public Storage() {
    }
//    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    private Book book;

    public Storage(Integer quantity, Book book) {
        this.quantity = quantity;
        this.book = book;
    }
}
