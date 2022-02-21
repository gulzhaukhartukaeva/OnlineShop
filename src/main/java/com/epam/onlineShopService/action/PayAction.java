package com.epam.onlineShopService.action;

import com.epam.onlineShopService.constants.GeneralConstants;
import com.epam.onlineShopService.dao.EnumDAO;
import com.epam.onlineShopService.dao.FactoryDAO;
import com.epam.onlineShopService.dao.implementation.OrderDAOImpl;
import com.epam.onlineShopService.dao.implementation.OrderDetailDAOImpl;
import com.epam.onlineShopService.dao.interfaces.OrderDAO;
import com.epam.onlineShopService.dao.interfaces.OrderDetailDAO;
import com.epam.onlineShopService.entity.CartItem;
import com.epam.onlineShopService.entity.Order;
import com.epam.onlineShopService.entity.OrderDetail;
import com.epam.onlineShopService.logic.OrderFactory;
import com.epam.onlineShopService.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class PayAction implements Action{

    private final FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private final OrderFactory orderFactory = OrderFactory.getInstance();

    private final OrderDAO orderDAO = (OrderDAOImpl) factoryDAO.getDAO(EnumDAO.ORDER_DAO);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAOImpl) factoryDAO.getDAO(EnumDAO.ORDER_DETAIL_DAO);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        if (AccessValidator.checkAccess(GeneralConstants.ADMIN_ROLE_ID, session) ||
                AccessValidator.checkAccess(GeneralConstants.USER_ROLE_ID, session)) {

            Order order = orderFactory.fillOrder(request);
            orderDAO.create(order);

            ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");

            for ( CartItem item: cartItems) {
                OrderDetail orderDetail = new OrderDetail();
                Long generatedID = orderDetailDAO.takeLastID() + 1;
                orderDetail.setId(generatedID);

                orderDetail.setProductId(item.getProduct().getId());
                orderDetail.setUnitPrice(item.getProduct().getPrice());
                orderDetail.setAmount(1);
                orderDetail.setOrderId(order.getId());

                orderDetailDAO.create(orderDetail);
            }

            String ORDER_SUCCESS_PAGE = "/order_success.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ORDER_SUCCESS_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
