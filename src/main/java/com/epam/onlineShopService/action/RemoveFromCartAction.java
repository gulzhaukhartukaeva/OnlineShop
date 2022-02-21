package com.epam.onlineShopService.action;

import com.epam.onlineShopService.entity.CartItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class RemoveFromCartAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            ParseException, SQLException {

        HttpSession session = request.getSession(true);

        Integer cartItemID = Integer.parseInt(request.getParameter("id"));
        Integer itemsCounter = (Integer) session.getAttribute("cartWithItems");
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");

        cartItems.removeIf(cartItem -> cartItem.getId().equals(cartItemID));
        itemsCounter--;

        session.setAttribute("cartWithItems", itemsCounter);
        session.setAttribute("cartItems", cartItems);
        String CART_PAGE = "/cart";
        response.sendRedirect(CART_PAGE);
    }
}