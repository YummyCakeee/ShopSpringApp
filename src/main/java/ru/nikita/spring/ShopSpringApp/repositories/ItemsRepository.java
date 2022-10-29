package ru.nikita.spring.ShopSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikita.spring.ShopSpringApp.models.Item;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
}
