package mate.academy.internetshop.dao.iml;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internetshop.dao.Dao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        return Storage.addToList(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.getUsers()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.getUsers().size())
                .filter(i -> Storage.getUsers().get(i).getId().equals(user.getId()))
                .forEach(i -> Storage.getUsers().set(i, user));
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getUsers().removeIf(user -> user.getId().equals(id));
    }

    @Override
    public List<User> getAll() {
        return Storage.getUsers();
    }
}
