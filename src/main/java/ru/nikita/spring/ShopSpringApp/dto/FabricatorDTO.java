package ru.nikita.spring.ShopSpringApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public class FabricatorDTO {
    private int id;

    @NotEmpty(message = "У производителя должно быть название")
    @Max(value = 50, message = "Длина названия не должна превышать 50 символов")
    private String name;

    public FabricatorDTO() {
    }

    public FabricatorDTO(String name) {
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
}
