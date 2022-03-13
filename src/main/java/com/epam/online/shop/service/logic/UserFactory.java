package com.epam.online.shop.service.logic;

import com.epam.online.shop.service.constants.GeneralConstants;
import com.epam.online.shop.service.entity.Role;
import com.epam.online.shop.service.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserFactory {
    private static UserFactory instance = new UserFactory();

    private UserFactory() {
    }

    public User fillUser(HttpServletRequest request) throws ParseException {
        User newUser = new User();
        newUser.setEmail(request.getParameter("email"));
        newUser.setSurname(request.getParameter("surname"));
        newUser.setName(request.getParameter("name"));
        newUser.setPhoneNumber(request.getParameter("phoneNumber"));
        String password = request.getParameter("password");
        String securedPassword = DigestUtils.md5Hex(password);
        newUser.setPassword(securedPassword);

        Role role = new Role();
        role.setId(GeneralConstants.USER_ROLE_ID);
        newUser.setRole(role);

        return newUser;
    }

    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }
}
