package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.Service;
import mate.academy.internetshop.service.ShoppingCartService;

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
        return orderDao.getAll().stream()
                .parallel()
                .filter(order -> order.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
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
