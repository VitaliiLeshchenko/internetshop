package mate.academy.internet_shop.service.impl;

import java.util.List;
import mate.academy.internet_shop.dao.OrderDao;
import mate.academy.internet_shop.lib.Inject;
import mate.academy.internet_shop.model.Order;
import mate.academy.internet_shop.model.Product;
import mate.academy.internet_shop.model.ShoppingCart;
import mate.academy.internet_shop.model.User;
import mate.academy.internet_shop.service.OrderService;
import mate.academy.internet_shop.service.Service;
import mate.academy.internet_shop.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        return orderDao.create(new Order(shoppingCartService.getAllProducts(shoppingCart),
                shoppingCart.getUserId()));
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getOrdersByUserId(user.getId());
    }

    @Override
    public Order create(Order element) {
        return orderDao.create(element);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order update(Order element) {
        return orderDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public double getPrice(Order order) {
        return order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }
}
