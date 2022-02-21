package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.interfaces.OrderDAO;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.Order;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_ORDER = "INSERT INTO `ORDER` (ID, ORDER_DATE, USERID, STATUSID, TOTAL_PRICE, " +
            "DEVIVERY_ADDRESS) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_LAST_ID_FROM_ORDER = "SELECT MAX(ID) FROM `ORDER`";


    @Override
    public void create(Order order) throws SQLException {

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)) {
            java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());

            preparedStatement.setLong(1, order.getId());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setInt(3, order.getUserId());
            preparedStatement.setInt(4, order.getStatusId());
            preparedStatement.setDouble(5, order.getTotalPrice());
            preparedStatement.setString(6, order.getDeliveryAddress());

            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Order> getAll(Integer languageID) throws SQLException, IOException {
        return null;
    }

    @Override
    public Order getByID(Long id, Integer languageID) throws SQLException, IOException {
        return null;
    }

    @Override
    public void update(Long id, Integer languageID, Order object) throws SQLException {

    }

    @Override
    public Long takeLastID() throws SQLException {
        long lastID = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_ID_FROM_ORDER)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lastID = resultSet.getLong("MAX(ID)");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return lastID;
    }
}