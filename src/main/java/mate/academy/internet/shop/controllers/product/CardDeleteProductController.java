package mate.academy.internet.shop.controllers.product;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.service.ProductService;
import mate.academy.internet.shop.service.ShoppingCartService;

@WebServlet("/cart/product/delete")
public class CardDeleteProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet.shop");
    private final ShoppingCartService shoppingCartService
            = (ShoppingCartService)INJECTOR.getInstance(ShoppingCartService.class);
    private ProductService productService
            = (ProductService)INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        Product product = productService.get(Long.parseLong(req.getParameter("product_id")));
        shoppingCartService.deleteProduct(shoppingCartService.getByUserId(userId), product);
        resp.sendRedirect(req.getContextPath() + "/user/card");
    }
}
