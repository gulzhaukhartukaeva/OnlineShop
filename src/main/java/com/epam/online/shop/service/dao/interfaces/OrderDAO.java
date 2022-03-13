package com.epam.online.shop.service.dao.interfaces;

import com.epam.online.shop.service.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CommonDAO<Order> {
    Long takeLastId() throws SQLException;
}