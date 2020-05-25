package mate.academy.internet.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.dao.Dao;
import mate.academy.internet.shop.dao.ShoppingCartDao;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.util.ConnectionUtil;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart element) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO shopping_carts\n"
                    + "(user_id) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, element.getUserId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            element.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return element;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM shopping_carts\n"
                    + "WHERE cart_id = ?;");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(getShoppingCart(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public ShoppingCart update(ShoppingCart element) {
        setProductList(element);
        return element;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM shopping_carts WHERE cart_id = ?;");
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        ArrayList<ShoppingCart> list = new ArrayList<>();
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM shopping_carts;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getShoppingCart(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM shopping_carts\n"
                    + "WHERE user_id = ?;");
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getShoppingCart(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ShoppingCart getShoppingCart(ResultSet rs) throws SQLException {
        ShoppingCart shoppingCart = new ShoppingCart(rs.getLong("user_id"));
        shoppingCart.setId(rs.getLong("cart_id"));
        shoppingCart.setProducts(getByShoppingCart(shoppingCart.getId()));
        return shoppingCart;
    }

    private void setProductList(ShoppingCart cart) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM shopping_carts_products WHERE cart_id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, cart.getId());
            ps.executeUpdate();
            for (Product product : cart.getProducts()) {
                ps = con.prepareStatement("INSERT INTO shopping_carts_products\n"
                        + "VALUES (?, ?);");
                ps.setLong(1, cart.getId());
                ps.setLong(2, product.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> getByShoppingCart(Long shoppingCartId) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT id, name, price "
                    + "FROM products JOIN shopping_carts_products ON id = product_id "
                    + "WHERE cart_id = ?");
            statement.setLong(1, shoppingCartId);
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
