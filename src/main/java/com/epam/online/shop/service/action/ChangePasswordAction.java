package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.UserDAOImpl;
import com.epam.online.shop.service.dao.interfaces.UserDAO;
import com.epam.online.shop.service.entity.User;
import com.epam.online.shop.service.validator.AccessValidator;
import org.apache.commons.codec.digest.DigestUtils;

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

public class ChangePasswordAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(USER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcherDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session) ||
                AccessValidator.checkAccess(USER_ROLE_ID, session)){
            User user = new User();
            user.setId(Long.parseLong(request.getParameter("userId")));

            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            String newSecuredPassword = DigestUtils.md5Hex(newPassword);

            if (newPassword != null && newPassword != "") {
                if (!newPassword.equals(confirmPassword)) {
                    request.setAttribute("passwordNotEqual", "passwordNotEqual");
                    dispatcherDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
                    dispatcherDispatcher.forward(request, response);
                } else {
                    userDAO.updatePassword(newSecuredPassword, user.getId());
                }
            }
            dispatcherDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
            dispatcherDispatcher.forward(request, response);
        } else {
            dispatcherDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            dispatcherDispatcher.forward(request, response);
        }
    }
}