package com.epam.online.shop.service.dao;

import com.epam.online.shop.service.dao.implementation.*;
import com.epam.online.shop.service.dao.interfaces.CommonDAO;

import java.util.HashMap;
import java.util.Map;

public class FactoryDAO {
    private static final Map<Enum, CommonDAO> MAP = new HashMap<>();
    private static final FactoryDAO FACTORY_DAO = new FactoryDAO();

    static {
        MAP.put(EnumDAO.USER_DAO, new UserDAOImpl());
        MAP.put(EnumDAO.ORDER_DAO, new OrderDAOImpl());
        MAP.put(EnumDAO.ORDER_DETAIL_DAO, new OrderDetailDAOImpl());
        MAP.put(EnumDAO.CATEGORY_DAO, new CategoryDAOImpl());
        MAP.put(EnumDAO.PRODUCER_DAO, new ProducerDAOImpl());
        MAP.put(EnumDAO.PRODUCT_DAO, new ProductDAOImpl());
        MAP.put(EnumDAO.ROLE_DAO, new RoleDAOImpl());
        MAP.put(EnumDAO.LANGUAGE_DAO, new LanguageDAOImpl());
        MAP.put(EnumDAO.SIZE_DAO, new SizeDAOImpl());
        MAP.put(EnumDAO.STATUS_DAO, new StatusDAOImpl());

    }

    public static FactoryDAO getInstance() {
        return FACTORY_DAO;
    }

    public CommonDAO getDAO(Enum DAOImpl) {
        return MAP.get(DAOImpl);
    }
}
