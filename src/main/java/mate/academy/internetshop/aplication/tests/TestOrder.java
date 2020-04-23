package mate.academy.internetshop.aplication.tests;

import java.util.ArrayList;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class TestOrder {
    static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void test() {
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User user1 = new User("user1", "login", "pass");
        User user2 = new User("user1", "login", "pass");
        user2 = userService.create(user2);
        user1 = userService.create(user1);
        ShoppingCart cart1 = new ShoppingCart(new ArrayList<Product>(), user1);
        ShoppingCartDao cartDao = (ShoppingCartDao) injector.getInstance(ShoppingCartDao.class);
        cart1.setUser(user1);
        cart1 = cartDao.create(cart1);
        cartDao.update(cart1);
        Product product = new Product("n1", 23.0);
        Product product2 = new Product("n2", 2.35);
        ShoppingCartService cartService
                = (ShoppingCartService)injector.getInstance(ShoppingCartService.class);
        cartService.addProduct(cart1, product);
        cartService.addProduct(cart1, product2);
        cartDao.getAll().get(0).setUser(user2);
        cartDao.getAll().get(0).getProducts().add(product);

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        Order order = orderService.completeOrder(cartService.getAllProducts(cart1), user1);
        order.setUser(user1);
        OrderDao orderDao = (OrderDao) injector.getInstance(OrderDao.class);
        order = orderDao.create(order);
        orderDao.update(order);
        System.out.println(orderService.getUserOrders(user1));
        System.out.println(orderService.get(order.getId()));
        System.out.println(orderService.getAll());
        System.out.println(orderService.delete(order.getId()));
    }
}
