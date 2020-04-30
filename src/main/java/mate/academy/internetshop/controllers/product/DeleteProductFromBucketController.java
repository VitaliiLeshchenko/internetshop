package mate.academy.internetshop.controllers.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;

@WebServlet("/productDeleteFromBucket")
public class DeleteProductFromBucketController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService)INJECTOR.getInstance(ShoppingCartService.class);
    private ProductService productService
            = (ProductService)INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Product product = productService.get(Long.parseLong(req.getParameter("productId")));
        shoppingCartService.deleteProduct(shoppingCartService.getByUserId(USER_ID), product);
        resp.sendRedirect(req.getContextPath() + "/shoppingCartShow");
    }
}
