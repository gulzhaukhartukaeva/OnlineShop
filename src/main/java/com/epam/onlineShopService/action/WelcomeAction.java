package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.ProductDAOImpl;
import com.epam.onlineShopService.dao.interfaces.ProductDAO;
import com.epam.onlineShopService.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class WelcomeAction implements Action {

    private final static String WELCOME_PAGE = "/welcome.jsp";

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCT_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        HttpSession session = request.getSession(true);

        Integer languageId = (Integer) session.getAttribute("languageId");
        List<Product> products = productDAO.getAll(languageId);

        session.setAttribute("products", products);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(WELCOME_PAGE);
        requestDispatcher.forward(request, response);
    }
}