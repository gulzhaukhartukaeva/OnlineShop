package com.epam.online.shop.service.logic;

import com.epam.online.shop.service.constants.GeneralConstants;
import com.epam.online.shop.service.dao.EnumDAO;
import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.OrderDAOImpl;
import com.epam.online.shop.service.entity.Order;
import com.epam.online.shop.service.dao.interfaces.OrderDAO;
import com.epam.online.shop.service.entity.Status;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

public class OrderFactory {
    private static OrderFactory instance = new OrderFactory();
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final OrderDAO orderDAO = (OrderDAOImpl) factoryDAO.getDAO(EnumDAO.ORDER_DAO);

    private OrderFactory() {
    }

    public Order fillOrder(HttpServletRequest request) throws SQLException {
        Order order = new Order();

        Long generatedId = orderDAO.takeLastId() + 1;
        order.setId(generatedId);

        Date date = new Date();
        order.setOrderDate(date);
        order.setUserId(Integer.parseInt(request.getParameter("userId")));

        Status status = new Status();
        status.setId(GeneralConstants.STATUS_SEND);
        order.setStatus(status);

        order.setTotalPrice(Double.parseDouble(request.getParameter("totalPriceCard")));
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
