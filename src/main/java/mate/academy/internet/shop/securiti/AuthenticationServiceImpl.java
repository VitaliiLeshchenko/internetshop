package mate.academy.internet.shop.securiti;

import mate.academy.internet.shop.exception.AuthenticationException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.Service;
import mate.academy.internet.shop.service.UserService;
import mate.academy.internet.shop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDB = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password"));
        if (!userFromDB.getPassword()
                .equals(HashUtil.hashPassword(password, userFromDB.getSalt()))) {
            throw new AuthenticationException("Incorrect login or password");
        }
        return userFromDB;
    }
}
