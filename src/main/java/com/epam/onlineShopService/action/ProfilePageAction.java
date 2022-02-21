package com.epam.onlineShopService.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ProfilePageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");

        Long userId = Long.parseLong(request.getParameter("userId"));

        session.setAttribute("userId", userId);
        session.setAttribute("languageId", languageId);

        String PROFILE_PAGE = "/profile.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
        requestDispatcher.forward(request, response);
    }
}
