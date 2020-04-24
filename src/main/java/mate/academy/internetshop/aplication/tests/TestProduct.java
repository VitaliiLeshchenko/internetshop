package mate.academy.internetshop.aplication.tests;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class TestProduct {
    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void test() {
        Product product = new Product("n1", 23.0);
        Product product2 = new Product("n2", 2.35);
        ProductService productService
                = (ProductService) injector.getInstance(ProductService.class);
        product = productService.create(product);
        product2 = productService.create(product2);
        System.out.println(productService.getAll());
        System.out.println(productService.get(1L));
        product2.setPrice(1000000.0);
        System.out.println(productService.update(product2));
        System.out.println(productService.delete(1L));
        System.out.println(productService.delete(product));
        System.out.println(productService.delete(100L));
        System.out.println(productService.delete(product));
        System.out.println(productService.getAll());
        System.out.println(productService.update(product));
        System.out.println(productService.getAll());
        System.out.println(productService.update(null));
    }
}
