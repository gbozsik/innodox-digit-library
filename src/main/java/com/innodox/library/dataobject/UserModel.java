package com.innodox.library.dataobject;

import com.innodox.library.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserModel {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<BookModel> bookModelList;
    private List<Role> roleList;
}
