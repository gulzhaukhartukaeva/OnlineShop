package com.epam.online.shop.service.dao.implementation;

import com.epam.online.shop.service.dao.interfaces.UserDAO;
import com.epam.online.shop.service.database.connection.ConnectionPool;
import com.epam.online.shop.service.entity.Role;
import com.epam.online.shop.service.entity.User;
import com.epam.online.shop.service.validator.AuthorizationValidator;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String ADD_USER = "INSERT INTO USER (email, surname, name, phoneNumber," +
            "password, roleId, isBanned) " + "VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM USER";
    private static final String CHANGE_PASSWORD = "UPDATE USER SET password = ? WHERE (ID = ?)";
    private static final String CHANGE_ADDRESS = "UPDATE USER SET address = ? WHERE (ID = ?)";
    private static final String GET_USER_BY_ID = "SELECT U.ID, U.NAME, U.SURNAME, U.password, " +
            "U.EMAIL, U.phoneNumber, U.ADDRESS, U.ISBANNED, U.ROLEID, R.NAME FROM USER U, ROLE R WHERE U.ROLEID=R.ID AND U.ID=?";
    private static final String GET_USER_BY_EMAIL_PASSWORD = "SELECT U.ID, U.NAME, U.SURNAME, U.PASSWORD, " +
            "U.EMAIL, U.phoneNumber, U.ADDRESS, U.roleId, U.isBanned FROM USER U, ROLE R WHERE U.EMAIL = ? AND U.PASSWORD = ?";
    private static final String CHECK_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";
    private static final String UPDATE_USER = "UPDATE USER SET ROlEID = ?, ISBANNED =?  WHERE ID = ?";

    private ConnectionPool connectionPool;
    private Connection connection;

    private void setParametersToUser(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setPhoneNumber(resultSet.getString("phoneNumber"));
        user.setAddress(resultSet.getString("address"));

        Role role = new Role();
        user.setRole(role);
        user.getRole().setId(resultSet.getLong("roleId"));

        user.setBanned(resultSet.getBoolean("isBanned"));
    }

    @Override
    public void create(User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setLong(6, user.getRole().getId());
            preparedStatement.setBoolean(7, user.getIsBanned());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public User getUserByLoginPassword(String email, String password, Integer languageID) throws SQLException {
        User user = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String passwordInDataBase = resultSet.getString("password");
                user = new User();
                if (AuthorizationValidator.checkPassword(passwordInDataBase, password)) {
                    setParametersToUser(user, resultSet);
                }
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> getAll(Integer languageID) throws SQLException, IOException {
        List<User> users = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                setParametersToUser(user, resultSet);
                users.add(user);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return users;
    }

    @Override
    public User getById(Long id, Integer languageID) throws SQLException, IOException {
        User user = new User();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                setParametersToUser(user, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public boolean isEmailExist(String email) throws SQLException {
        boolean isExist = false;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                isExist = true;
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExist;
    }

    @Override
    public void update(Long id, Integer languageID, User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setLong(1, user.getRole().getId());
            preparedStatement.setBoolean(2, user.getIsBanned());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void updatePassword(String password, Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void updateAddress(String address, Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ADDRESS)) {
            preparedStatement.setString(1, address);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
