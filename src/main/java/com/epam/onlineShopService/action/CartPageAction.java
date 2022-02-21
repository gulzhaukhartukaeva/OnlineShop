package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String CART_PAGE = "/cart.jsp";
        request.setAttribute("page", CART_PAGE);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CART_PAGE);
        requestDispatcher.forward(request, response);
    }
}