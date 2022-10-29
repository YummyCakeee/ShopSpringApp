package ru.nikita.spring.ShopSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikita.spring.ShopSpringApp.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}
