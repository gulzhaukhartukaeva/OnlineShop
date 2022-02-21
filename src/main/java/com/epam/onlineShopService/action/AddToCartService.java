package com.epam.onlineShopService.action;

import com.epam.onlineShopService.entity.CartItem;
import com.epam.onlineShopService.logic.CartItemFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AddToCartService implements Action {

    private final CartItemFactory cartItemFactory = CartItemFactory.getInstance();
    private final Integer COUNTER_BEGIN = 1;
    private Integer itemsCounter = COUNTER_BEGIN;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);

        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");
        java.sql.Date todaySql = new java.sql.Date(new Date().getTime());
        String today = todaySql.toString();
        java.sql.Date.valueOf(today);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
            CartItem cartItem = cartItemFactory.setCartItemParameters(request, session, itemsCounter);
            cartItems.add(cartItem);
        } else {
            itemsCounter++;
            CartItem cartItem = cartItemFactory.setCartItemParameters(request, session, itemsCounter);
            cartItems.add(cartItem);
        }

        double totalPrice = 0.0;

        for (CartItem item: cartItems){
            totalPrice = totalPrice + item.getProduct().getPrice();
            item.setTotalPrice(totalPrice);
        }

        session.setAttribute("cartItems", cartItems);
        session.setAttribute("today", today);
        session.setAttribute("cartWithItems", itemsCounter);
        session.setAttribute("totalPrice", totalPrice);

        String WELCOME_PAGE = "/";
        response.sendRedirect(WELCOME_PAGE);
    }
}