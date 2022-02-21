package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.ProducerDAOImpl;
import com.epam.onlineShopService.dao.interfaces.ProducerDAO;
import com.epam.onlineShopService.entity.Producer;
import com.epam.onlineShopService.logic.ProducerFactory;
import com.epam.onlineShopService.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class AddProducerAction implements Action{

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProducerFactory producerFactory = ProducerFactory.getInstance();
    private final ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");

        if (AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session)) {
            List<Producer> producers = producerFactory.fillProducersForCreating(request);
            for (Producer producer : producers) {
                producerDAO.create(producer);
            }
            List<Producer> producersList = producerDAO.getAll(languageId);

            session.setAttribute("producers", producersList);

            String PRODUCER_PAGE = "/admin_producers_menu.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PRODUCER_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
