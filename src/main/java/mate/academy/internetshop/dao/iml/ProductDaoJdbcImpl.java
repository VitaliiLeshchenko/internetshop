package mate.academy.internetshop.dao.iml;

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
import org.apache.log4j.Logger;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoJdbcImpl.class);

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
            throw new DataProcessingException(e.getMessage());
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
            throw new DataProcessingException(e.getMessage());
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
            LOGGER.error(msg);
            throw new DataProcessingException(msg);
        } catch (SQLException e) {
            throw new DataProcessingException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?;";
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id + "");
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException(e.getMessage());
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
            throw new DataProcessingException(e.getMessage());
        }
        return productList;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product(resultSet.getString("name"),
                resultSet.getDouble("price"));
        product.setId(resultSet.getLong("id"));
        return product;
    }
}
