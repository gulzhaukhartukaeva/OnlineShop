package com.epam.onlineShopService.constants;

public class GeneralConstants {
    public static final String USER = "Пользователь";
    public static final Integer USER_ROLE_ID = 1;
    public static final Integer ADMIN_ROLE_ID = 3;
    public static final String ENGLISH = "en_US";
    public static final String RUSSIAN = "ru_RU";
    public static final Integer ENGLISH_ID = 1;
    public static final Integer RUSSIAN_ID = 2;

    public Integer getUserRoleId() {
        return USER_ROLE_ID;
    }

    public Integer getAdminRoleId() {
        return ADMIN_ROLE_ID;
    }

    public Integer getEnglishId() {
        return ENGLISH_ID;
    }

    public Integer getRussianId() {
        return RUSSIAN_ID;
    }
}
