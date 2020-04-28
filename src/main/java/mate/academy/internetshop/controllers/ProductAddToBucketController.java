package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;

public class ProductAddToBucketController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final ShoppingCartService SHOPPING_CART_SERVICE
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private static final ProductService PRODUCT_SERVICE
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SHOPPING_CART_SERVICE.addProduct(SHOPPING_CART_SERVICE.getByUserId(USER_ID),
                PRODUCT_SERVICE.get(Long.parseLong(req.getParameter("productId"))));
        resp.sendRedirect(req.getContextPath() + "/productList");
    }
}
