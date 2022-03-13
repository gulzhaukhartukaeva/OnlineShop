package com.epam.online.shop.service.action;

import com.epam.online.shop.service.entity.ShoppingCardItem;
import com.epam.online.shop.service.logic.ShoppingCardFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.epam.online.shop.service.constants.ActionConstants.*;

public class AddToShoppingCardService implements Action {
    private final ShoppingCardFactory shoppingCardFactory = ShoppingCardFactory.getInstance();
    private final Integer COUNTER_BEGIN = 1;
    private Integer idCounter = COUNTER_BEGIN;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);

        ArrayList<ShoppingCardItem> shoppingCardItems = (ArrayList<ShoppingCardItem>) session.getAttribute("cartItems");

        if (shoppingCardItems == null) {
            shoppingCardItems = new ArrayList<>();
            ShoppingCardItem cartItem = shoppingCardFactory.setCartItemParameters(request, session, idCounter);
            shoppingCardItems.add(cartItem);
        } else {
            idCounter++;
            ShoppingCardItem cartItem = shoppingCardFactory.setCartItemParameters(request, session, idCounter);
            shoppingCardItems.add(cartItem);
        }

        for(int i = 0; i < shoppingCardItems.size(); i++){
            shoppingCardItems.get(i).setTotalPrice(
                    shoppingCardItems.get(i).getProduct().getPrice() * shoppingCardItems.get(i).getAmount());
        }

        double totalPriceCard = 0;
        for(int i = 0; i < shoppingCardItems.size(); i++) {
            totalPriceCard = totalPriceCard + shoppingCardItems.get(i).getTotalPrice();
        }

        session.setAttribute("cartItems", shoppingCardItems);
        session.setAttribute("idCounter", idCounter);
        session.setAttribute("totalPriceCard", totalPriceCard);

        response.sendRedirect(WELCOME_ACTION);
    }
}