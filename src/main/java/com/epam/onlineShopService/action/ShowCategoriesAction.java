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
import java.text.ParseException;
import java.util.List;

public class ShowCategoriesAction implements Action {

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        Integer languageId = (Integer) session.getAttribute("languageId");
        List<Category> categories = categoryDAO.getAll(languageId);

        session.setAttribute("categories", categories);

        String CATEGORIES_JSP = "/admin_categories_menu.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORIES_JSP);
        requestDispatcher.forward(request, response);
    }
}