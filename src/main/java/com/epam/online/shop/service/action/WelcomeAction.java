package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.ProductDAOImpl;
import com.epam.online.shop.service.dao.implementation.SizeDAOImpl;
import com.epam.online.shop.service.dao.interfaces.SizeDAO;
import com.epam.online.shop.service.entity.Product;
import com.epam.online.shop.service.dao.interfaces.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class WelcomeAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(PRODUCT_DAO);
    private final SizeDAO sizeDAO = (SizeDAOImpl) factoryDAO.getDAO(SIZE_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");
        List<Product> products = productDAO.getAll(languageId);

        for (Product product : products) {
            product.setSize(sizeDAO.getById(product.getSize().getId(), languageId));
        }

        session.setAttribute("products", products);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(WELCOME_PAGE);
        requestDispatcher.forward(request, response);
    }
}