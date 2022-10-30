package ru.nikita.spring.ShopSpringApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.ShopSpringApp.dto.ItemDTO;
import ru.nikita.spring.ShopSpringApp.models.Item;
import ru.nikita.spring.ShopSpringApp.services.ItemsService;
import ru.nikita.spring.ShopSpringApp.util.FieldErrorsData;
import ru.nikita.spring.ShopSpringApp.util.ItemNotFoundException;
import ru.nikita.spring.ShopSpringApp.util.ResponseData;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
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
    public ResponseEntity<ResponseData> findAllItems(
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
    public ItemDTO findItemById(@PathVariable("id") int id) {
        Item foundItem = itemsService.findById(id);
        if (Objects.isNull(foundItem)) throw new ItemNotFoundException();
        return convertToItemDTO(itemsService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseData> saveItem(
            @RequestBody @Valid ItemDTO itemDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorsData = FieldErrorsData.getErrorsData(bindingResult.getFieldErrors());
            return new ResponseEntity<>(new ResponseData(false, errorsData), HttpStatus.BAD_REQUEST);
        }
        itemsService.save(convertToItem(itemDTO));
        return new ResponseEntity<>(new ResponseData(true), HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseData> itemNotFoundExceptionHandler(ItemNotFoundException e) {
        return new ResponseEntity<>(new ResponseData(false, "Такого товара нет"), HttpStatus.NOT_FOUND);
    }

    private ItemDTO convertToItemDTO (Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    private Item convertToItem(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }
}
