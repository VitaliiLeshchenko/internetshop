package mate.academy.internet_shop.service.impl;

import java.util.List;
import mate.academy.internet_shop.dao.ProductDao;
import mate.academy.internet_shop.lib.Inject;
import mate.academy.internet_shop.model.Product;
import mate.academy.internet_shop.service.ProductService;
import mate.academy.internet_shop.service.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).get();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }

    @Override
    public boolean delete(Product product) {
        return productDao.delete(product.getId());
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }
}
