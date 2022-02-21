package com.epam.onlineShopService.logic;

import com.epam.onlineShopService.action.*;

import java.util.HashMap;
import java.util.Map;

import com.epam.onlineShopService.constants.ActionConstants;

public class ServiceFactory {
    private static final Map<String, Action> SERVICE_MAP = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    static {
        SERVICE_MAP.put(ActionConstants.WELCOME_ACTION, new WelcomeAction());
        SERVICE_MAP.put(ActionConstants.LOGIN_ACTION, new LoginAction());
        SERVICE_MAP.put(ActionConstants.SIGNUP_PAGE, new SignUpPageAction());
        SERVICE_MAP.put(ActionConstants.SIGNUP_ACTION, new SignUpAction());
        SERVICE_MAP.put(ActionConstants.SIGNUP_SUCCESS_ACTION, new SignUpSuccessAction());
        SERVICE_MAP.put(ActionConstants.LOGIN_PAGE, new LoginPageAction());
        SERVICE_MAP.put(ActionConstants.PRODUCT_PAGE, new ProductAction());
        SERVICE_MAP.put(ActionConstants.CART_PAGE, new CartPageAction());
        SERVICE_MAP.put(ActionConstants.LOGOUT_ACTION, new LogOutAction());
        SERVICE_MAP.put(ActionConstants.ACCESS_ERROR_PAGE, new AccessErrorAction());
        SERVICE_MAP.put(ActionConstants.ADD_TO_CART_ACTION, new AddToCartService());
        SERVICE_MAP.put(ActionConstants.REMOVE_FROM_CART_ACTION, new RemoveFromCartAction());
        SERVICE_MAP.put(ActionConstants.ADD_CATEGORY_ACTION, new AddCategoryAction());
        SERVICE_MAP.put(ActionConstants.ADD_CATEGORY_PAGE_ACTION, new AddCategoryPageAction());
        SERVICE_MAP.put(ActionConstants.ADD_PRODUCER_ACTION, new AddProducerAction());
        SERVICE_MAP.put(ActionConstants.ADD_PRODUCER_PAGE_ACTION, new AddProducerPageAction());
        SERVICE_MAP.put(ActionConstants.ADD_PRODUCT_ACTION, new AddProductAction());
        SERVICE_MAP.put(ActionConstants.ADD_PRODUCT_PAGE_ACTION, new AddProductPageAction());
        SERVICE_MAP.put(ActionConstants.EDIT_CATEGORY_ACTION, new EditCategoryAction());
        SERVICE_MAP.put(ActionConstants.EDIT_CATEGORY_PAGE_ACTION, new EditCategoryPageAction());
        SERVICE_MAP.put(ActionConstants.EDIT_ORDER_ACTION, new EditOrderAction());
        SERVICE_MAP.put(ActionConstants.EDIT_ORDER_PAGE_ACTION, new EditOrderPageAction());
        SERVICE_MAP.put(ActionConstants.EDIT_PRODUCER_ACTION, new EditProducerAction());
        SERVICE_MAP.put(ActionConstants.EDIT_PRODUCER_PAGE_ACTION, new EditProducerPageAction());
        SERVICE_MAP.put(ActionConstants.EDIT_PRODUCT_ACTION, new EditProductAction());
        SERVICE_MAP.put(ActionConstants.EDIT_PRODUCT_PAGE_ACTION, new EditProductPageAction());
        SERVICE_MAP.put(ActionConstants.EDIT_USER_ACTION, new EditUserAction());
        SERVICE_MAP.put(ActionConstants.EDIT_USER_PAGE_ACTION, new EditUserPageAction());
        SERVICE_MAP.put(ActionConstants.SHOW_CATEGORIES_ACTION, new ShowCategoriesAction());
        SERVICE_MAP.put(ActionConstants.SHOW_USERS_ACTION, new ShowUsersAction());
        SERVICE_MAP.put(ActionConstants.SHOW_ORDERS_ACTION, new ShowOrdersAction());
        SERVICE_MAP.put(ActionConstants.SHOW_PRODUCERS_ACTION, new ShowProducersAction());
        SERVICE_MAP.put(ActionConstants.SHOW_PRODUCTS_ACTION, new ShowProductsAction());
        SERVICE_MAP.put(ActionConstants.ORDER_PAGE_ACTION, new OrderPageAction());
        SERVICE_MAP.put(ActionConstants.PROFILE_PAGE_ACTION, new ProfilePageAction());
        SERVICE_MAP.put(ActionConstants.CHANGE_PASSWORD_ACTION, new ChangePasswordAction());
        SERVICE_MAP.put(ActionConstants.CHANGE_LANGUAGE_ACTION, new ChangeLanguageAction());
        SERVICE_MAP.put(ActionConstants.PAY_ACTION, new PayAction());
        SERVICE_MAP.put(ActionConstants.CATALOG_PAGE_ACTION, new CatalogPageAction());
        SERVICE_MAP.put(ActionConstants.CATEGORY, new CategoryAction());
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }

    public Action getAction(String request) {
        Action action = SERVICE_MAP.get(ActionConstants.ERROR_ACTION);

        for (Map.Entry<String, Action> pair : SERVICE_MAP.entrySet()) {
            if (request.equalsIgnoreCase(pair.getKey())) {
                action = SERVICE_MAP.get(pair.getKey());
            }
        }
        return action;
    }
}






