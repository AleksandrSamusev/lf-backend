package dev.practice.lf.service.impl;

import dev.practice.lf.dto.ItemDto;
import dev.practice.lf.dto.ItemFullDto;
import dev.practice.lf.entity.Category;
import dev.practice.lf.entity.Item;
import dev.practice.lf.exception.ResourceNotFoundException;
import dev.practice.lf.mapper.CategoryMapper;
import dev.practice.lf.mapper.ItemMapper;

import dev.practice.lf.repository.ItemRepository;
import dev.practice.lf.service.CategoryService;
import dev.practice.lf.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryService categoryService;

    @Override
    public ItemFullDto createItem(ItemDto dto) {
        Set<Category> categories = new HashSet<>();
        for (Long id : dto.getCategoriesIds()) {
            categories.add(CategoryMapper.toCategory(categoryService.getCategoryById(id)));
        }
        Item item = ItemMapper.toItem(dto, categories);
        Item savedItem = itemRepository.save(item);
        ItemFullDto fullDto = ItemMapper.toItemFullDto(savedItem);
        return fullDto;

    }

    @Override
    public ItemFullDto getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new ResourceNotFoundException("Item is not exists with given id"));

        return ItemMapper.toItemFullDto(item);
    }

    @Override
    public List<ItemFullDto> getAllItems() {
        List<Item> list = itemRepository.findAll();

        return list.stream().map(ItemMapper::toItemFullDto).collect(Collectors.toList());
    }

    @Override
    public ItemFullDto updateItem(Long itemId, ItemDto updatedItem) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new ResourceNotFoundException("Item is not exists with given id"));
        if (updatedItem.getDescription() != null) {
            item.setDescription(updatedItem.getDescription());
        }
        if (updatedItem.getLocationDescription() != null) {
            item.setLocationDescription(updatedItem.getLocationDescription());
        }
        Item savedItem = itemRepository.save(item);
        return ItemMapper.toItemFullDto(savedItem);
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
