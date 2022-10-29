package ru.nikita.spring.ShopSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikita.spring.ShopSpringApp.models.Category;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoriesByName(String name);
}
