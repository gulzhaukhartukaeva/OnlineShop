package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.UserDAOImpl;
import com.epam.onlineShopService.dao.interfaces.UserDAO;
import com.epam.onlineShopService.entity.User;
import com.epam.onlineShopService.logic.UserFactory;
import com.epam.onlineShopService.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class SignUpAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserFactory userFactory = UserFactory.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(EnumDAO.USER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            ParseException, SQLException {

        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;

        if (!AccessValidator.checkAccess(GeneralConstants.USER_ROLE_ID, session) ||
                !AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session)) {
                User newUser = userFactory.fillUser(request);
                userDAO.create(newUser);
                session.setAttribute("id", newUser.getId());
                session.setAttribute("role", newUser.getRole());
                session.setAttribute("email", newUser);
                session.setAttribute("role_id", newUser.getRoleID());

            String SIGN_UP_SUCCESS = "/signup_success.jsp";
            dispatcher = request.getRequestDispatcher(SIGN_UP_SUCCESS);
                dispatcher.forward(request, response);
        } else {
            dispatcher = request.getRequestDispatcher("/access_error.jsp");
            dispatcher.forward(request, response);
        }
    }
}