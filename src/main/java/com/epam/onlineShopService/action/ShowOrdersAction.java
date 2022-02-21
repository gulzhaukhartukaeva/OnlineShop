package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.OrderDAOImpl;
import com.epam.onlineShopService.dao.interfaces.OrderDAO;
import com.epam.onlineShopService.entity.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ShowOrdersAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final OrderDAO orderDAO = (OrderDAOImpl) factoryDAO.getDAO(EnumDAO.ORDER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        Integer languageId = (Integer) session.getAttribute("languageId");
        List<Order> orders = orderDAO.getAll(languageId);

        session.setAttribute("orders", orders);

        String ORDERS_JSP = "/admin_orders_menu.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ORDERS_JSP);
        requestDispatcher.forward(request, response);
    }
}
