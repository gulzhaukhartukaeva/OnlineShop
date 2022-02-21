package com.epam.onlineShopService.logic;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.SizeDAOImpl;
import com.epam.onlineShopService.dao.interfaces.SizeDAO;

public class SizeFactory {
    private static SizeFactory instance = new SizeFactory();
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private SizeDAO sizeDAO = (SizeDAOImpl) factoryDAO.getDAO(EnumDAO.SIZE_DAO);


    private SizeFactory() {
    }

    public static SizeFactory getInstance() {
        if (instance == null) {
            instance = new SizeFactory();
        }
        return instance;
    }
}