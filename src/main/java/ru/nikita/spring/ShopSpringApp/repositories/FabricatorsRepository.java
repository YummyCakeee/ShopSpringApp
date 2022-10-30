package ru.nikita.spring.ShopSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikita.spring.ShopSpringApp.models.Fabricator;

public interface FabricatorsRepository extends JpaRepository<Fabricator, Integer> {
}
