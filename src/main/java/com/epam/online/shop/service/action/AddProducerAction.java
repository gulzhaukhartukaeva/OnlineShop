package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.ProducerDAOImpl;
import com.epam.online.shop.service.dao.interfaces.ProducerDAO;
import com.epam.online.shop.service.entity.Producer;
import com.epam.online.shop.service.logic.ProducerFactory;
import com.epam.online.shop.service.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class AddProducerAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProducerFactory producerFactory = ProducerFactory.getInstance();
    private final ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(PRODUCER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session)) {
            List<Producer> producers = producerFactory.fillProducersForCreating(request);
            for (Producer producer : producers) {
                producerDAO.create(producer);
            }
            List<Producer> producersList = producerDAO.getAll(languageId);
            session.setAttribute("producers", producersList);
            requestDispatcher = request.getRequestDispatcher(PRODUCER_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}
