package com.epam.online.shop.service.dao.implementation;

import com.epam.online.shop.service.dao.interfaces.StatusDAO;
import com.epam.online.shop.service.database.connection.ConnectionPool;
import com.epam.online.shop.service.entity.Status;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAOImpl implements StatusDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ALL_STATUS = "SELECT * FROM `STATUS` WHERE LANGUAGE_ID = ?";
    private static final String SELECT_STATUS_BY_ID = "SELECT ID, NAME, LANGUAGE_ID FROM `STATUS` WHERE ID = ? AND LANGUAGE_ID = ?";
    private static final String ADD_STATUS = "INSERT INTO `STATUS` (NAME, LANGUAGE_ID, ID) VALUES (?, ?, ?)";
    private static final String UPDATE_STATUS = "UPDATE `STATUS` SET NAME = ? WHERE ID = ? AND LANGUAGE_ID = ?";


    @Override
    public void create(Status status) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_STATUS)) {
            preparedStatement.setString(1, status.getName());
            preparedStatement.setLong(2, status.getLanguageId());
            preparedStatement.setLong(3, status.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Status> getAll(Integer languageId) throws SQLException, IOException {
        List<Status> statuses = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATUS)) {
            preparedStatement.setInt(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Status status = new Status();
                setParametersToStatus(status, resultSet);
                statuses.add(status);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return statuses;
    }

    private void setParametersToStatus(Status status, ResultSet resultSet) throws SQLException{
        status.setId(resultSet.getLong("ID"));
        status.setName(resultSet.getString("NAME"));
        status.setLanguageId(resultSet.getInt("LANGUAGE_ID"));
    }

    @Override
    public Status getById(Long id, Integer languageId) throws SQLException, IOException {
        Status status = new Status() ;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATUS_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToStatus(status, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return status;
    }

    @Override
    public void update(Long id, Integer languageId, Status status) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS)) {
            preparedStatement.setString(1, status.getName());
            preparedStatement.setLong(2, id);
            preparedStatement.setLong(3, languageId);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
