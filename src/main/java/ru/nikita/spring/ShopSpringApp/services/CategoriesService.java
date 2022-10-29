package ru.nikita.spring.ShopSpringApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikita.spring.ShopSpringApp.models.Category;
import ru.nikita.spring.ShopSpringApp.repositories.CategoriesRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    public List<Category> findByName(String name) {
        return categoriesRepository.findCategoriesByName(name);
    }

    public Category findById(int id) {
        return categoriesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Category category) {
        categoriesRepository.save(category);
    }

    @Transactional
    public void deleteById(int id) {
        categoriesRepository.deleteById(id);
    }
}
