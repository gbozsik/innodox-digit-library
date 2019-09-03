package com.innodox.library.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
