package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.UserDAOImpl;
import com.epam.online.shop.service.dao.interfaces.UserDAO;
import com.epam.online.shop.service.entity.User;
import com.epam.online.shop.service.logic.UserFactory;
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
import static com.epam.online.shop.service.validator.AuthorizationValidator.*;

public class SignUpAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserFactory userFactory = UserFactory.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(USER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            ParseException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;

        if (!AccessValidator.checkAccess(USER_ROLE_ID, session) ||
                !AccessValidator.checkAccess(ADMIN_ROLE_ID, session)) {
            if (userDAO.isEmailExist(request.getParameter("email"))) {
                request.setAttribute("emailExist", "Email exists");
                dispatcher = request.getRequestDispatcher(SIGN_UP_PAGE);
                dispatcher.forward(request, response);
            } else if (!validatePhoneNumber(request.getParameter("phoneNumber"))) {
                request.setAttribute("notCorrectPhone", "Phone format error");
                dispatcher = request.getRequestDispatcher(SIGN_UP_PAGE);
                dispatcher.forward(request, response);
            } else if (!validatePasswordRegex(request.getParameter("password"))) {
                request.setAttribute("notCorrectPassword", "Password format error");
                dispatcher = request.getRequestDispatcher(SIGN_UP_PAGE);
                dispatcher.forward(request, response);
            } else {
                User newUser = userFactory.fillUser(request);
                userDAO.create(newUser);
                session.setAttribute("id", newUser.getId());
                session.setAttribute("role", newUser.getRole().getId());
                session.setAttribute("email", newUser);
                session.setAttribute("role_id", newUser.getRole().getId());

                dispatcher = request.getRequestDispatcher(SIGN_UP_SUCCESS);
                dispatcher.forward(request, response);
            }
        } else {
            dispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            dispatcher.forward(request, response);
        }
    }
}