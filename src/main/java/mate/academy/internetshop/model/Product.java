package mate.academy.internetshop.model;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private int count;

    public Product(String name, Double price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price
                + ", count=" + count + '}';
    }
}
