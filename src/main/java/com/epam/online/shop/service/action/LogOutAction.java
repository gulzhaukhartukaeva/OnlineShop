package com.epam.online.shop.service.action;

import com.epam.online.shop.service.constants.ActionConstants;
import com.epam.online.shop.service.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.online.shop.service.constants.ActionConstants.*;
import static com.epam.online.shop.service.constants.GeneralConstants.*;

public class LogOutAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session) ||
                AccessValidator.checkAccess(USER_ROLE_ID, session)) {
            request.getSession().invalidate();
            response.sendRedirect(ActionConstants.WELCOME_ACTION);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}