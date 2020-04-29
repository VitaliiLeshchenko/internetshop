package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;

public class DeleteProductController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService)INJECTOR.getInstance(ShoppingCartService.class);
    private ProductService productService
            = (ProductService)INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("productId");
        if (id != null) {
            boolean isDeleted = productService.delete(Long.parseLong(id));
            req.setAttribute("isDeleted", isDeleted);
        }
        List<Product> productList = productService.getAll();
        req.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB-INF/jsp/productListAdmin.jsp").forward(req, resp);

    }
}

