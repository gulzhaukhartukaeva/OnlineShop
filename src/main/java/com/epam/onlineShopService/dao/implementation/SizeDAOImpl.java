package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.interfaces.SizeDAO;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.Size;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SizeDAOImpl implements SizeDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ALL_SIZES = "SELECT * FROM SIZE WHERE LANGUAGE_ID = ?";
    private static final String ADD_SIZE = "INSERT INTO SIZE (NAME, LANGUAGE_ID, ID) VALUES (?, ?, ?)";
    private static final String SELECT_SIZE_BY_ID = "SELECT ID, NAME, LANGUAGE_ID FROM SIZE WHERE ID = ? AND LANGUAGE_ID = ?";
    private static final String UPDATE_SIZE = "UPDATE SIZE SET NAME = ? WHERE ID = ? AND LANGUAGE_ID = ?";

    @Override
    public void create(Size size) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_SIZE)) {
            preparedStatement.setString(1, size.getName());
            preparedStatement.setLong(2, size.getLanguageId());
            preparedStatement.setLong(3, size.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Size> getAll(Integer languageId) throws SQLException, IOException {
        List<Size> sizes = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SIZES)) {
            preparedStatement.setInt(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size size = new Size();
                setParametersToSize(size, resultSet);
                sizes.add(size);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return sizes;
    }

    private void setParametersToSize(Size size, ResultSet resultSet) throws SQLException, IOException{
        size.setId(resultSet.getLong("ID"));
        size.setLanguageId(resultSet.getInt("LANGUAGE_ID"));
        size.setName(resultSet.getString("NAME"));    }

    @Override
    public Size getByID(Long id, Integer languageId) throws SQLException, IOException {
        Size size = new Size();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SIZE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToSize(size, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return size;
    }

    @Override
    public void update(Long id, Integer languageId, Size size) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIZE)) {
            preparedStatement.setString(1, size.getName());
            preparedStatement.setLong(2, id);
            preparedStatement.setLong(3, languageId);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
