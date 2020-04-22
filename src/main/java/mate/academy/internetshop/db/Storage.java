package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public class Storage {
    private static Long productId = 0L;
    private static Long bucketId = 0L;
    private static Long userId = 0L;
    private static Long orderId = 0L;
    private static Long shoppingCartId = 0L;
    private static final List<Product> products = new ArrayList<>();
    private static final List<Bucket> buckets = new ArrayList<>();
    private static final List<User> users = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public static Product addToList(Product product) {
        if (product == null) {
            return null;
        }
        product.setId(productId++);
        products.add(product);
        return product;
    }

    public static User addToList(User user) {
        if (user == null) {
            return null;
        }
        user.setId(userId++);
        users.add(user);
        return user;
    }

    public static Order addToList(Order order) {
        if (order == null) {
            return null;
        }
        order.setId(orderId++);
        orders.add(order);
        return order;
    }

    public static ShoppingCart addToList(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            return null;
        }
        shoppingCart.setId(shoppingCartId++);
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static List<Bucket> getBuckets() {
        return buckets;
    }
}
