package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.interfaces.StatusDAO;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.Status;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StatusDAOImpl implements StatusDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public void create(Status object) throws SQLException {

    }

    @Override
    public List<Status> getAll(Integer languageID) throws SQLException, IOException {
        return null;
    }

    @Override
    public Status getByID(Long id, Integer languageID) throws SQLException, IOException {
        return null;
    }

    @Override
    public void update(Long id, Integer languageID, Status object) throws SQLException {

    }
}
