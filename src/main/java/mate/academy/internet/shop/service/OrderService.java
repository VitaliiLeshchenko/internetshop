package mate.academy.internet.shop.service;

import java.util.List;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.model.User;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getUserOrders(User user);

    double getPrice(Order order);
}
