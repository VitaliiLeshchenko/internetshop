package mate.academy.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.Dao;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {

    @Override
    public Product create(Product element) {
        String sql = "insert into products (name, price) VALUES (?, ?);";
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement
                    = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, BigDecimal.valueOf(element.getPrice()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            element.setId(resultSet.getLong(1));
            return element;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        Product product;
        String sql = "SELECT * FROM products WHERE id = ?;";
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBigDecimal(1, BigDecimal.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getProduct(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(Product element) {
        String sql = "UPDATE products SET name = ? , price = ? WHERE id = ?;";
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement
                    = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, BigDecimal.valueOf(element.getPrice()));
            statement.setBigDecimal(3, BigDecimal.valueOf(element.getId()));
            if (statement.executeUpdate() > 0) {
                return get(element.getId()).get();
            }
            String msg = "Can't find product with id :" + element.getId();
            throw new DataProcessingException(msg);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        //todo delete also in shopping_cart_products and orders_products
        String sql = "DELETE FROM products WHERE id = ?;";
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id + "");
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM products";
        List<Product> productList = new ArrayList<>();
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                productList.add(getProduct(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product(resultSet.getString("name"),
                resultSet.getDouble("price"));
        product.setId(resultSet.getLong("id"));
        return product;
    }

    @Override
    public List<Product> getByOrder(Long orderId) {
        return getProductsBySomeId("SELECT id, name, price FROM products "
                        + "JOIN orders_products ON id = product_id WHERE order_id = ?",
                orderId);
    }

    @Override
    public List<Product> getByShoppingCart(Long shoppingCartId) {
        return getProductsBySomeId("SELECT id, name, price "
                + "FROM products JOIN shopping_carts_products ON id = product_id "
                + "WHERE cart_id = ?", shoppingCartId);
    }

    private List<Product> getProductsBySomeId(String query, Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Product> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getProduct(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
