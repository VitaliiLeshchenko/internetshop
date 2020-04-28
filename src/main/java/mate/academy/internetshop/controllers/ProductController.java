package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class ProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private static final ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/productCreate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        if (name.equals("") || price.equals("") || !price.matches("[0-9]+[.]?[0-9]{0,2}")) {
            req.setAttribute("massage", "You write wrong data: " + price);
        } else {
            productService.create(new Product(name, Double.parseDouble(price)));
            req.setAttribute("massage", "Product added : name = " + name + " prise = " + price);
        }
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/productCreate.jsp").forward(req,resp);
    }
}
