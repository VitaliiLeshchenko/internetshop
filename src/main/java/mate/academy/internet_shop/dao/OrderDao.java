package mate.academy.internet_shop.dao;

import java.util.List;
import mate.academy.internet_shop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getOrdersByUserId(Long userId);
}
