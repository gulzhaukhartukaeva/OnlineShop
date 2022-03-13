package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.UserDAOImpl;
import com.epam.online.shop.service.dao.interfaces.RoleDAO;
import com.epam.online.shop.service.dao.interfaces.UserDAO;
import com.epam.online.shop.service.entity.Role;
import com.epam.online.shop.service.entity.User;
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

public class EditUserPageAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(USER_DAO);
    private final RoleDAO roleDAO = (RoleDAO) factoryDAO.getDAO(ROLE_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session)) {
            Integer languageId = (Integer) session.getAttribute("languageId");

            Long userId = Long.parseLong(request.getParameter("id"));
            User userEdit = userDAO.getById(userId, languageId);
            List<Role> roles = roleDAO.getAll(languageId);

            session.setAttribute("userEdit", userEdit);
            session.setAttribute("roles", roles);

            requestDispatcher = request.getRequestDispatcher(EDIT_USER_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}