package ru.nikita.spring.ShopSpringApp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category", referencedColumnName = "id")
    private Category owner;

    @OneToMany(mappedBy = "owner")
    private List<Category> subcategories;

    @OneToMany(mappedBy = "category")
    private List<Item> items;

    @OneToMany(mappedBy = "owner")
    private List<CategoryFilter> categoryFilters;

    public Category() {
    }

    public Category(String name) {
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

    public Category getOwner() {
        return owner;
    }

    public void setOwner(Category owner) {
        this.owner = owner;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
