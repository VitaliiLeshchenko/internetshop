package mate.academy.internet_shop.dao;

import mate.academy.internet_shop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {
    ShoppingCart getByUserId(Long userId);
}
