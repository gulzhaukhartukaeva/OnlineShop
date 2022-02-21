package com.epam.onlineShopService.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class Errors {
    public static String getErrorFromLanguageBundle(HttpServletRequest request, String error) {

        HttpSession session = request.getSession(true);

        String currentLanguage = (String) session.getAttribute("language");
        Locale sessionLocale = new Locale(currentLanguage);
        ResourceBundle languageBundle = ResourceBundle.getBundle("language", sessionLocale);

        return languageBundle.getString(error);
    }
}
