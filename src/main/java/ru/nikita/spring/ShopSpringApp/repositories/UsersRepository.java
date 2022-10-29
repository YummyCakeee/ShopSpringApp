package ru.nikita.spring.ShopSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikita.spring.ShopSpringApp.models.User;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    List<User> findAllByOrderByIdAsc();
    List<User> findAllByOrderByFirstNameAsc();
    List<User> findAllByOrderBySecondNameAsc();
    List<User> findAllByOrderByDateOfBirthAsc();
}
