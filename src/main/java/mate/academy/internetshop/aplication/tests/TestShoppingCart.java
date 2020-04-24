package mate.academy.internetshop.aplication.tests;

import java.util.ArrayList;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ShoppingCartService;

public class TestShoppingCart {
    public static final Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void test() {
        User user1 = new User("user1", "login", "pass");
        ShoppingCart cart1 = new ShoppingCart(new ArrayList<Product>(), user1);

        ShoppingCartDao cartDao = (ShoppingCartDao) injector.getInstance(ShoppingCartDao.class);
        ShoppingCartService cartService
                = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        // 1. Dao Create
        cart1.setUser(user1);
        cartDao.create(cart1);
        // 2. Service addProduct
        Product product1 = new Product("n1", 23.0);
        cartService.addProduct(cart1, product1);
        // 3. Service deleteProduct
        cartService.deleteProduct(cart1, product1);
        // 4. Service getAllProduct
        cartService.addProduct(cart1, product1);
        Product product2 = new Product("n2", 2.35);
        cartService.addProduct(cart1, product2);
        cartService.getAllProducts(cart1);
        // 5. Service clear
        cartService.clear(cart1);
    }
}
