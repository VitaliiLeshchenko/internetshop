package mate.academy.internet_shop.controllers;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet_shop.lib.Injector;
import mate.academy.internet_shop.service.OrderService;

@WebServlet("/order/delete")
public class OrderDeleteController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet_shop");
    private final OrderService orderService
            = (OrderService)INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long orderId = Long.parseLong(req.getParameter("order_id"));
        orderService.delete(orderId);
        resp.sendRedirect(req.getContextPath() + "/user/orders");
    }
}
