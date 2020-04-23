package mate.academy.internetshop;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class Application {
    static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        Product product = new Product("n1", 23.0);
        Product product2 = new Product("n2", 2.35);
        ProductService productService = (ProductService)injector.getInstance(ProductService.class);
        //create(item)
        product = productService.create(product);
        product2 = productService.create(product2);
        //getAll
        System.out.println(productService.getAll());
        //get(id)
        System.out.println(productService.get(1L));
        //update(item)
        product2.setPrice(1000000.0);
        System.out.println(productService.update(product2));
        //delete(id)
        System.out.println(productService.delete(1L));
        //delete(item)
        System.out.println(productService.delete(product));
        //other
        System.out.println(productService.delete(100L));
        System.out.println(productService.delete(product));
        System.out.println(productService.getAll());
        System.out.println(productService.update(product));
        System.out.println(productService.getAll());
        System.out.println(productService.update(null));
    }
}
