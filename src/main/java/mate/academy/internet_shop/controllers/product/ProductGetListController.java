package mate.academy.internet_shop.controllers.product;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet_shop.lib.Injector;
import mate.academy.internet_shop.model.Product;
import mate.academy.internet_shop.service.ProductService;

@WebServlet("/product/list")
public class ProductGetListController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet_shop");
    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> productList = productService.getAll();
        req.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB-INF/jsp/productList.jsp").forward(req, resp);
    }
}
