package ru.nikita.spring.ShopSpringApp.test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.nikita.spring.ShopSpringApp.models.User;
import ru.nikita.spring.ShopSpringApp.repositories.UsersRepository;
import ru.nikita.spring.ShopSpringApp.services.UsersService;

import java.util.Optional;

import static org.testng.Assert.*;

public class UsersServiceTest {

    @Mock
    UsersRepository usersRepository;
    UsersService usersService;

    @BeforeTest
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
        usersService = new UsersService(usersRepository, null);
    }

    @Test(groups = {"userService"})
    public void testFindById() {
        Mockito.when(usersRepository.findById(1)).thenReturn(Optional.of(new User(){{setId(1);}}));

        User userById = usersService.findById(1);
        assertEquals(userById.getId(), 1);
    }
}