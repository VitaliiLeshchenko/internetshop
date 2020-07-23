package mate.academy.internet.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.service.ShoppingCartService;

@WebServlet("/user/card")
public class UserGetCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet.shop");
    private final ShoppingCartService shoppingCartService
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        req.setAttribute("shoppingCartList",
                shoppingCart.getProducts());
        req.setAttribute("price", shoppingCartService.getTotalPrice(shoppingCart));
        req.getRequestDispatcher("/WEB-INF/jsp/shoppingCartShow.jsp").forward(req, resp);
    }
}
