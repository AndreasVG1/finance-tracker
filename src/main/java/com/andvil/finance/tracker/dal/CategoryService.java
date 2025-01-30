package com.andvil.finance.tracker.dal;

import com.andvil.finance.tracker.domain.Category;
import com.andvil.finance.tracker.domain.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    public Category createFromDTO(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategory_name(categoryDTO.getCategory_name());
        category.setTransactions(new ArrayList<>());
        return createCategory(category);
    }
}
