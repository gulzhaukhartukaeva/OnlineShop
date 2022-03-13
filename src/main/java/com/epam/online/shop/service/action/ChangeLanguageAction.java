package com.epam.online.shop.service.action;

import com.epam.online.shop.service.dao.FactoryDAO;
import com.epam.online.shop.service.dao.implementation.LanguageDAOImpl;
import com.epam.online.shop.service.dao.interfaces.LanguageDAO;
import com.epam.online.shop.service.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;
import static com.epam.online.shop.service.dao.EnumDAO.*;

public class ChangeLanguageAction implements Action {
    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final LanguageDAO languageDAO = (LanguageDAOImpl) factoryDAO.getDAO(LANGUAGE_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        RequestDispatcher requestDispatcher;

        if (AccessValidator.checkAccess(ADMIN_ROLE_ID, session) ||
                (AccessValidator.checkAccess(USER_ROLE_ID, session))) {

            String languageToChange = request.getParameter("languageToChange");
            Integer languageId = languageDAO.getLanguageId(languageToChange);
            session.setAttribute("language", languageToChange);
            session.setAttribute("languageId", languageId);

            requestDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}