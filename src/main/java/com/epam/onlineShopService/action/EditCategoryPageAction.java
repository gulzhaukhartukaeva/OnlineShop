package com.epam.onlineShopService.action;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.CategoryDAOImpl;
import com.epam.onlineShopService.dao.interfaces.CategoryDAO;
import com.epam.onlineShopService.entity.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditCategoryPageAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        HttpSession session = request.getSession(true);

        Long categoryId = Long.parseLong(request.getParameter("categoryId"));

        List<Category> categoriesLocalized = categoryDAO.takeAllLocalizedCategoryByID(categoryId);

        session.setAttribute("categoriesLocalized", categoriesLocalized);

        String EDIT_CATEGORY_PAGE = "/edit_category.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(EDIT_CATEGORY_PAGE);
        requestDispatcher.forward(request, response);
    }
}