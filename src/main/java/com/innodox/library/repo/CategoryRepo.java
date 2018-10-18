package com.innodox.library.repo;

import com.innodox.library.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryRepo extends CrudRepository<Category, Long> {

    Category findById(long id);

}
