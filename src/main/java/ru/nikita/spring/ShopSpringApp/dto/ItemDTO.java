package ru.nikita.spring.ShopSpringApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class ItemDTO {

    @NotEmpty(message = "У товара должно быть название")
    @Max(value = 100, message = "Название товара не должно превышать 100 символов")
    private String name;
    @Positive(message = "Цена должна быть положительным числом")
    private double price;
    private String photo;
    private CategoryDTO category;

    public ItemDTO() {
    }

    public ItemDTO(String name, double price, String photo) {
        this.name = name;
        this.price = price;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
