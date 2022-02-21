package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.UserDAOImpl;
import com.epam.onlineShopService.dao.interfaces.UserDAO;
import com.epam.onlineShopService.entity.User;
import com.epam.onlineShopService.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class EditUserAction implements Action{

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(EnumDAO.USER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {

        HttpSession session = request.getSession(true);

        if (AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session)) {
            Integer languageID = (Integer) session.getAttribute("languageId");

            User user = new User();

            user.setId(Long.parseLong(request.getParameter("id")));

            user.setRoleID(Integer.parseInt(request.getParameter("userRole")));
            user.setBanned(Boolean.parseBoolean(request.getParameter("banned")));

            userDAO.update(user.getId(), languageID, user);

            List<User> users = userDAO.getAll(languageID);
            session.setAttribute("users", users);

            String USERS_PAGE = "/admin_users_menu.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(USERS_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}