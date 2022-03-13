package com.epam.online.shop.service.dao.implementation;

import com.epam.online.shop.service.database.connection.ConnectionPool;
import com.epam.online.shop.service.entity.Language;
import com.epam.online.shop.service.dao.interfaces.LanguageDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAOImpl implements LanguageDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String GET_LANGUAGE_ID = "SELECT ID FROM LANGUAGE WHERE LANGUAGE=?";
    private static final String SELECT_ALL_LANGUAGES = "SELECT * FROM LANGUAGE";

    @Override
    public Integer getLanguageId(String language) throws SQLException {
        long languageId = 0L;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LANGUAGE_ID)) {
            preparedStatement.setString(1, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                languageId = resultSet.getLong("ID");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return (int) languageId;
    }

    @Override
    public void create(Language object) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Language> getAll(Integer languageID) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Language> getAll() throws SQLException {
        List<Language> languages = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LANGUAGES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Language language = new Language();
                language.setId(resultSet.getInt("ID"));
                language.setLanguage(resultSet.getString("LANGUAGE"));
                languages.add(language);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return languages;
    }

    @Override
    public Language getById(Long id, Integer languageID) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, Integer languageID, Language language) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
