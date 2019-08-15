package com.innodox.library.dataobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String[] roles;
    private List<BookModel> bookModelList;
}
