package com.epam.online.shop.service.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.online.shop.service.constants.PageConstants.SIGN_UP_SUCCESS;


public class SignUpSuccessAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGN_UP_SUCCESS);
        requestDispatcher.forward(request, response);
    }
}