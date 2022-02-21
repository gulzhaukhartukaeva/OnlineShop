package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;

        if (request.getParameter("checkout") != null) {
            if (AccessValidator.checkAccess(GeneralConstants.USER_ROLE_ID, session)){
                String ORDER_PAGE = "/order.jsp";
                dispatcher = request.getRequestDispatcher(ORDER_PAGE);
                dispatcher.forward(request, response);
            }
            else {
                String LOGIN_PAGE = "/login.jsp";
                dispatcher = request.getRequestDispatcher(LOGIN_PAGE);
                dispatcher.forward(request, response);
            }
        }
    }
}
