package ru.nikita.spring.ShopSpringApp.models;

import javax.persistence.*;

@Entity
@Table(name = "Category_filter")
public class CategoryFilter {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id",
            referencedColumnName = "id")
    private Category owner;

    public CategoryFilter() {
    }

    public CategoryFilter(String name) {
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
}
