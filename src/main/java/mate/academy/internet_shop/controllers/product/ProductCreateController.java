package mate.academy.internet_shop.controllers.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet_shop.lib.Injector;
import mate.academy.internet_shop.model.Product;
import mate.academy.internet_shop.service.ProductService;

@WebServlet("/product/create")
public class ProductCreateController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet_shop");
    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/productCreate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        Double price = Double.parseDouble(req.getParameter("price"));
        productService.create(new Product(name, price));
        req.getRequestDispatcher("/WEB-INF/jsp/productCreate.jsp").forward(req,resp);
    }
}
