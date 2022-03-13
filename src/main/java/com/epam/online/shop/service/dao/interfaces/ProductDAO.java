package com.epam.online.shop.service.dao.interfaces;

import com.epam.online.shop.service.entity.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CommonDAO<Product> {
    Long takeLastId() throws SQLException;
    List<Product> takeAllLocalizedProductByID(Long productId, Integer languageId) throws SQLException, IOException;
    List<Product> findByCategoryId(Integer languageId, Long categoryId) throws SQLException, IOException;
}
