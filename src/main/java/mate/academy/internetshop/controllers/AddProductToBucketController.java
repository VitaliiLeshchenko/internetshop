package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;

public class AddProductToBucketController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private static final ShoppingCartService shoppingCart
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static final ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        shoppingCart.addProduct(shoppingCart.getByUserId(USER_ID),
                productService.get(Long.parseLong(req.getParameter("productId"))));
        resp.sendRedirect(req.getContextPath() + "/productList");
    }
}
