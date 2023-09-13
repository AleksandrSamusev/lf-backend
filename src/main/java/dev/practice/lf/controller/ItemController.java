package dev.practice.lf.controller;

import dev.practice.lf.dto.ItemDto;
import dev.practice.lf.dto.ItemFullDto;
import dev.practice.lf.entity.Item;
import dev.practice.lf.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto) {
        Item savedItem = itemService.createItem(itemDto);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{itemId}")
    public ResponseEntity<ItemFullDto> getItemById(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<ItemFullDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ItemFullDto> updateItem(@PathVariable("id") Long itemId,
                                              @RequestBody ItemDto dto) {
        ItemFullDto itemFullDto = itemService.updateItem(itemId, dto);
        return ResponseEntity.ok(itemFullDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.ok("Item with id: {" + itemId +"} deleted");
    }
}
