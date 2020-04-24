package mate.academy.internetshop;

import mate.academy.internetshop.aplication.tests.TestOrder;
import mate.academy.internetshop.aplication.tests.TestProduct;
import mate.academy.internetshop.aplication.tests.TestShoppingCart;
import mate.academy.internetshop.aplication.tests.TestUser;

public class Application {
    public static void main(String[] args) {
        TestProduct.test();
        TestShoppingCart.test();
        TestUser.test();
        TestOrder.test();
    }
}
