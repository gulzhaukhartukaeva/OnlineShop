package com.epam.online.shop.service.action;

import com.epam.online.shop.service.constants.GeneralConstants;
import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.ProductDAOImpl;
import com.epam.online.shop.service.entity.Product;
import com.epam.online.shop.service.logic.ProductFactory;
import com.epam.online.shop.service.dao.interfaces.ProductDAO;
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

import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class EditProductAction implements Action{
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProductFactory productFactory = ProductFactory.getInstance();
    private final ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(PRODUCT_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session)) {
            Long productId = Long.parseLong(request.getParameter("productId"));
            List<Product> products = productFactory.fillProductsForUpdating(request, languageId);

            for (Product product : products) {
                productDAO.update(productId, product.getLanguageId(), product);
            }

            List<Product> productList = productDAO.getAll(languageId);
            session.setAttribute("products", productList);

            requestDispatcher = request.getRequestDispatcher(PRODUCT_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}
