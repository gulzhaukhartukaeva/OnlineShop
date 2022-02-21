package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategoryPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ADD_CATEGORY_PAGE = "/add_category.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADD_CATEGORY_PAGE);
        requestDispatcher.forward(request, response);
    }
}