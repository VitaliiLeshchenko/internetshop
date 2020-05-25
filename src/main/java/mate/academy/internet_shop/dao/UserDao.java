package mate.academy.internet_shop.dao;

import java.util.Optional;
import mate.academy.internet_shop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}
