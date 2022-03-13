package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.LanguageDAOImpl;
import com.epam.online.shop.service.dao.implementation.UserDAOImpl;
import com.epam.online.shop.service.dao.interfaces.LanguageDAO;
import com.epam.online.shop.service.dao.interfaces.UserDAO;
import com.epam.online.shop.service.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import static com.epam.online.shop.service.constants.ActionConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class LoginAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(USER_DAO);
    private final LanguageDAO languageDAO = (LanguageDAOImpl) factoryDAO.getDAO(LANGUAGE_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            ParseException, SQLException {

        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String securedPassword = DigestUtils.md5Hex(password);
        request.getParameterValues("language");
        Integer languageId = (Integer) session.getAttribute("languageId");

        List<User> users = userDAO.getAll(languageId);
        User user = userDAO.getUserByLoginPassword(email, securedPassword, languageId);

        if (user != null) {
            session.setAttribute("id", user.getId());
            session.setAttribute("email", email);
            session.setAttribute("address", user.getAddress());
            session.setAttribute("roleId", user.getRole().getId());
            session.setAttribute("isBanned", user.getIsBanned());
            session.setAttribute("users", users);
            session.setAttribute("languages", languageDAO.getAll());

            response.sendRedirect(WELCOME_ACTION);
        } else {
            request.setAttribute("notCorrectEmailPassword", "Email or Password not correct!");

            dispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            dispatcher.forward(request, response);
        }
    }
}