package com.kreitek.store.application.service.service;

import com.kreitek.store.application.dto.CategoryDTO;
import com.kreitek.store.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    Optional<CategoryDTO> getCategoryById(Long idCategory);
    CategoryDTO saveCategory(CategoryDTO category);
    void deleteCategory(Long idCategory);

    List<CategoryDTO> getAllCategoriesByName(String partialName);
}
