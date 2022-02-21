package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.interfaces.CategoryDAO;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.Category;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORY WHERE LANGUAGE_ID = ?";
    private static final String ADD_CATEGORY = "INSERT INTO CATEGORY (NAME, LANGUAGE_ID, ID) VALUES (?, ?, ?)";
    private static final String SELECT_LAST_ID_FROM_CATEGORY = "SELECT MAX(ID) FROM CATEGORY";
    private static final String SELECT_ALL_LOCALIZED_CATEGORIES_BY_ID = "SELECT * FROM CATEGORY WHERE ID = ?";
    private static final String UPDATE_CATEGORY = "UPDATE CATEGORY SET NAME = ? WHERE ID = ? AND LANGUAGE_ID = ?";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT ID, NAME, LANGUAGE_ID FROM CATEGORY WHERE ID = ? AND LANGUAGE_ID = ?";

    @Override
    public void create(Category category) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, category.getLanguageId());
            preparedStatement.setLong(3, category.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Category> getAll(Integer languageId) throws SQLException, IOException {
        List<Category> categories = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)) {
            preparedStatement.setInt(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                setParametersToCategory(category, resultSet);
                categories.add(category);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return categories;
    }

    private void setParametersToCategory(Category category, ResultSet resultSet) throws SQLException, IOException{
        category.setId(resultSet.getLong("ID"));
        category.setName(resultSet.getString("NAME"));
        category.setLanguageId(resultSet.getInt("LANGUAGE_ID"));
    }

    @Override
    public Category getByID(Long id, Integer languageId) throws SQLException, IOException {
        Category category = new Category() ;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToCategory(category, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return category;
    }

    @Override
    public void update(Long id, Integer languageId, Category category) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, id);
            preparedStatement.setLong(3, languageId);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Long takeLastID() throws SQLException {
        long lastID = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_ID_FROM_CATEGORY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lastID = resultSet.getLong("MAX(ID)");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return lastID;
    }

    @Override
    public List<Category> takeAllLocalizedCategoryByID(Long categoryId) throws SQLException, IOException {
        List<Category> categories = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOCALIZED_CATEGORIES_BY_ID)) {
            preparedStatement.setLong(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToCategoryList(categories, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return categories;
    }

    private void setParametersToCategoryList(List<Category> categories, ResultSet resultSet) throws SQLException, IOException {
        Category category = new Category();
        setParametersToCategory(category, resultSet);
        categories.add(category);
    }
}
