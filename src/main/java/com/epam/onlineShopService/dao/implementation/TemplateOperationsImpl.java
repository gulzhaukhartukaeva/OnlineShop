package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.database.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateOperationsImpl {
    private static TemplateOperationsImpl instance = new TemplateOperationsImpl();

    private TemplateOperationsImpl(){}

    private ConnectionPool connectionPool;
    private Connection connection;

    Long extractIDByName(String name, String SQLQuery) throws SQLException {
        long ID = 0L;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ID = resultSet.getLong("ID");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return ID;
    }

    public static TemplateOperationsImpl getInstance(){
        if (instance == null) {
            instance = new TemplateOperationsImpl();
        }
        return instance;
    }
}

