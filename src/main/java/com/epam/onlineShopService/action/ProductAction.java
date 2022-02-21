package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String PRODUCT_PAGE = "/product.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PRODUCT_PAGE);
        requestDispatcher.forward(request, response);
    }
}