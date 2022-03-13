package com.epam.online.shop.service.logic;

import com.epam.online.shop.service.dao.EnumDAO;
import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.entity.Producer;
import com.epam.online.shop.service.dao.implementation.ProducerDAOImpl;
import com.epam.online.shop.service.dao.interfaces.ProducerDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerFactory {
    private static ProducerFactory instance = new ProducerFactory();
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCER_DAO);

    private ProducerFactory() {
    }

    public List<Producer> fillProducersForCreating(HttpServletRequest request) throws SQLException {
        List<Producer> producers = fillProducersForUpdating(request);
        Long generatedID = producerDAO.takeLastId() + 1;
        for (Producer producer : producers) {
            producer.setId(generatedID);
        }
        return producers;
    }

    public List<Producer> fillProducersForUpdating(HttpServletRequest request) {
        List<Producer> producers = new ArrayList<>();
        String[] producerNames = request.getParameterValues("name");
        String[] languageIds = request.getParameterValues("languageId");

        for (int i = 0; i < producerNames.length; i++) {
            Producer producer = new Producer();
            if (request.getParameterValues("producerId") != null) {
                producer.setId(Long.parseLong(request.getParameterValues("producerId")[i]));
            }
            producer.setLanguageId(Integer.parseInt(languageIds[i]));
            producer.setName(producerNames[i]);
            producers.add(producer);
        }
        return producers;
    }

    public static ProducerFactory getInstance() {
        if (instance == null) {
            instance = new ProducerFactory();
        }
        return instance;
    }
}
