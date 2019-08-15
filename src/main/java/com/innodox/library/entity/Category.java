package com.innodox.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

//    @OneToMany(mappedBy = "category")
//    private List<Book> bookModelList;

    public Category(){
        super();
    }

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
