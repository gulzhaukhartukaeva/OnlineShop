package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CommonDAO<Order> {
    Long takeLastID() throws SQLException;
}