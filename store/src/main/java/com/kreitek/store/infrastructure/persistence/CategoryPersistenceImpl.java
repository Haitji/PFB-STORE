package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.application.mapper.CategoryMapper;
import com.kreitek.store.domain.entity.Category;
import com.kreitek.store.domain.persistence.CategoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryPersistenceImpl implements CategoryPersistence {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryPersistenceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories;
    }

    @Override
    public Optional<Category> getCategoryById(Long idCategory) {
        return this.categoryRepository.findById(idCategory);
    }

    @Override
    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        this.categoryRepository.deleteById(idCategory);
    }

    @Override
    public List<Category> getCategoryByName(String partialName) {
        return this.categoryRepository.findByNameContainsIgnoreCase(partialName);
    }
}
