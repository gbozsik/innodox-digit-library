package com.innodox.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "BOOK", schema = "public")
@SequenceGenerator(name = "default_gen", sequenceName = "book_seq", allocationSize = 1)
public class Book extends BaseEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "PREFACE", nullable = false)
    private String preface;

    @Column(name = "CONTENT", columnDefinition = "clob", nullable = false)
    private String content;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private Category category;

}
