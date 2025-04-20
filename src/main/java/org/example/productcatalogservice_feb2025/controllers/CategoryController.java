package org.example.productcatalogservice_feb2025.controllers;

import org.example.productcatalogservice_feb2025.dtos.CategoryDTO;
import org.example.productcatalogservice_feb2025.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CategoryController {

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return null;
    }

    @GetMapping("/category/{id}")
    public CategoryDTO getCategoryDetails(@PathVariable Long id) {
        return null;
    }

    @PatchMapping("/category/{id}")
    public CategoryDTO updateCategoryDetails(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return null;
    }

    @PutMapping("/category/{id}")
    public CategoryDTO replaceCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return null;
    }

    @PostMapping("/category/{id}")
    public CategoryDTO createCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return null;
    }

    @DeleteMapping("/category/{id}")
    public boolean deleteCategory(@PathVariable Long id) {
        return false;
    }

    public CategoryDTO from(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public Category to(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }

}
