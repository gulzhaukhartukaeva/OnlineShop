package com.epam.online.shop.service.controller;

import com.epam.online.shop.service.logic.ServiceFactory;
import com.epam.online.shop.service.action.Action;
import org.apache.logging.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OnlineShopController extends HttpServlet {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();

    public OnlineShopController()
    {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestString = request.getRequestURI();

        Action action = SERVICE_FACTORY.getAction(requestString);

        try {
            action.execute(request, response);
        } catch (Exception e)
        {
            LOGGER.error(e);
        }
    }
}
