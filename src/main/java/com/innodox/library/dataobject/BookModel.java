package com.innodox.library.dataobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookModel {
    private Long id;
    private String title;
    private String publisher;
    private String preface;
    private String content;
    private Integer quantity;
    private AuthorModel authorModel;
    private CategoryModel categoryModel;
    ;
}
