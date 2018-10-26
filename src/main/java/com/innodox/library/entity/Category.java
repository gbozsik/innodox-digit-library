package com.innodox.library.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;

@Component
@Entity
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
