package com.innodox.library.dataobject;

import com.innodox.library.entity.Author;
import com.innodox.library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
