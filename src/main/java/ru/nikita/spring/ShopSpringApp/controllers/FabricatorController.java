package ru.nikita.spring.ShopSpringApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.ShopSpringApp.dto.FabricatorDTO;
import ru.nikita.spring.ShopSpringApp.models.Fabricator;
import ru.nikita.spring.ShopSpringApp.services.FabricatorsService;
import ru.nikita.spring.ShopSpringApp.util.FieldErrorsData;
import ru.nikita.spring.ShopSpringApp.util.ItemNotFoundException;
import ru.nikita.spring.ShopSpringApp.util.ResponseData;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fabricators")
public class FabricatorController {
    private final FabricatorsService fabricatorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public FabricatorController(FabricatorsService fabricatorsService, ModelMapper modelMapper) {
        this.fabricatorsService = fabricatorsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseData> findAllFabricators() {
        List<FabricatorDTO> response = fabricatorsService.findAll().stream()
                .map(this::convertToFabricatorDTO).collect(Collectors.toList());
        return new ResponseEntity<>(new ResponseData(response, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> findFabricatorById(@PathVariable("id") int id) {
        Fabricator fabricator = fabricatorsService.findById(id);
        if (Objects.isNull(fabricator)) throw new ItemNotFoundException();
        return new ResponseEntity<>(new ResponseData(convertToFabricatorDTO(fabricator), true),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseData> saveFabricator(
            @RequestBody @Valid FabricatorDTO fabricatorDTO,
            BindingResult bindingResult) {
        fabricatorDTO.setId(0);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ResponseData(false,
                    FieldErrorsData.getErrorsData(bindingResult.getFieldErrors())), HttpStatus.BAD_REQUEST);
        }
        fabricatorsService.save(convertToFabricator(fabricatorDTO));
        return new ResponseEntity<>(new ResponseData(true), HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseData> fabricatorNotFoundExceptionHandler(ItemNotFoundException e) {
        return new ResponseEntity<>(new ResponseData(false, "Такого производителя нет"),
                HttpStatus.NOT_FOUND);
    }

    private Fabricator convertToFabricator(FabricatorDTO fabricatorDTO) {
        return modelMapper.map(fabricatorDTO, Fabricator.class);
    }

    private FabricatorDTO convertToFabricatorDTO(Fabricator fabricator) {
        return modelMapper.map(fabricator, FabricatorDTO.class);
    }
}
