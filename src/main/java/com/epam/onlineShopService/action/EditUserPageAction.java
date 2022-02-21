package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.UserDAOImpl;
import com.epam.onlineShopService.dao.interfaces.RoleDAO;
import com.epam.onlineShopService.dao.interfaces.UserDAO;
import com.epam.onlineShopService.entity.Role;
import com.epam.onlineShopService.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditUserPageAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(EnumDAO.USER_DAO);
    private final RoleDAO roleDAO = (RoleDAO) factoryDAO.getDAO(EnumDAO.ROLE_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        HttpSession session = request.getSession(true);
        Integer languageID = (Integer) session.getAttribute("languageId");

        Long userId = Long.parseLong(request.getParameter("id"));

        User userEdit = userDAO.getByID(userId, languageID);
        List<Role> roles = roleDAO.getAll(languageID);

        session.setAttribute("userEdit", userEdit);
        session.setAttribute("roles", roles);

        String EDIT_CATEGORY_PAGE = "/edit_user.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(EDIT_CATEGORY_PAGE);
        requestDispatcher.forward(request, response);
    }
}