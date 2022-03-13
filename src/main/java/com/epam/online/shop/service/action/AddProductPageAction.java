package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.CategoryDAOImpl;
import com.epam.online.shop.service.dao.implementation.ProducerDAOImpl;
import com.epam.online.shop.service.dao.implementation.SizeDAOImpl;
import com.epam.online.shop.service.dao.interfaces.CategoryDAO;
import com.epam.online.shop.service.dao.interfaces.ProducerDAO;
import com.epam.online.shop.service.dao.interfaces.SizeDAO;
import com.epam.online.shop.service.entity.Category;
import com.epam.online.shop.service.entity.Producer;
import com.epam.online.shop.service.entity.Size;
import com.epam.online.shop.service.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class AddProductPageAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final ProducerDAO producerDAO = (ProducerDAOImpl) factoryDAO.getDAO(PRODUCER_DAO);
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(CATEGORY_DAO);
    private final SizeDAO sizeDAO = (SizeDAOImpl) factoryDAO.getDAO(SIZE_DAO);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session)) {
            List<Producer> producers = producerDAO.getAll(languageId);
            List<Category> categories = categoryDAO.getAll(languageId);
            List<Size> sizes = sizeDAO.getAll(languageId);

            session.setAttribute("producers", producers);
            session.setAttribute("categories", categories);
            session.setAttribute("sizes", sizes);

            requestDispatcher = request.getRequestDispatcher(ADD_PRODUCT_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}