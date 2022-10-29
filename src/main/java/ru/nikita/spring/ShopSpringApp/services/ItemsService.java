package ru.nikita.spring.ShopSpringApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikita.spring.ShopSpringApp.dao.ItemDAO;
import ru.nikita.spring.ShopSpringApp.models.Category;
import ru.nikita.spring.ShopSpringApp.models.Item;
import ru.nikita.spring.ShopSpringApp.repositories.CategoriesRepository;
import ru.nikita.spring.ShopSpringApp.repositories.ItemsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemsService {
    private final ItemsRepository itemsRepository;
    private final ItemDAO itemDAO;
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository, ItemDAO itemDAO, CategoriesRepository categoriesRepository) {
        this.itemsRepository = itemsRepository;
        this.itemDAO = itemDAO;
        this.categoriesRepository = categoriesRepository;
    }

    public List<Item> findAll() {
        return itemsRepository.findAll();
    }

    public Item findById(int id) {
        return itemsRepository.findById(id).orElse(null);
    }

    public List<Item> findByCategoryId(int id) {
        List<Item> items = itemDAO.findByCategoryId(id);
        Category category = categoriesRepository.findById(id).orElse(null);
        items.forEach(e -> e.setCategory(category));
        return items;
    }

    @Transactional
    public void save(Item item) {
        itemsRepository.save(item);
    }
}
