package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.Order;
import com.epam.onlineShopService.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CommonDAO<OrderDetail>{
    Long takeLastID() throws SQLException;
}
