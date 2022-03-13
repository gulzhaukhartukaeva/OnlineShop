package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.CategoryDAOImpl;
import com.epam.online.shop.service.dao.interfaces.CategoryDAO;
import com.epam.online.shop.service.entity.Category;
import com.epam.online.shop.service.logic.CategoryFactory;
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

import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class AddCategoryAction implements Action{
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final CategoryFactory categoryFactory = CategoryFactory.getInstance();
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(CATEGORY_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session)) {
            List<Category> categories = categoryFactory.fillCategoriesForCreating(request);

            for (Category category : categories) {
                categoryDAO.create(category);
            }

            List<Category> categoriesList = categoryDAO.getAll(languageId);
            session.setAttribute("categories", categoriesList);

            requestDispatcher = request.getRequestDispatcher(CATEGORY_PAGE);
            requestDispatcher.forward(request, response);
        }
        else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}
