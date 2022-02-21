package com.epam.onlineShopService.action;

import com.epam.onlineShopService.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class LogOutAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;

        if (AccessValidator.checkAccess(2, session) || AccessValidator.checkAccess(1, session) ||
                AccessValidator.checkAccess(3, session)) {
            request.getSession().invalidate();
            String WELCOME_PAGE = "/";
            response.sendRedirect(WELCOME_PAGE);
        } else {
            String ACCESS_ERROR_PAGE = "/access_error";
            dispatcher = request.getRequestDispatcher(ACCESS_ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}