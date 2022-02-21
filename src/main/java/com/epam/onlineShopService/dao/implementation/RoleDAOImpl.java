package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.interfaces.CommonDAO;
import com.epam.onlineShopService.dao.interfaces.RoleDAO;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.Role;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final String GET_ROLE_BY_ID = "SELECT ROLE FROM ROLE WHERE ID = ?";
    private static final String SELECT_ALL_ROLES = "SELECT * FROM ROLE";

    private ConnectionPool connectionPool;
    private Connection connection;

    public String getRole(Integer id, Integer languageID) throws SQLException {
        String role = "";
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString("ROLE");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return role;
    }

    private void setParametersToRole(Role role, ResultSet resultSet) throws SQLException, IOException {
        role.setId(resultSet.getLong("id"));
        role.setLanguageID(resultSet.getInt("language_id"));
        role.setRole(resultSet.getString("role"));
    }

    @Override
    public void create(Role role) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List getAll(Integer languageID) throws SQLException, IOException {
        List<Role> roles = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES)) {
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
    public Role getByID(Long id, Integer languageID) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, Integer languageID, Role role) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
