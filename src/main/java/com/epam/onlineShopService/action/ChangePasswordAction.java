package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.UserDAOImpl;
import com.epam.onlineShopService.dao.interfaces.UserDAO;
import com.epam.onlineShopService.entity.User;
import com.epam.onlineShopService.validator.AccessValidator;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ChangePasswordAction implements Action{

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();

    private final UserDAO userDAO = (UserDAOImpl) factoryDAO.getDAO(EnumDAO.USER_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {

        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher;

        if (AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session)) {

            User user = new User();
            user.setId(Long.parseLong(request.getParameter("userId")));

            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            String newSecuredPassword = DigestUtils.md5Hex(newPassword);

            String PROFILE_PAGE = "/profile.jsp";
            if(newPassword != null){
                if (!newPassword.equals(confirmPassword)) {
                    request.setAttribute("passwordNotEqual", "passwordNotEqual");
                    dispatcher = request.getRequestDispatcher(PROFILE_PAGE);
                    dispatcher.forward(request, response);
                }
                else{
                    userDAO.updatePassword(newSecuredPassword, user.getId());
                }
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}