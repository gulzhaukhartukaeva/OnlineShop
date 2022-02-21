package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.ProducerDAOImpl;
import com.epam.onlineShopService.dao.interfaces.ProducerDAO;
import com.epam.onlineShopService.entity.Producer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ShowProducersAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        Integer languageID = (Integer) session.getAttribute("languageId");
        List<Producer> producers = producerDAO.getAll(languageID);

        session.setAttribute("producers", producers);

        String PRODUCERS_JSP = "/admin_producers_menu.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PRODUCERS_JSP);
        requestDispatcher.forward(request, response);
    }
}
