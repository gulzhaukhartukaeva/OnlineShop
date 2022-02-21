package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.interfaces.OrderDetailDAO;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Order;
import com.epam.onlineShopService.entity.OrderDetail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_LAST_ID_FROM_ORDERDETAIL = "SELECT MAX(ID) FROM `ORDERDETAIL`";
    private static final String ADD_ORDERDETAIL = "INSERT INTO `ORDERDETAIL` (ID, PRODUCTID, UNIT_PRICE, AMOUNT, ORDERID) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDER_DETAILS = "SELECT * FROM ORDERDETAIL";


    @Override
    public void create(OrderDetail orderDetail) throws SQLException {

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDERDETAIL)) {

            preparedStatement.setLong(1, orderDetail.getId());
            preparedStatement.setLong(2, orderDetail.getProductId());
            preparedStatement.setDouble(3, orderDetail.getUnitPrice());
            preparedStatement.setInt(4, orderDetail.getAmount());
            preparedStatement.setLong(5, orderDetail.getOrderId());

            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<OrderDetail> getAll(Integer languageID) throws SQLException, IOException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER_DETAILS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                setParametersToOrderDetail(orderDetail, resultSet);
                orderDetails.add(orderDetail);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orderDetails;
    }

    private void setParametersToOrderDetail(OrderDetail orderDetail, ResultSet resultSet) throws SQLException, IOException{
    }

    @Override
    public OrderDetail getByID(Long id, Integer languageId) throws SQLException, IOException {
        return null;
    }

    @Override
    public void update(Long id, Integer languageId, OrderDetail orderDetail) throws SQLException {

    }

    @Override
    public Long takeLastID() throws SQLException {
        long lastID = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_ID_FROM_ORDERDETAIL)) {
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
