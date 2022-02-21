package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.UserDAOImpl;
import com.epam.onlineShopService.dao.interfaces.UserDAO;
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

public class ShowUsersAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(EnumDAO.USER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        Integer languageID = (Integer) session.getAttribute("languageId");
        List<User> users = userDAO.getAll(languageID);

        session.setAttribute("users", users);

        String USERS_JSP = "/admin_users_menu.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(USERS_JSP);
        requestDispatcher.forward(request, response);
    }
}
