package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;

public class Storage {
    private static long itemId = 0;
    private static long bucketId = 0;
    private static long userId = 0;
    private static long orderId = 0;
    private static long shoppingCartId = 0;
    private static final List<Product> products = new ArrayList<>();
    private static final List<Bucket> buckets = new ArrayList<>();
    private static final List<User> users = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public static Product addItemToList(Product product) {
        if (product == null) {
            return null;
        }
        product.setId(itemId);
        products.add(product);
        itemId++;
        return product;
    }

    public static Bucket addBucketToList(Bucket bucket) {
        bucket.setId(bucketId);
        bucketId++;
        return bucket;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static List<Bucket> getBuckets() {
        return buckets;
    }
}
