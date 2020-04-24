package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    boolean delete(User user);
}
