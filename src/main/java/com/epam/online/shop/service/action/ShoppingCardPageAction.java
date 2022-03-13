package com.epam.online.shop.service.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.online.shop.service.constants.PageConstants.CART_PAGE;

public class ShoppingCardPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("page", CART_PAGE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CART_PAGE);
        requestDispatcher.forward(request, response);
    }
}