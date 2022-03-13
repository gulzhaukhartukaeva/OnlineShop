package com.epam.online.shop.service.action;

import com.epam.online.shop.service.entity.ShoppingCardItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static com.epam.online.shop.service.constants.ActionConstants.*;

public class RemoveFromCartAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            ParseException, SQLException {
        HttpSession session = request.getSession(true);

        Integer cartItemId = Integer.parseInt(request.getParameter("id"));
        Integer idCounter = (Integer) session.getAttribute("idCounter");
        ArrayList<ShoppingCardItem> shoppingCardItems = (ArrayList<ShoppingCardItem>) session.getAttribute("cartItems");

        shoppingCardItems.removeIf(cartItem -> cartItem.getId().equals(cartItemId));
        idCounter--;

        for(int i = 0; i < shoppingCardItems.size(); i++){
            shoppingCardItems.get(i).setTotalPrice(
                    shoppingCardItems.get(i).getProduct().getPrice() * shoppingCardItems.get(i).getAmount());
        }

        double totalPriceCard = 0;
        for(int i = 0; i < shoppingCardItems.size(); i++) {
            totalPriceCard = totalPriceCard + shoppingCardItems.get(i).getTotalPrice();
        }

        session.setAttribute("idCounter", idCounter);
        session.setAttribute("cartItems", shoppingCardItems);
        session.setAttribute("totalPriceCard", totalPriceCard);

        response.sendRedirect(CART_PAGE);
    }
}