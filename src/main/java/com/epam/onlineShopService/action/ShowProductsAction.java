package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.ProductDAOImpl;
import com.epam.onlineShopService.dao.implementation.SizeDAOImpl;
import com.epam.onlineShopService.dao.interfaces.ProductDAO;
import com.epam.onlineShopService.dao.interfaces.SizeDAO;
import com.epam.onlineShopService.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ShowProductsAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCT_DAO);
    private final SizeDAO sizeDAO = (SizeDAOImpl) factoryDAO.getDAO(EnumDAO.SIZE_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        Integer languageId = (Integer) session.getAttribute("languageId");
        List<Product> products = productDAO.getAll(languageId);

        for(Product product:products){
            product.setSize(sizeDAO.getByID(product.getSize().getId(), languageId));
        }

        session.setAttribute("products", products);

        String PRODUCTS_JSP = "/admin_products_menu.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PRODUCTS_JSP);
        requestDispatcher.forward(request, response);
    }
}
