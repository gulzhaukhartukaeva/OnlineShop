package com.epam.onlineShopService.logic;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.CategoryDAOImpl;
import com.epam.onlineShopService.dao.implementation.OrderDAOImpl;
import com.epam.onlineShopService.dao.implementation.SizeDAOImpl;
import com.epam.onlineShopService.dao.implementation.StatusDAOImpl;
import com.epam.onlineShopService.dao.interfaces.CategoryDAO;
import com.epam.onlineShopService.dao.interfaces.OrderDAO;
import com.epam.onlineShopService.dao.interfaces.SizeDAO;
import com.epam.onlineShopService.dao.interfaces.StatusDAO;
import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

public class OrderFactory {
    private static OrderFactory instance = new OrderFactory();
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private StatusDAO statusDAO = (StatusDAOImpl) factoryDAO.getDAO(EnumDAO.STATUS_DAO);
    private OrderDAO orderDAO = (OrderDAOImpl) factoryDAO.getDAO(EnumDAO.ORDER_DAO);

    private OrderFactory() {
    }

    public Order fillOrder(HttpServletRequest request) throws SQLException {
        Order order = new Order();

        Long generatedID = orderDAO.takeLastID() + 1;
        order.setId(generatedID);

        Date date = new Date();
        order.setOrderDate(date);
        order.setUserId(Integer.parseInt(request.getParameter("userId")));
        order.setStatusId(2); //???
        order.setTotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
        order.setDeliveryAddress(request.getParameter("deliveryAddress"));
        return order;
    }

    public static OrderFactory getInstance() {
        if (instance == null) {
            instance = new OrderFactory();
        }
        return instance;
    }
}
