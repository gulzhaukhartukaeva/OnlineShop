package com.epam.onlineShopService.validator;

import javax.servlet.http.HttpSession;

public class AccessValidator {
    private static final String ROLE_ID="roleId";

    public static boolean checkAccess(Integer role, HttpSession httpSession){

        boolean isValid = false;
        Integer role_id = (Integer) httpSession.getAttribute(ROLE_ID);

        if(role_id != null && role_id.equals(role))
        {
            isValid = true;
        }
        return isValid;
    }
}
