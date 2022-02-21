package com.epam.onlineShopService.dao.implementation;

import com.epam.onlineShopService.dao.interfaces.LoginDAO;
import com.epam.onlineShopService.database.connection.ConnectionPool;
import com.epam.onlineShopService.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    private static final String USER_CREDENTIALS = "SELECT email, password, IsAdmin FROM USER";
    private String emailDatabase = "";
    private String passwordDatabase = "";
    private boolean isAdminDatabase = false;

    @Override
    public String authenticateUser(User user) {

        String email = user.getEmail();
        String password = user.getPassword();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.takeConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(USER_CREDENTIALS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                emailDatabase = resultSet.getString("email");
                passwordDatabase = resultSet.getString("password");
                isAdminDatabase = resultSet.getBoolean("isAdmin");

                if(email.equals(emailDatabase) && password.equals(passwordDatabase) && isAdminDatabase)
                {
                    return "Admin_Role";
                }
                else if(email.equals(emailDatabase) && password.equals(passwordDatabase) && !isAdminDatabase) {
                    return "User_Role";
                }
            }

        }catch (SQLException exception)
        {
            LOGGER.error(exception);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return "Invalid user credentials";
    }
}
