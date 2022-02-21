package com.epam.onlineShopService.filter;

import com.epam.onlineShopService.constants.GeneralConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {
    private static final String CONFIG_LANGUAGE_ID_NAME = "defaultLanguageID";
    private static final String CONFIG_LANGUAGE_NAME = "defaultLanguage";
    private Integer defaultLanguageID;
    private String defaultLanguage;

    @Override
    public void init(FilterConfig config) throws ServletException {
        defaultLanguageID = Integer.parseInt(config.getInitParameter(CONFIG_LANGUAGE_ID_NAME));
        defaultLanguage = config.getInitParameter(CONFIG_LANGUAGE_NAME);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(true);

        Integer languageId = (Integer) session.getAttribute("languageId");
        String language = (String) session.getAttribute("language");

        if (languageId == null || language == null) {
            session.setAttribute("languageId", defaultLanguageID);
            session.setAttribute("language", defaultLanguage);
        } else if (languageId.equals(GeneralConstants.ENGLISH_ID) && language.equalsIgnoreCase(GeneralConstants.ENGLISH)) {
            session.setAttribute("languageId", GeneralConstants.ENGLISH_ID);
            session.setAttribute("language", GeneralConstants.ENGLISH);
        } else if (languageId.equals(GeneralConstants.RUSSIAN_ID) && language.equalsIgnoreCase(GeneralConstants.RUSSIAN)) {
            session.setAttribute("languageId", GeneralConstants.RUSSIAN_ID);
            session.setAttribute("language", GeneralConstants.RUSSIAN);
        }

        chain.doFilter(request, response);
    }
}

