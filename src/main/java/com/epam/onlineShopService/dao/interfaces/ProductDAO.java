package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Order;
import com.epam.onlineShopService.entity.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CommonDAO<Product> {
    Long takeLastID() throws SQLException;
    List<Product> takeAllLocalizedProductByID(Long productId, Integer languageId) throws SQLException, IOException;
    List<Product> findByCategoryId(Integer languageId, Long categoryId) throws SQLException, IOException;
}
