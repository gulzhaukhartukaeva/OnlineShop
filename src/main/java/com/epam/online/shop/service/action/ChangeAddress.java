package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.UserDAOImpl;
import com.epam.online.shop.service.dao.interfaces.UserDAO;
import com.epam.online.shop.service.entity.User;
import com.epam.online.shop.service.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class ChangeAddress implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(USER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcherDispatcher;

        if (AccessValidator.checkAccess(USER_ROLE_ID, session)){
            User user = new User();
            user.setId(Long.parseLong(request.getParameter("userId")));
            user.setAddress(request.getParameter("newAddress"));

            String newAddress = request.getParameter("newAddress");

            if (newAddress != null) {
                userDAO.updateAddress(newAddress, user.getId());
            }

            session.setAttribute("address", user.getAddress());

            dispatcherDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
            dispatcherDispatcher.forward(request, response);
        } else {
            dispatcherDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            dispatcherDispatcher.forward(request, response);
        }
    }
}