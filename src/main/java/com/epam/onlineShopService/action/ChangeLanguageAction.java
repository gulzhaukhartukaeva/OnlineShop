package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.LanguageDAOImpl;
import com.epam.onlineShopService.dao.interfaces.LanguageDAO;
import com.epam.onlineShopService.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ChangeLanguageAction implements Action{

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();

    private final LanguageDAO languageDAO = (LanguageDAOImpl) factoryDAO.getDAO(EnumDAO.LANGUAGE_DAO);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {

        HttpSession session = request.getSession(true);

        if (AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session) ||
                (AccessValidator.checkAccess(GeneralConstants.USER_ROLE_ID, session))) {

            String languageToChange = request.getParameter("languageToChange");
            Integer languageId = languageDAO.getLanguageId(languageToChange);

            session.setAttribute("language", languageToChange);
            session.setAttribute("languageId", languageId);

            String PROFILE_PAGE = "/profile.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}