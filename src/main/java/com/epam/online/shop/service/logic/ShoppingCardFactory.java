package com.epam.online.shop.service.logic;

import com.epam.online.shop.service.dao.EnumDAO;
import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.entity.Product;
import com.epam.online.shop.service.entity.ShoppingCardItem;
import com.epam.online.shop.service.dao.implementation.ProductDAOImpl;
import com.epam.online.shop.service.dao.interfaces.ProductDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ShoppingCardFactory {
    private static ShoppingCardFactory instance = new ShoppingCardFactory();
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCT_DAO);

    private ShoppingCardFactory() {
    }

    public ShoppingCardItem setCartItemParameters(HttpServletRequest request, HttpSession session, Integer idCounter) throws SQLException, IOException {
        ShoppingCardItem shoppingCardItem = new ShoppingCardItem();

        shoppingCardItem.setId(idCounter);

        Integer languageId = (Integer) session.getAttribute("languageId");

        String productId = request.getParameter("productId");

        Product productCart = productDAO.getById(Long.parseLong(productId), languageId);

        shoppingCardItem.setProduct(productCart);

        int defaultAmount = 1;
        shoppingCardItem.setAmount(defaultAmount);

        return shoppingCardItem;
    }

    public static ShoppingCardFactory getInstance() {
        if (instance == null) {
            instance = new ShoppingCardFactory();
        }
        return instance;
    }
}
