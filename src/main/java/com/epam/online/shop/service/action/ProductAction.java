package com.epam.online.shop.service.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.online.shop.service.constants.PageConstants.*;

public class ProductAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PRODUCT_DETAIL_PAGE);
        requestDispatcher.forward(request, response);
    }
}