package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

@WebServlet("/user/orders")
public class UserGetOrdersController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        User user = userService.get(userId);
        req.setAttribute("order", orderService.getUserOrders(user));
        req.getRequestDispatcher("/WEB-INF/jsp/userOrders.jsp").forward(req, resp);
    }
}