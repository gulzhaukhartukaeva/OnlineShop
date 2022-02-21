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
import java.util.List;

public class EditProducerPageAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        HttpSession session = request.getSession(true);

        Long producerId = Long.parseLong(request.getParameter("producerId"));

        List<Producer> producersLocalized = producerDAO.takeAllLocalizedProducerByID(producerId);

        session.setAttribute("producersLocalized", producersLocalized);

        String EDIT_PRODUCER_PAGE = "/edit_producer.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(EDIT_PRODUCER_PAGE);
        requestDispatcher.forward(request, response);
    }
}