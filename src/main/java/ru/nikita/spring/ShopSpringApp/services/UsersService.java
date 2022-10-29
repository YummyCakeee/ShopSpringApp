package ru.nikita.spring.ShopSpringApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikita.spring.ShopSpringApp.dao.UserDAO;
import ru.nikita.spring.ShopSpringApp.models.User;
import ru.nikita.spring.ShopSpringApp.repositories.UsersRepository;
import ru.nikita.spring.ShopSpringApp.util.UserSortMode;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;
    private final UserDAO userDAO;

    @Autowired
    public UsersService(UsersRepository usersRepository, UserDAO userDAO) {
        this.usersRepository = usersRepository;
        this.userDAO = userDAO;
    }

    public List<User> findAll(UserSortMode userSortMode) {
        return switch (userSortMode) {
            case ID -> usersRepository.findAllByOrderByIdAsc();
            case FIRST_NAME -> usersRepository.findAllByOrderByFirstNameAsc();
            case SECOND_NAME -> usersRepository.findAllByOrderBySecondNameAsc();
            case DATE_OF_BIRTH -> usersRepository.findAllByOrderByDateOfBirthAsc();
            default -> usersRepository.findAll();
        };
    }

    public User findById(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void deleteById(int id) {
        usersRepository.deleteById(id);
    }


}
