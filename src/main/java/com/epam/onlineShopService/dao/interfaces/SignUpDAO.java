package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public interface SignUpDAO {
    public void save(User user)  throws SQLException;
}
