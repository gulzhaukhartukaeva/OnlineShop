package com.epam.online.shop.service.dao.interfaces;

import com.epam.online.shop.service.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDAO extends CommonDAO<User>{
    User getUserByLoginPassword(String login, String password, Integer languageID) throws SQLException, IOException;

    User getById(Long id, Integer languageID) throws SQLException, IOException;

    void updatePassword(String password, Long id) throws SQLException;

    void updateAddress(String address, Long id) throws SQLException;

    boolean isEmailExist(String login) throws SQLException;
 }
