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
import java.util.List;

import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class EditOrderPageAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final OrderDAO orderDAO = (OrderDAOImpl) factoryDAO.getDAO(ORDER_DAO);
    private final StatusDAO statusDAO = (StatusDAOImpl) factoryDAO.getDAO(STATUS_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session)) {
            Integer languageId = (Integer) session.getAttribute("languageId");

            Long orderId = Long.parseLong(request.getParameter("id"));
            Order orderEdit = orderDAO.getById(orderId, languageId);
            List<Status> statuses = statusDAO.getAll(languageId);

            session.setAttribute("orderEdit", orderEdit);
            session.setAttribute("statuses", statuses);

            requestDispatcher = request.getRequestDispatcher(EDIT_ORDER_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}