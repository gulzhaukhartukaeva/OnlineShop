package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.CategoryDAOImpl;
import com.epam.onlineShopService.dao.implementation.ProducerDAOImpl;
import com.epam.onlineShopService.dao.implementation.ProductDAOImpl;
import com.epam.onlineShopService.dao.implementation.SizeDAOImpl;
import com.epam.onlineShopService.dao.interfaces.CategoryDAO;
import com.epam.onlineShopService.dao.interfaces.ProducerDAO;
import com.epam.onlineShopService.dao.interfaces.ProductDAO;
import com.epam.onlineShopService.dao.interfaces.SizeDAO;
import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Producer;
import com.epam.onlineShopService.entity.Product;
import com.epam.onlineShopService.entity.Size;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditProductPageAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProductDAO productDAO = (ProductDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCT_DAO);
    private final ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(EnumDAO.PRODUCER_DAO);
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);
    private final SizeDAO sizeDAO = (SizeDAOImpl) factoryDAO.getDAO(EnumDAO.SIZE_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");

        Long productId = Long.parseLong(request.getParameter("productId"));

        List<Product> productsLocalized = productDAO.takeAllLocalizedProductByID(productId, languageId);
        List<Producer> producers = producerDAO.getAll( languageId);
        List<Category> categories = categoryDAO.getAll( languageId);
        List<Size> sizes = sizeDAO.getAll( languageId);

        Product product = productDAO.getByID(productId, languageId);

        session.setAttribute("productsLocalized", productsLocalized);
        session.setAttribute("product", product);
        session.setAttribute("producers", producers);
        session.setAttribute("categories", categories);
        session.setAttribute("sizes", sizes);

        String EDIT_PRODUCT_PAGE = "/edit_product.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(EDIT_PRODUCT_PAGE);
        requestDispatcher.forward(request, response);
    }
}