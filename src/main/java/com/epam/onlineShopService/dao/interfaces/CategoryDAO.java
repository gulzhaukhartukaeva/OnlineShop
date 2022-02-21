package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Order;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO extends CommonDAO<Category> {
    Long takeLastID() throws SQLException;
    List<Category> takeAllLocalizedCategoryByID(Long categoryId) throws SQLException, IOException;
}