package com.epam.online.shop.service.validator;

public class AuthorizationValidator {
    private static final String PHONE_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9~!@#$%^&*]{6,}";

    public static boolean validatePhoneNumber(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public static boolean checkPassword(String password, String passwordInDataBase) {
        return passwordInDataBase.equals(password);
    }

    public static boolean validatePasswordRegex(String password) {
        return password.matches(PASSWORD_REGEX);
    }

}

