package mate.academy.internet_shop.service;

import java.util.Optional;
import mate.academy.internet_shop.model.User;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}
