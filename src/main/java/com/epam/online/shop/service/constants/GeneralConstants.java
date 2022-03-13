package com.epam.online.shop.service.constants;

public class GeneralConstants {
    public static final Long USER_ROLE_ID = 1L;
    public static final Long ADMIN_ROLE_ID = 3L;
    public static final String ENGLISH = "en_US";
    public static final String RUSSIAN = "ru_RU";
    public static final Integer ENGLISH_ID = 1;
    public static final Integer RUSSIAN_ID = 2;
    public static final Long STATUS_SEND = 2L;

    public Long getUserRoleId() {
        return USER_ROLE_ID;
    }

    public Long getAdminRoleId() {
        return ADMIN_ROLE_ID;
    }

    public Integer getEnglishId() {
        return ENGLISH_ID;
    }

    public Integer getRussianId() {
        return RUSSIAN_ID;
    }
}
