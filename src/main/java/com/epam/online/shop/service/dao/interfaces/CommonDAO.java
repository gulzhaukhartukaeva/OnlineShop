package com.epam.online.shop.service.dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CommonDAO<T> {

    void create(T object) throws SQLException;

    List<T> getAll(Integer languageID) throws SQLException, IOException;

    T getById(Long id, Integer languageID) throws SQLException, IOException;

    void update(Long id, Integer languageID, T object) throws SQLException;
}
