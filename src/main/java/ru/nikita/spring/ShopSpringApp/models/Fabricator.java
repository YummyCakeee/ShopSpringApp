package ru.nikita.spring.ShopSpringApp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Fabricator")
public class Fabricator {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fabricator")
    private List<Item> items;

    public Fabricator() {
    }

    public Fabricator(String name, List<Item> items) {
        this.name = name;
        this.items = items;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
