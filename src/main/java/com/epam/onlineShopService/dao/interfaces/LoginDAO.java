package com.epam.onlineShopService.dao.interfaces;

import com.epam.onlineShopService.entity.User;

public interface LoginDAO {
    public String authenticateUser(User user);
}
