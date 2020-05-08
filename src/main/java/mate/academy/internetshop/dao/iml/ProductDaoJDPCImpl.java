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
public class ProductDaoJDPCImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoJDPCImpl.class);

    @Override
    public Product create(Product element) {
        String sql = "insert into products (name, price) VALUES (?, ?);";
        try (Connection con = ConnectionUtil.getConnection()){
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, BigDecimal.valueOf(element.getPrice()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                element.setId(resultSet.getLong(1));
            }
            return element;
        } catch (SQLException e) {
            LOGGER.error("Cant create product.");
            throw new DataProcessingException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?;";
        try (Connection con = ConnectionUtil.getConnection()){
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBigDecimal(1, BigDecimal.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                        product = new Product(
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                );
                product.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            LOGGER.error("Cant fing product.");
            throw new DataProcessingException(e.getMessage());
        }
        return Optional.ofNullable(product);
    }

    @Override
    public Product update(Product element) {
        String sql = "UPDATE products SET name = ? , price = ? WHERE id = ?;";
        try (Connection con = ConnectionUtil.getConnection()){
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, BigDecimal.valueOf(element.getPrice()));
            statement.setBigDecimal(3, BigDecimal.valueOf(element.getId()));
            statement.executeUpdate();
            return element;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return element;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?;";
        int row = 0;
        try (Connection con = ConnectionUtil.getConnection()){
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id + "");
            row = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return row > 0;
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM products";
        List<Product> productList = new ArrayList<>();
        try (Connection con = ConnectionUtil.getConnection()){
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                );
                product.setId(resultSet.getLong("id"));
                productList.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            //todo add my exceptions everywhere
        }
        return productList;
    }
}
