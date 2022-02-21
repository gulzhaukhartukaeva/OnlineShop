package com.epam.onlineShopService.logic;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.CategoryDAOImpl;
import com.epam.onlineShopService.dao.interfaces.CategoryDAO;
import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Producer;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryFactory {
    private static CategoryFactory instance = new CategoryFactory();
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);

    private CategoryFactory() {
    }

    public Category fillCategory(HttpServletRequest request) {
        Category category = new Category();
        category.setName(request.getParameter("name"));
        return category;
    }

    public List<Category> fillCategoriesForCreating(HttpServletRequest request) throws SQLException {
        List<Category> categories = fillCategoriesForUpdating(request);
        Long generatedID = categoryDAO.takeLastID() + 1;
        for (Category category : categories) {
            category.setId(generatedID);
        }
        return categories;
    }

    public List<Category> fillCategoriesForUpdating(HttpServletRequest request) {
        List<Category> categories = new ArrayList<>();
        String[] categoryNames = request.getParameterValues("name");
        String[] languageIds = request.getParameterValues("languageId");

        for (int i = 0; i < categoryNames.length; i++) {
            Category category = new Category();
            if (request.getParameterValues("categoryId") != null) {
                category.setId(Long.parseLong(request.getParameterValues("categoryId")[i]));
            }
            category.setLanguageId(Integer.parseInt(languageIds[i]));
            category.setName(categoryNames[i]);
            categories.add(category);
        }
        return categories;
    }

    public static CategoryFactory getInstance() {
        if (instance == null) {
            instance = new CategoryFactory();
        }
        return instance;
    }
}