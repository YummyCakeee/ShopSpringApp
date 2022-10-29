package ru.nikita.spring.ShopSpringApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nikita.spring.ShopSpringApp.models.Item;

import java.util.List;

@Repository
public class ItemDAO {
    private final JdbcTemplate jdbcTemplate;

    @Value("${database.tables.item}")
    private String tableName;

    @Autowired
    public ItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> findByCategoryId(int id) {
        System.out.println(tableName);
        return jdbcTemplate.query("SELECT * FROM " + tableName  + " WHERE category_id=?",
                new Object[] {id}, new BeanPropertyRowMapper<>(Item.class));
    }
}
