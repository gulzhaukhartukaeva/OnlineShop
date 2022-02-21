package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public interface UserDAO extends CommonDAO<User>{
    User getUserByLoginPassword(String login, String password, Integer languageID) throws SQLException, IOException;

    User getByID(Long id, Integer languageID) throws SQLException, IOException;

    void updatePassword(String password, Long id) throws SQLException;

    void changeUserRole(Integer roleID, Long userID) throws SQLException;

    boolean isEmailExist(String login) throws SQLException;

    void delete(Long id) throws SQLException;
 }
