package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.ProductDAOImpl;
import com.epam.onlineShopService.dao.interfaces.ProductDAO;
import com.epam.onlineShopService.entity.Product;
import com.epam.onlineShopService.logic.ProductFactory;
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

public class EditProductAction implements Action{

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProductFactory productFactory = ProductFactory.getInstance();
    private final ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCT_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");

        if (AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session)) {

            Long productId = Long.parseLong(request.getParameter("productId"));

            List<Product> products = productFactory.fillProductsForUpdating(request, languageId);

            for (Product product : products) {
                productDAO.update(productId, product.getLanguageId(), product);
            }

            List<Product> productList = productDAO.getAll(languageId);

            session.setAttribute("products", productList);

            String CATEGORY_PAGE = "/admin_products_menu.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
