package com.epam.online.shop.service.dao.interfaces;

import com.epam.online.shop.service.entity.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO extends CommonDAO<Category> {
    Long takeLastId() throws SQLException;
    List<Category> takeAllLocalizedCategoryByID(Long categoryId) throws SQLException, IOException;
}