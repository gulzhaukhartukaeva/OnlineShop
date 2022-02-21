package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProducerPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ADD_PRODUCER_PAGE = "/add_producer.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADD_PRODUCER_PAGE);
        requestDispatcher.forward(request, response);
    }
}