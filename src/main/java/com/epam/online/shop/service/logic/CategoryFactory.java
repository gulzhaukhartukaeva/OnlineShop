package com.epam.online.shop.service.logic;

import com.epam.online.shop.service.dao.EnumDAO;
import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.entity.Category;
import com.epam.online.shop.service.dao.implementation.CategoryDAOImpl;
import com.epam.online.shop.service.dao.interfaces.CategoryDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryFactory {
    private static CategoryFactory instance = new CategoryFactory();
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final CategoryDAO categoryDAO = (CategoryDAOImpl) factoryDAO.getDAO(EnumDAO.CATEGORY_DAO);

    private CategoryFactory() {
    }

    public List<Category> fillCategoriesForCreating(HttpServletRequest request) throws SQLException {
        List<Category> categories = fillCategoriesForUpdating(request);
        Long generatedID = categoryDAO.takeLastId() + 1;
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