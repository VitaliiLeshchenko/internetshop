package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.ShoppingCartService;

public class ShoppingCartShowController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final ShoppingCartService SHOPPING_CART_SERVICE
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("shoppingCartList",
                SHOPPING_CART_SERVICE.getByUserId(USER_ID).getProducts());
        req.setAttribute("price", SHOPPING_CART_SERVICE.getTotalPrice(USER_ID));
        req.getRequestDispatcher("/WEB-INF/jsp/shoppingCartShow.jsp").forward(req, resp);
    }
}
