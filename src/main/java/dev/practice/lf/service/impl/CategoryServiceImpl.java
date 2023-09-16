package dev.practice.lf.service.impl;

import dev.practice.lf.dto.CategoryDto;
import dev.practice.lf.entity.Category;
import dev.practice.lf.exception.ResourceNotFoundException;
import dev.practice.lf.mapper.CategoryMapper;
import dev.practice.lf.repository.CategoryRepository;
import dev.practice.lf.repository.ItemRepository;
import dev.practice.lf.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(CategoryMapper.toCategory(categoryDto));
        log.info("Category with id = " + savedCategory.getId() + " saved");
        return CategoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category with given id: " + id + " not exist"));
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto updatedCategory) {
        Category dbCategory = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category with given id: " + id + " not exist"));
        if (updatedCategory.getName() != null) {
            dbCategory.setName(updatedCategory.getName());
        }
        if (updatedCategory.getDescription() != null) {
            dbCategory.setDescription(updatedCategory.getDescription());
        }
        Category savedCategory = categoryRepository.save(dbCategory);
        return CategoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public String deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return "Category with id:" + id + " was successfully deleted";
    }
}
