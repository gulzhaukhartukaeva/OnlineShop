package com.epam.online.shop.service.validator;

import javax.servlet.http.HttpSession;

public class AccessValidator {
    public static boolean checkAccess(Long role, HttpSession session){
        boolean isValid = false;
        Long roleId = (Long) session.getAttribute("roleId");

        if(roleId != null && roleId.equals(role))
        {
            isValid = true;
        }
        return isValid;
    }

    public static boolean checkBanned(HttpSession session){
        Boolean isBanned = (Boolean) session.getAttribute("isBanned");
        if(isBanned)
            return true;
        else
            return false;
    }
}
