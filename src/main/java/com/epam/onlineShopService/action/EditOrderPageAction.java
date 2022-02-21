package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditOrderPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String EDIT_CATEGORY_PAGE = "/edit_order.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(EDIT_CATEGORY_PAGE);
        requestDispatcher.forward(request, response);
    }
}