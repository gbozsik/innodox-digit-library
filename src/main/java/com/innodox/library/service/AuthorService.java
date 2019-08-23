package com.innodox.library.service;

import com.innodox.library.dataobject.AuthorModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorService {

    List<AuthorModel> getAuthors();
}
