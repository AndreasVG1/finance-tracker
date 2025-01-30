package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.ResourceNotFoundException;
import com.andvil.finance.tracker.dal.CategoryService;
import com.andvil.finance.tracker.domain.Category;
import com.andvil.finance.tracker.domain.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOS = categories.stream().map(Category::convertToDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategory(id).orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
        return ResponseEntity.status(HttpStatus.OK).body(category.convertToDTO());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.createFromDTO(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(category.convertToDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.getCategory(id).orElse(null);
        if (category != null) {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
