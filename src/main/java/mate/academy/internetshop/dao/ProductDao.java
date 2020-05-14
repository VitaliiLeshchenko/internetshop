package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.model.Product;

public interface ProductDao extends GenericDao<Product,Long> {
    List<Product> getByOrder(Long id);

    List<Product> getByShoppingCart(Long shoppingCartId);
}
