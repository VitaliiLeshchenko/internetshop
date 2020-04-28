package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;

public class ProductDeleteFromBucketController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final ShoppingCartService SHOPPING_CART_SERVICE
            = (ShoppingCartService)INJECTOR.getInstance(ShoppingCartService.class);
    private static final ProductService PRODUCT_SERVICE
            = (ProductService)INJECTOR.getInstance(ProductService.class);
    private static final Long USER_ID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Product product = PRODUCT_SERVICE.get(Long.parseLong(req.getParameter("productId")));
        SHOPPING_CART_SERVICE.deleteProduct(SHOPPING_CART_SERVICE.getByUserId(USER_ID), product);
        resp.sendRedirect(req.getContextPath() + "/shoppingCartShow");
    }
}
