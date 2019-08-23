package com.innodox.library.dataobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AuthorModel {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
