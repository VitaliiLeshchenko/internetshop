package mate.academy.internet_shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet_shop.lib.Injector;
import mate.academy.internet_shop.model.Order;
import mate.academy.internet_shop.service.OrderService;

@WebServlet("/user/order")
public class UserGetOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet_shop");
    private final OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.parseLong(req.getParameter("order_id"));
        Order order = orderService.get(orderId);
        req.setAttribute("id", orderId);
        req.setAttribute("date", order.getDateTime());
        req.setAttribute("products", order.getProducts());
        req.setAttribute("price", orderService.getPrice(order));
        req.getRequestDispatcher("/WEB-INF/jsp/userOrder.jsp").forward(req, resp);
    }
}
