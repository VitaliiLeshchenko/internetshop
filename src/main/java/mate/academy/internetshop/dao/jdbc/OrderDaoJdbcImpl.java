package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.Dao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO orders (user_id) VALUE (?)";
            PreparedStatement statement = con.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            saveProducts(order, con);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT user_id FROM orders WHERE order_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getOrder(id, resultSet.getLong("user_id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order update(Order order) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            saveProducts(order, con);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setLong(1, id);
            st.executeUpdate();
            st = con.prepareStatement(
                    "DELETE FROM orders WHERE order_id = ?");
            st.setLong(1, id);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll() {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders";
            Statement statement = con.createStatement();
            List<Order> orders = new ArrayList<>();
            if (statement.execute(query)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    orders.add(getOrder(resultSet.getLong("order_id"),
                            resultSet.getLong("user_id")));
                }
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getOrdersByUserId(Long userId) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM orders WHERE user_id = ?");
            statement.setLong(1, userId);
            List<Order> orders = new ArrayList<>();
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    orders.add(getOrder(resultSet.getLong("order_id"),
                            resultSet.getLong("user_id")));
                }
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Order getOrder(Long id, Long userId) {
        Order order = new Order(getByOrder(id), userId);
        order.setId(id);
        return order;
    }

    private void saveProducts(Order order, Connection con) throws SQLException {
        String query = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        for (Product product : order.getProducts()) {
            statement.setLong(1, order.getId());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
        }
    }

    public List<Product> getByOrder(Long orderId) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT id, name, price FROM products "
                    + "JOIN orders_products ON id = product_id WHERE order_id = ?");
            statement.setLong(1, orderId);
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

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product(resultSet.getString("name"),
                resultSet.getDouble("price"));
        product.setId(resultSet.getLong("id"));
        return product;
    }
}
