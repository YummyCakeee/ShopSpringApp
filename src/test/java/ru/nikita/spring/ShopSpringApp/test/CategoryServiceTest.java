package ru.nikita.spring.ShopSpringApp.test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.nikita.spring.ShopSpringApp.repositories.CategoriesRepository;
import ru.nikita.spring.ShopSpringApp.services.CategoriesService;

public class CategoryServiceTest {
    CategoriesService categoriesService;
    @Mock
    CategoriesRepository categoriesRepository;

    @BeforeTest
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
        categoriesService = new CategoriesService(categoriesRepository);
    }

    @Test(groups = {"categoryService"})
    public void testAddCategory() {
        categoriesService.findAll();
        Mockito.verify(categoriesRepository, Mockito.times(1)).findAll();
    }
}
