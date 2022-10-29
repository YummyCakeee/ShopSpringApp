package ru.nikita.spring.ShopSpringApp.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.nikita.spring.ShopSpringApp.models.Category;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CategoryDTO {
    private int id;

    @NotEmpty(message = "У категории должно быть название")
    private String name;
    private List<CategoryDTO> subcategories;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<CategoryDTO> subcategories) {
        this.subcategories = subcategories;
    }
}
