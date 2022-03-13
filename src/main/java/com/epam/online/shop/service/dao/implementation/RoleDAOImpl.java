package com.epam.online.shop.service.dao.implementation;

import com.epam.online.shop.service.database.connection.ConnectionPool;
import com.epam.online.shop.service.entity.Role;
import com.epam.online.shop.service.dao.interfaces.RoleDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final String GET_ROLE_BY_ID = "SELECT ID, NAME, LANGUAGE_ID FROM ROLE WHERE ID = ? AND LANGUAGE_ID = ?";
    private static final String SELECT_ALL_ROLES = "SELECT * FROM ROLE WHERE LANGUAGE_ID = ?";

    private ConnectionPool connectionPool;
    private Connection connection;

    private void setParametersToRole(Role role, ResultSet resultSet) throws SQLException {
        role.setId(resultSet.getLong("id"));
        role.setName(resultSet.getString("name"));
        role.setLanguageId(resultSet.getInt("language_id"));
    }

    @Override
    public void create(Role role) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Role> getAll(Integer languageId) throws SQLException, IOException {
        List<Role> roles = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES)) {
            preparedStatement.setInt(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                setParametersToRole(role, resultSet);
                roles.add(role);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return roles;
    }

    @Override
    public Role getById(Long id, Integer languageId) throws SQLException, IOException {
        Role role = new Role() ;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setParametersToRole(role, resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return role;
    }

    @Override
    public void update(Long id, Integer languageID, Role role) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
