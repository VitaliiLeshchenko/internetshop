package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final UserService USER_SERVICE
            = (UserService) INJECTOR.getInstance(UserService.class);
    private static final ProductService PRODUCT_SERVICE
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        USER_SERVICE.create(new User("Vitalii", "411100253g@gmail.com", "niceTry"));
        USER_SERVICE.create(new User("Bolvan", "1234123@gmail.com", "niceTry"));
        USER_SERVICE.create(new User("Goblin", "312983791@gmail.com", "niceTry"));
        PRODUCT_SERVICE.create(new Product("Telephone", 109.0));
        PRODUCT_SERVICE.create(new Product("TV", 123.0));
        PRODUCT_SERVICE.create(new Product("Table", 3.0));
        PRODUCT_SERVICE.create(new Product("Monitor", 1231.0));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
