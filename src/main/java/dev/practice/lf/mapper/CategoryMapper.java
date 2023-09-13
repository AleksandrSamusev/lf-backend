package dev.practice.lf.mapper;

import dev.practice.lf.dto.CategoryDto;
import dev.practice.lf.entity.Category;
import org.springframework.stereotype.Component;

@Component

public class CategoryMapper {

    public static CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public static Category toCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getDescription()
        );
    }
}
