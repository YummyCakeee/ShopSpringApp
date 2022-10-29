package ru.nikita.spring.ShopSpringApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.ShopSpringApp.dto.ItemDTO;
import ru.nikita.spring.ShopSpringApp.models.Item;
import ru.nikita.spring.ShopSpringApp.services.ItemsService;
import ru.nikita.spring.ShopSpringApp.util.ResponseData;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemsService itemsService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemController(ItemsService itemsService, ModelMapper modelMapper) {
        this.itemsService = itemsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseData> getAllItems(
            @RequestParam(name = "category", required = false, defaultValue = "-1") int id) {
        List<Item> items;
        if (id > 0)
            items = itemsService.findByCategoryId(id);
        else
            items = itemsService.findAll();
        List<ItemDTO> response = items.stream().map(this::convertToItemDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ResponseData(response, true) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable("id") int id) {
        return convertToItemDTO(itemsService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseData> addItem(@RequestBody ItemDTO itemDTO) {
        itemsService.save(convertToItem(itemDTO));
        return new ResponseEntity<>(new ResponseData(true), HttpStatus.OK);
    }

    private ItemDTO convertToItemDTO (Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    private Item convertToItem(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }
}
