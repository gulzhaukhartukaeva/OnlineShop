package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessErrorAction  implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ACCESS_ERROR_PAGE = "/access_error.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_PAGE);
        requestDispatcher.forward(request, response);
    }
}