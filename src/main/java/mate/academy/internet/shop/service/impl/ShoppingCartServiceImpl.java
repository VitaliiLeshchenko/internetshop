package mate.academy.internet.shop.service.impl;

import java.util.List;
import mate.academy.internet.shop.dao.ShoppingCartDao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.service.Service;
import mate.academy.internet.shop.service.ShoppingCartService;

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
        return cartDao.getByUserId(userId);
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return cartDao.get(shoppingCart.getId()).get().getProducts();
    }

    @Override
    public ShoppingCart create(ShoppingCart element) {
        return cartDao.create(element);
    }

    @Override
    public ShoppingCart get(Long id) {
        return cartDao.get(id).get();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return cartDao.getAll();
    }

    @Override
    public ShoppingCart update(ShoppingCart element) {
        return cartDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        return cartDao.delete(id);
    }

    @Override
    public double getTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }
}
