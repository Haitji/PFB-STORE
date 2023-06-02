package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.CategoryDTO;
import com.kreitek.store.application.mapper.CategoryMapper;
import com.kreitek.store.application.service.service.CategoryService;
import com.kreitek.store.domain.entity.Category;
import com.kreitek.store.domain.persistence.CategoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryPersistence persistence;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryPersistence persistence, CategoryMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = persistence.getAllCategories();
        return mapper.toDto(categories);
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(Long idCategory) {
        return persistence.getCategoryById(idCategory).map(mapper::toDto);
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO category) {
        Category category1 = persistence.saveCategory(mapper.toEntity(category));
        return mapper.toDto(category1);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        persistence.deleteCategory(idCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategoriesByName(String partialName) {
        List<Category> categories = this.persistence.getCategoryByName(partialName);
        return mapper.toDto(categories);
    }
}
