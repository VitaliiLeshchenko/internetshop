package mate.academy.internet_shop.service;

import mate.academy.internet_shop.model.Product;

public interface ProductService extends GenericService<Product, Long> {
    boolean delete(Product product);
}
