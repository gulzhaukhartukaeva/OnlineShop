package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.interfaces.ProducerDAO;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.Category;
import com.epam.onlineShopService.entity.Producer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerDAOImpl implements ProducerDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ALL_PRODUCERS = "SELECT * FROM PRODUCER WHERE LANGUAGE_ID = ?";
    private static final String ADD_PRODUCER = "INSERT INTO PRODUCER (NAME, LANGUAGE_ID, ID) VALUES (?, ?, ?)";
    private static final String SELECT_PRODUCER_BY_ID = "SELECT ID, NAME, LANGUAGE_ID FROM PRODUCER WHERE ID = ? AND LANGUAGE_ID = ?";
    private static final String SELECT_LAST_ID_FROM_PRODUCER = "SELECT MAX(ID) FROM PRODUCER";
    private static final String SELECT_ALL_LOCALIZED_PRODUCERS_BY_ID = "SELECT * FROM PRODUCER WHERE ID = ?";
    private static final String UPDATE_PRODUCER = "UPDATE PRODUCER SET NAME = ? WHERE ID = ? AND LANGUAGE_ID = ?";

    @Override
    public void create(Producer producer) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCER)) {
            preparedStatement.setString(1, producer.getName());
            preparedStatement.setLong(2, producer.getLanguageId());
            preparedStatement.setLong(3, producer.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Producer> getAll(Integer languageId) throws SQLException, IOException {
        List<Producer> producers = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCERS)) {
            preparedStatement.setInt(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Producer producer = new Producer();
                setParametersToProducer(producer, resultSet);
                producers.add(producer);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return producers;
    }

    private void setParametersToProducer(Producer producer, ResultSet resultSet) throws SQLException, IOException{
        producer.setId(resultSet.getLong("ID"));
        producer.setLanguageId(resultSet.getInt("LANGUAGE_ID"));
        producer.setName(resultSet.getString("NAME"));
    }

    @Override
    public Producer getByID(Long id, Integer languageId) throws SQLException, IOException {
        Producer producer = new Producer();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCER_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToProducer(producer, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return producer;
    }

    @Override
    public void update(Long id, Integer languageId, Producer producer) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCER)) {
            preparedStatement.setString(1, producer.getName());
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_ID_FROM_PRODUCER)) {
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
    public List<Producer> takeAllLocalizedProducerByID(Long animalID) throws SQLException, IOException {
        List<Producer> producers = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOCALIZED_PRODUCERS_BY_ID)) {
            preparedStatement.setLong(1, animalID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToProducerList(producers, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return producers;
    }

    private void setParametersToProducerList(List<Producer> producers, ResultSet resultSet) throws SQLException, IOException {
        Producer producer = new Producer();
        setParametersToProducer(producer, resultSet);
        producers.add(producer);
    }
}

