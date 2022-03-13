package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.OrderDAOImpl;
import com.epam.online.shop.service.dao.implementation.StatusDAOImpl;
import com.epam.online.shop.service.dao.interfaces.OrderDAO;
import com.epam.online.shop.service.dao.interfaces.StatusDAO;
import com.epam.online.shop.service.entity.Order;
import com.epam.online.shop.service.entity.Status;
import com.epam.online.shop.service.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class EditOrderAction implements Action{
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final OrderDAO orderDAO = (OrderDAOImpl) factoryDAO.getDAO(ORDER_DAO);
    private final StatusDAO statusSAO = (StatusDAOImpl) factoryDAO.getDAO(STATUS_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session)) {
            Integer languageId = (Integer) session.getAttribute("languageId");

            Order order = new Order();
            order.setId(Long.parseLong(request.getParameter("orderId")));

            Status status = new Status();
            status.setId(Long.parseLong(request.getParameter("status")));
            order.setStatus(status);

            orderDAO.update(order.getId(), languageId, order);

            List<Order> updatedOrders = orderDAO.getAll(languageId);
            for (Order updatedOrder : updatedOrders) {
                updatedOrder.setStatus(statusSAO.getById(updatedOrder.getStatus().getId(), languageId));
            }

            session.setAttribute("orders", updatedOrders);

            requestDispatcher = request.getRequestDispatcher(ORDER_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}