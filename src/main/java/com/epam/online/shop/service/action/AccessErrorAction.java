package com.epam.online.shop.service.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.online.shop.service.constants.ActionConstants.*;

public class AccessErrorAction  implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_PAGE);
        requestDispatcher.forward(request, response);
    }
}