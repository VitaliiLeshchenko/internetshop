package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

@WebServlet("/injectData")
public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);
    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        userService.create(new User("Vitalii", "411100253g@gmail.com", "niceTry"));
        userService.create(new User("Bolvan", "1234123@gmail.com", "niceTry"));
        userService.create(new User("Goblin", "312983791@gmail.com", "niceTry"));
        productService.create(new Product("Telephone", 109.0));
        productService.create(new Product("TV", 123.0));
        productService.create(new Product("Table", 3.0));
        productService.create(new Product("Monitor", 1231.0));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
