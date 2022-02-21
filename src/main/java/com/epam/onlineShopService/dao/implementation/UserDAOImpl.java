package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.interfaces.*;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.User;
import com.epam.onlineShopService.validator.AuthorizationValidator;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private RoleDAOImpl roleDAO = new RoleDAOImpl();

    private static final String ADD_USER = "INSERT INTO USER (email, surname, name, birthDate, phoneNumber, securityQuestion," +
            "answer, password, role_ID, isBanned) " +
            "VALUES (?,?,?,?,?,?,?,?,?, ?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM USER";
    private static final String CHANGE_PASSWORD = "UPDATE USER SET password = ? WHERE (ID = ?)";
    private static final String CHANGE_ROLE = "UPDATE USER SET role_id = ? WHERE (ID = ?)";
    private static final String GET_USER_BY_ID = "SELECT U.ID, U.NAME, U.SURNAME, U.password, " +
            "U.EMAIL, U.BIRTHDATE, U.phoneNumber, U.ADDRESS, U.ISBANNED, U.ROLE_ID, R.ROLE FROM USER U, ROLE R WHERE U.ROLE_ID=R.ID AND U.ID=?";
    private static final String GET_USER_BY_EMAIL_PASSWORD = "SELECT U.ID, U.NAME, U.SURNAME, U.PASSWORD, " +
            "U.EMAIL, U.BIRTHDATE, U.phoneNumber, U.ADDRESS, U.ROLE_ID, R.ROLE, U.isBanned FROM USER U, ROLE R WHERE U.EMAIL = ? AND U.PASSWORD = ?";
    private static final String DELETE_USER = "DELETE FROM USER WHERE ID = ?";
    private static final String CHECK_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";
    private static final String UPDATE_USER = "UPDATE USER SET ROlE_ID = ?, ISBANNED =?  WHERE ID = ?";

    private ConnectionPool connectionPool;
    private Connection connection;

    private void setParametersToUser(User user, ResultSet resultSet, Integer languageID) throws SQLException, IOException {

        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setBirthDate(resultSet.getDate("birthDate"));
        user.setPhoneNumber(resultSet.getString("phoneNumber"));
        user.setAddress(resultSet.getString("address"));
        user.setRoleID(resultSet.getInt("role_id"));
        user.setBanned(resultSet.getBoolean("isBanned"));
        String role = roleDAO.getRole(user.getRoleID(), languageID);
        user.setRole(role);
    }

    @Override
    public void create(User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime());

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setDate (4, sqlDate);
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getSecurityQuestion());
            preparedStatement.setString(7, user.getAnswer());
            preparedStatement.setString(8, user.getPassword());
            preparedStatement.setInt(9, GeneralConstants.USER_ROLE_ID);
            preparedStatement.setBoolean(10, user.getIsBanned());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public User getUserByLoginPassword(String email, String password, Integer languageID) throws SQLException, IOException {
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
                    setParametersToUser(user, resultSet, languageID);
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
                setParametersToUser(user, resultSet, languageID);
                users.add(user);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return users;
    }

    @Override
    public User getByID(Long id, Integer languageID) throws SQLException, IOException {
        User user = new User();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                setParametersToUser(user, resultSet, languageID);
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
            preparedStatement.setInt(1, user.getRoleID());
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
    public void changeUserRole(Integer roleID, Long userID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROLE)) {
            preparedStatement.setInt(1, roleID);
            preparedStatement.setLong(2, userID);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
