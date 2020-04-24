package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.Service;
import mate.academy.internetshop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao cartDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return cartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        ShoppingCart newCart = cartDao.get(shoppingCart.getId()).get();
        var resultRemove = newCart.getProducts().remove(product);
        cartDao.update(newCart);
        return resultRemove;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        ShoppingCart newCart = cartDao.get(shoppingCart.getId()).get();
        newCart.getProducts().clear();
        cartDao.update(newCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return cartDao.getAll().stream()
                .filter(cart -> cart.getUser().getId().equals(userId))
                .findFirst()
                .get();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return cartDao.get(shoppingCart.getId()).get().getProducts();
    }
}
