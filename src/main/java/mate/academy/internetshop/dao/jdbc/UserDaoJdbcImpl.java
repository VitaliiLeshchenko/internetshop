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
import mate.academy.internetshop.dao.UserDao;
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
            return Optional.of(getUser(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
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
            return element;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Connection con = ConnectionUtil.getConnection();) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return Optional.of(getUser(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
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
            return element;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ConnectionUtil.getConnection();) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE user_id = ?;");
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
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
            throw new RuntimeException(e.getMessage());
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
}
