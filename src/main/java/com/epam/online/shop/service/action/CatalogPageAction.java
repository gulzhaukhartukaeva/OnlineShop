package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.CategoryDAOImpl;
import com.epam.online.shop.service.dao.interfaces.CategoryDAO;
import com.epam.online.shop.service.entity.Category;

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

public class CatalogPageAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(CATEGORY_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");
        List<Category> categories = categoryDAO.getAll(languageId);

        session.setAttribute("categories", categories);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATALOG_PAGE);
        requestDispatcher.forward(request, response);
    }
}
