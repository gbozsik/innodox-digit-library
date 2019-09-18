package com.innodox.library.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "CATEGORY", schema = "public")
public class Category extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
