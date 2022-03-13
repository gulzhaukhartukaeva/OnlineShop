package com.epam.online.shop.service.dao.interfaces;

import com.epam.online.shop.service.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CommonDAO<OrderDetail>{
    Long takeLastId() throws SQLException;
}
