package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.*;
import com.epam.onlineShopService.dao.interfaces.*;
import com.epam.onlineShopService.entity.User;

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

public class LoginAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(EnumDAO.USER_DAO);
    private final LanguageDAO languageDAO = (LanguageDAOImpl) factoryDAO.getDAO(EnumDAO.LANGUAGE_DAO);

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

        String WELCOME_PAGE = "/";
        if (user != null) {
            session.setAttribute("id", user.getId());
            session.setAttribute("email", email);
            session.setAttribute("surname", user.getSurname());
            session.setAttribute("name", user.getName());
            session.setAttribute("birthDate", user.getBirthDate());
            session.setAttribute("phoneNumber", user.getPhoneNumber());
            session.setAttribute("securityQuestion", user.getSecurityQuestion());
            session.setAttribute("answer", user.getAnswer());
            session.setAttribute("role", user.getRole());
            session.setAttribute("roleId", user.getRoleID());
            session.setAttribute("isBanned", user.getIsBanned());

            session.setAttribute("users", users);
            session.setAttribute("languages", languageDAO.getAll());

            response.sendRedirect(WELCOME_PAGE);
        } else {
            //request.setAttribute("errorLogin", Errors.getErrorFromLanguageBundle(request, "error.login"));
            dispatcher = request.getRequestDispatcher(WELCOME_PAGE);
            dispatcher.forward(request, response);
        }
    }
}