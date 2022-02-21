package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String LOGIN_PAGE = "/login.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        requestDispatcher.forward(request, response);
    }
}
