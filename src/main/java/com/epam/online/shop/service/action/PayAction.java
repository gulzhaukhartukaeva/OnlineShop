package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.OrderDAOImpl;
import com.epam.online.shop.service.dao.implementation.OrderDetailDAOImpl;
import com.epam.online.shop.service.dao.interfaces.OrderDAO;
import com.epam.online.shop.service.dao.interfaces.OrderDetailDAO;
import com.epam.online.shop.service.entity.Order;
import com.epam.online.shop.service.entity.OrderDetail;
import com.epam.online.shop.service.entity.ShoppingCardItem;
import com.epam.online.shop.service.logic.OrderFactory;
import com.epam.online.shop.service.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class PayAction implements Action{
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final OrderFactory orderFactory = OrderFactory.getInstance();
    private final OrderDAO orderDAO = (OrderDAOImpl) factoryDAO.getDAO(ORDER_DAO);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAOImpl) factoryDAO.getDAO(ORDER_DETAIL_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session) ||
                AccessValidator.checkAccess(USER_ROLE_ID, session)) {
            Order order = orderFactory.fillOrder(request);
            orderDAO.create(order);

            ArrayList<ShoppingCardItem> cartItems = (ArrayList<ShoppingCardItem>) session.getAttribute("cartItems");

            for ( ShoppingCardItem item: cartItems) {
                OrderDetail orderDetail = new OrderDetail();
                Long generatedId = orderDetailDAO.takeLastId() + 1;
                orderDetail.setId(generatedId);
                orderDetail.setProductId(item.getProduct().getId());
                orderDetail.setUnitPrice(item.getProduct().getPrice());
                orderDetail.setAmount(item.getAmount());
                orderDetail.setOrderId(order.getId());
                orderDetailDAO.create(orderDetail);
            }

            requestDispatcher = request.getRequestDispatcher(ORDER_SUCCESS_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}
