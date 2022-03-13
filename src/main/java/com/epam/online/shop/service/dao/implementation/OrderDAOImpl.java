package com.epam.online.shop.service.dao.implementation;

import com.epam.online.shop.service.database.connection.ConnectionPool;
import com.epam.online.shop.service.entity.Order;
import com.epam.online.shop.service.dao.interfaces.OrderDAO;
import com.epam.online.shop.service.entity.Status;


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
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM `ORDER`";
    private static final String SELECT_ORDER_BY_ID = "SELECT ID, ORDER_DATE, USERID, STATUSID, TOTAL_PRICE, \" +\n" +
            "            \"DEVIVERY_ADDRESS FROM `ORDER` WHERE ID = ?";
    private static final String UPDATE_ORDER = "UPDATE `ORDER` SET STATUSID = ? WHERE ID = ?";


    @Override
    public void create(Order order) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)) {
            java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());

            preparedStatement.setLong(1, order.getId());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setInt(3, order.getUserId());
            preparedStatement.setLong(4, order.getStatus().getId());
            preparedStatement.setDouble(5, order.getTotalPrice());
            preparedStatement.setString(6, order.getDeliveryAddress());

            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Order> getAll(Integer languageId) throws SQLException, IOException {
        List<Order> orders = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setParametersToOrder(order, resultSet);
                orders.add(order);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    private void setParametersToOrder(Order order, ResultSet resultSet) throws SQLException{
        order.setId(resultSet.getLong("ID"));
        order.setOrderDate(resultSet.getDate("ORDER_DATE"));
        order.setUserId(resultSet.getInt("USERID"));

        Status status = new Status();
        order.setStatus(status);
        order.getStatus().setId(resultSet.getLong("STATUSID"));

        order.setTotalPrice(resultSet.getDouble("TOTAL_PRICE"));
        order.setDeliveryAddress(resultSet.getString("DEVIVERY_ADDRESS"));
    }

    @Override
    public Order getById(Long id, Integer languageId) throws SQLException, IOException {
        Order order = new Order() ;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToOrder(order, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return order;
    }

    @Override
    public void update(Long id, Integer languageId, Order order) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {
            preparedStatement.setLong(1, order.getStatus().getId());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Long takeLastId() throws SQLException {
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