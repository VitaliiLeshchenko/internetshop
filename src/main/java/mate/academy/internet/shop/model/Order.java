package mate.academy.internet.shop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private Long userId;
    private final LocalDateTime dateTime;

    public Order(List<Product> products, Long userId) {
        this.products = products;
        this.userId = userId;
        dateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", products=" + products
                + ", user=" + userId + '}';
    }
}
