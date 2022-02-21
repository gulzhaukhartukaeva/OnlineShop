package com.epam.onlineShopService.logic;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.OrderDAOImpl;
import com.epam.onlineShopService.dao.implementation.OrderDetailDAOImpl;
import com.epam.onlineShopService.dao.implementation.StatusDAOImpl;
import com.epam.onlineShopService.dao.interfaces.OrderDAO;
import com.epam.onlineShopService.dao.interfaces.OrderDetailDAO;
import com.epam.onlineShopService.dao.interfaces.StatusDAO;
import com.epam.onlineShopService.entity.CartItem;
import com.epam.onlineShopService.entity.Order;
import com.epam.onlineShopService.entity.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailFactory {
    private static OrderDetailFactory instance = new OrderDetailFactory();
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private OrderDetailDAO orderDetailDAO = (OrderDetailDAOImpl) factoryDAO.getDAO(EnumDAO.ORDER_DETAIL_DAO);

    private OrderDetailFactory() {
    }

    public List<OrderDetail> fillOrder(HttpServletRequest request, Long orderId) throws SQLException {
        HttpSession session = request.getSession(true);

        List<OrderDetail> orderDetails = new ArrayList<>();

        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");

        for ( CartItem item: cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            Long generatedID = orderDetailDAO.takeLastID() + 1;
            orderDetail.setId(generatedID);

            orderDetail.setProductId(item.getProduct().getId());
            orderDetail.setUnitPrice(item.getProduct().getPrice());
            orderDetail.setAmount(1);
            orderDetail.setOrderId(orderId);

            orderDetails.add(orderDetail);
        }

        return orderDetails;
    }

    public static OrderDetailFactory getInstance() {
        if (instance == null) {
            instance = new OrderDetailFactory();
        }
        return instance;
    }
}

