package com.restapi.expensetracker.repositories;

import com.restapi.expensetracker.domain.Category;
import com.restapi.expensetracker.exceptions.EtBadRequestException;
import com.restapi.expensetracker.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

    private static final String SQL_CREATE = "INSERT INTO ET_CATEGORIES (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION) " +
            "VALUES(NEXTVAL('ET_CATEGORIES_SEQ'), ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE ET_CATEGORIES SET TITLE = ?, DESCRIPTION = ? WHERE CATEGORY_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Category> findAll(Integer userId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Integer create(Integer userId, String title, String description) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, description);

                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("CATEGORY_ID");

        }catch (Exception e){
            throw new EtBadRequestException("Invalid Details");
        }
    }

    @Override
    public void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {

                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, category.getTitle());
                ps.setString(2, category.getDescription());
                ps.setInt(3, categoryId);

                return ps;

            }, keyHolder);

        } catch (Exception e){
            throw new EtBadRequestException("Invalid Details");
        }

    }

    @Override
    public void deleteById(Integer userId, Integer categoryId) {

    }
}
