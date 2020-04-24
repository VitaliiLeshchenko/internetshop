package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> get(Long id);

    ShoppingCart update(ShoppingCart shoppingCart);

    boolean delete(Long id);

    boolean delete(ShoppingCart shoppingCart);

    List<ShoppingCart> getAll();
}
