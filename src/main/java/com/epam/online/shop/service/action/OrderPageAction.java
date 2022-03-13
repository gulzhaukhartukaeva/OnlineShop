package com.epam.online.shop.service.action;

import com.epam.online.shop.service.entity.ShoppingCardItem;
import com.epam.online.shop.service.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static com.epam.online.shop.service.constants.ActionConstants.CART_PAGE;
import static com.epam.online.shop.service.constants.GeneralConstants.*;
import static com.epam.online.shop.service.constants.PageConstants.*;

public class OrderPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        RequestDispatcher requestDispatcher;

        if (request.getParameter("checkout") != null) {
            if (AccessValidator.checkAccess(USER_ROLE_ID, session)) {
                if (!AccessValidator.checkBanned(session)) {
                    requestDispatcher = request.getRequestDispatcher(ORDERING_PAGE);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher(BANNED_MESSAGE_PAGE);
                    requestDispatcher.forward(request, response);
                }
            }else {
                requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
                requestDispatcher.forward(request, response);
            }
        }

        if (request.getParameter("update") != null) {
            ArrayList<ShoppingCardItem> shoppingCardItems = (ArrayList<ShoppingCardItem>) session.getAttribute("cartItems");
            for (int i = 0; i < shoppingCardItems.size(); i++) {
                shoppingCardItems.get(i).setAmount(Integer.parseInt(request.getParameterValues("amount")[i]));
                shoppingCardItems.get(i).setTotalPrice(
                        shoppingCardItems.get(i).getProduct().getPrice() * shoppingCardItems.get(i).getAmount());
            }

            double totalPriceCard = 0;
            for (int i = 0; i < shoppingCardItems.size(); i++) {
                totalPriceCard = totalPriceCard + shoppingCardItems.get(i).getTotalPrice();
            }
            session.setAttribute("totalPriceCard", totalPriceCard);

            requestDispatcher = request.getRequestDispatcher(CART_PAGE);
            requestDispatcher.forward(request, response);
        }

        if (request.getParameter("remove") != null) {
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
}
