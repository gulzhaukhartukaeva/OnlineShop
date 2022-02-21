package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.CategoryDAOImpl;
import com.epam.onlineShopService.dao.interfaces.CategoryDAO;
import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.logic.CategoryFactory;
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

public class AddCategoryAction implements Action{

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final CategoryFactory categoryFactory = CategoryFactory.getInstance();
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        Integer languageId = (Integer) session.getAttribute("languageId");

        if (AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session)) {
            List<Category> categories = categoryFactory.fillCategoriesForCreating(request);
            for (Category category : categories) {
                categoryDAO.create(category);
            }

            List<Category> categoriesList = categoryDAO.getAll(languageId);
            session.setAttribute("categories", categoriesList);

            String CATEGORY_PAGE = "/admin_categories_menu.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
