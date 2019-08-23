package com.innodox.library.dataobject;

import com.innodox.library.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private Category category;
}
