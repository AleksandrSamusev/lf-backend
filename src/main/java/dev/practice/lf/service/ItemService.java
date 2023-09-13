package dev.practice.lf.service;

import dev.practice.lf.dto.ItemDto;
import dev.practice.lf.dto.ItemFullDto;
import dev.practice.lf.entity.Item;

import java.util.List;

public interface ItemService {
    Item createItem(ItemDto dto);
    ItemFullDto getItemById(Long itemId);
    List<ItemFullDto> getAllItems();
    ItemFullDto updateItem(Long itemId, ItemDto updatedItem);
    void deleteItem(Long itemId);
}
