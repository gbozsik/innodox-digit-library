package com.innodox.library.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.innodox.library.entity.Author;
import com.innodox.library.entity.Book;
import com.innodox.library.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookModel implements Serializable {
    private Long id;
    private String title;
    private String publisher;
    private String preface;
    private String content;
    private Integer quantity;
    private AuthorModel authorModel;
    private Category category;
}
