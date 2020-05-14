package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.dao.Dao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public Optional<User> findByLogin(String login) {
        try (Connection con = ConnectionUtil.getConnection();) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = getUser(rs);
            for (Role rol : getUserRoles(user.getId())) {
                user.addRole(rol);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User create(User element) {
        try (Connection con = ConnectionUtil.getConnection();) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users (login, password, name) VALUES(?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, element.getLogin());
            ps.setString(2, element.getPassword());
            ps.setString(3, element.getName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            element.setId(rs.getLong(1));
            setUserRoles(element);
            return element;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Connection con = ConnectionUtil.getConnection();) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = getUser(rs);
            for (Role rol : getUserRoles(user.getId())) {
                user.addRole(rol);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User update(User element) {
        try (Connection con = ConnectionUtil.getConnection();) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE users SET login = ?, password = ?, name = ? WHERE user_id = ?;");
            ps.setString(1, element.getLogin());
            ps.setString(2, element.getPassword());
            ps.setString(3, element.getName());
            ps.setLong(4, element.getId());
            ps.executeUpdate();
            setUserRoles(element);
            return element;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ConnectionUtil.getConnection();) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT order_id FROM orders WHERE user_id = ?;");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ps = con.prepareStatement(
                        "DELETE FROM orders_products WHERE order_id = ?;");
                ps.setLong(1, rs.getLong("order_id"));
                ps.execute();
            }
            ps = con.prepareStatement("DELETE FROM orders WHERE user_id = ?;");
            ps.setLong(1, id);
            ps.execute();
            ps = con.prepareStatement(
                    "SELECT cart_id FROM shopping_carts WHERE user_id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            ps = con.prepareStatement("DELETE FROM shopping_carts_products WHERE cart_id = ?");
            rs.next();
            ps.setLong(1, rs.getLong("cart_id"));
            ps.execute();
            ps = con.prepareStatement("DELETE FROM shopping_carts WHERE user_id = ?;");
            ps.setLong(1, id);
            ps.execute();
            ps = con.prepareStatement("DELETE FROM user_roles WHERE user_id = ?;");
            ps.setLong(1, id);
            ps.execute();
            ps = con.prepareStatement("DELETE FROM users WHERE user_id = ?;");
            ps.setLong(1, id);
            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection con = ConnectionUtil.getConnection();) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(getUser(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User(rs.getString("name"),
                rs.getString("login"),
                rs.getString("password"));
        user.setId(rs.getLong("user_id"));
        return user;
    }

    private Set<Role> getUserRoles(Long id) {
        Set<Role> roles = new HashSet<>();
        try (Connection con = ConnectionUtil.getConnection()) {
            String sql = "SELECT users.*, roles.name as role, roles.role_id from users\n"
                    + "join user_roles on users.user_id = user_roles.user_id\n"
                    + "join roles on user_roles.role_id = roles.role_id\n"
                    + "where users.user_id = ?;";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setLong(1, id);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                roles.add(
                        new Role(Long.parseLong(resultSet.getString("role_id")),
                                Role.RoleName.valueOf(resultSet.getString("role"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    private void setUserRoles(User user) {
        try (Connection con = ConnectionUtil.getConnection()) {
            for (Role roles : user.getRoles()) {
                String sql = "SELECT role_id FROM roles WHERE name = ?";
                PreparedStatement pr = con.prepareStatement(sql);
                pr.setString(1, roles.getRoleName().toString());
                ResultSet rs = pr.executeQuery();
                rs.next();
                Long roleId = rs.getLong("role_id");
                pr = con.prepareStatement("INSERT INTO user_roles\n"
                         + "VALUES(?, ?);");
                pr.setLong(1, user.getId());
                pr.setLong(2, roleId);
                pr.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
