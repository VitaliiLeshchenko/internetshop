package mate.academy.internetshop.dao.iml;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internetshop.dao.Dao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        return Storage.addToList(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.getOrders()
                .stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.getOrders().size())
                .filter(i -> Storage.getOrders().get(i).getId().equals(order.getId()))
                .forEach(i -> Storage.getOrders().set(i, order));
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getOrders()
                .removeIf(order -> order.getId().equals(id));
    }

    @Override
    public boolean delete(Order order) {
        return Storage.getOrders().remove(order);
    }

    @Override
    public List<Order> getAll() {
        return Storage.getOrders();
    }
}
