package mate.academy.internetshop.dao.iml;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return Storage.addToList(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.getShoppingCarts()
                .stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.getShoppingCarts().size())
                .filter(i -> Storage.getShoppingCarts().get(i).getId().equals(shoppingCart.getId()))
                .forEach(i -> Storage.getShoppingCarts().set(i, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getShoppingCarts().removeIf(item -> item.getId().equals(id));
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.getShoppingCarts();
    }
}
