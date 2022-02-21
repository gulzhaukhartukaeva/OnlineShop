package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String SIGN_UP_PAGE = "/signup.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGN_UP_PAGE);
        requestDispatcher.forward(request, response);
    }
}