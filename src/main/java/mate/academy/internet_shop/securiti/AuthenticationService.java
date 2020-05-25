package mate.academy.internet_shop.securiti;

import mate.academy.internet_shop.exception.AuthenticationException;
import mate.academy.internet_shop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}

