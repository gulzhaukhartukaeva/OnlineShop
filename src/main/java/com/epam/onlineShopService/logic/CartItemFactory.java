package com.epam.onlineShopService.logic;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.ProductDAOImpl;
import com.epam.onlineShopService.dao.interfaces.ProductDAO;
import com.epam.onlineShopService.entity.CartItem;
import com.epam.onlineShopService.entity.Producer;
import com.epam.onlineShopService.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CartItemFactory {
    private static CartItemFactory instance = new CartItemFactory();
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCT_DAO);

    private CartItemFactory() {
    }

    public CartItem setCartItemParameters(HttpServletRequest request, HttpSession session, Integer itemsCounter) throws SQLException, IOException {
        CartItem cartItem = new CartItem();

        Integer languageId = (Integer) session.getAttribute("languageId");

        cartItem.setId(itemsCounter);

        String productId = request.getParameter("productId");

        Product productCart = productDAO.getByID(Long.parseLong(productId), languageId);

        cartItem.setProduct(productCart);

        Integer amount = 1;
        cartItem.setAmount(amount);

        return cartItem;
    }

    public static CartItemFactory getInstance() {
        if (instance == null) {
            instance = new CartItemFactory();
        }
        return instance;
    }
}
