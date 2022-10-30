package ru.nikita.spring.ShopSpringApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.ShopSpringApp.dto.CategoryDTO;
import ru.nikita.spring.ShopSpringApp.models.Category;
import ru.nikita.spring.ShopSpringApp.services.CategoriesService;
import ru.nikita.spring.ShopSpringApp.util.FieldErrorsData;
import ru.nikita.spring.ShopSpringApp.util.ItemNotFoundException;
import ru.nikita.spring.ShopSpringApp.util.ResponseData;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoriesService categoriesService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoriesService categoriesService, ModelMapper modelMapper) {
        this.categoriesService = categoriesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseData> findAllCategories(@RequestParam(value = "name", required = false) String name) {
        List<Category> categories;
        if (name != null)
            categories = categoriesService.findByName(name);
        else categories = categoriesService.findAll();

        List<CategoryDTO> categoryDTOs = categories.stream().map(this::convertToCategoryDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResponseData(categoryDTOs, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> findCategoryById(@PathVariable("id") int id) {
        Category foundCategory = categoriesService.findById(id);
        if (Objects.isNull(foundCategory)) {
            throw new ItemNotFoundException();
        }
        CategoryDTO categoryDTO = convertToCategoryDTO(categoriesService.findById(id));

        return new ResponseEntity<>(new ResponseData(categoryDTO, true), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseData> addCategory(@RequestBody @Valid CategoryDTO categoryDTO,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorsData = FieldErrorsData.getErrorsData(bindingResult.getFieldErrors());
            return new ResponseEntity<>(new ResponseData(false, errorsData), HttpStatus.BAD_REQUEST);
        }
        Category category = convertToCategory(categoryDTO);
        category.setId(0);
        categoriesService.save(convertToCategory(categoryDTO));
        return new ResponseEntity<>(new ResponseData(true), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteCategory(@PathVariable("id") int id) {
        categoriesService.deleteById(id);
        return new ResponseEntity<>(new ResponseData(true), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseData> categoryNotFoundExceptionHandler(ItemNotFoundException e) {
        return new ResponseEntity<>(new ResponseData(false, "Такой категории нет"), HttpStatus.NOT_FOUND);
    }

    private Category convertToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
