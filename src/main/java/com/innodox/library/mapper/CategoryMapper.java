package com.innodox.library.mapper;

import com.innodox.library.dataobject.CategoryModel;
import com.innodox.library.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryModel mapCategoryToCategoryModel(Category category);
}
