package dev.practice.lf.mapper;

import dev.practice.lf.dto.ItemDto;
import dev.practice.lf.dto.ItemFullDto;
import dev.practice.lf.entity.Category;
import dev.practice.lf.entity.Item;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public static Item toItem(ItemDto dto, Set<Category> categories) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setDescription(dto.getDescription());
        item.setLocationDescription(dto.getLocationDescription());
        item.setPostDateTime(dto.getPostDateTime());
        item.setFoundDate(dto.getFoundDate());
        item.setCategories(categories);

        return item;
    }

    public static ItemDto toItemDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setDescription(item.getDescription());
        dto.setLocationDescription(item.getLocationDescription());
        dto.setFoundDate(item.getFoundDate());
        dto.setPostDateTime(item.getPostDateTime());
        dto.setCategoriesIds(item.getCategories().stream()
                .map(Category::getId).collect(Collectors.toSet()));
        return dto;
    }

    public static ItemFullDto toItemFullDto(Item item) {
        return new ItemFullDto(
                item.getId(),
                item.getDescription(),
                item.getLocationDescription(),
                item.getPostDateTime(),
                item.getFoundDate(),
                item.getCategories().stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toSet())
        );
    }
}
