package com.epam.onlineShopService.logic;

import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.ProducerDAOImpl;
import com.epam.onlineShopService.dao.implementation.UserDAOImpl;
import com.epam.onlineShopService.dao.interfaces.ProducerDAO;
import com.epam.onlineShopService.dao.interfaces.UserDAO;
import com.epam.onlineShopService.entity.*;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;

public class UserFactory {
    private static UserFactory instance = new UserFactory();
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    private UserFactory() {
    }

    public User fillUser(HttpServletRequest request) throws ParseException {
        User newUser = new User();

        newUser.setEmail(request.getParameter("email"));
        newUser.setSurname(request.getParameter("surname"));
        newUser.setName(request.getParameter("name"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(request.getParameter("birthDate"));

        newUser.setBirthDate(date);

        newUser.setPhoneNumber(request.getParameter("phoneNumber"));
        newUser.setSecurityQuestion(request.getParameter("securityQuestion"));
        newUser.setAnswer(request.getParameter("answer"));

        String password = request.getParameter("password");
        String securedPassword = DigestUtils.md5Hex(password);
        newUser.setPassword(securedPassword);

        newUser.setRole("Пользователь");
        return newUser;
    }

    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }
}
