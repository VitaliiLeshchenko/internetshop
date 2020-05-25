package mate.academy.internet_shop.controllers.product;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet_shop.lib.Injector;
import mate.academy.internet_shop.service.ProductService;

@WebServlet("/product/delete")
public class ProductDeleteController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet_shop");
    private ProductService productService
            = (ProductService)INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("product_id");
        productService.delete(Long.parseLong(id));
        resp.sendRedirect(req.getContextPath() + "/product/list/admin");
    }
}
