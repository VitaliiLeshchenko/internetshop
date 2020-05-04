package mate.academy.internetshop.securiti;

import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.Service;
import mate.academy.internetshop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDB = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password"));
        if (!userFromDB.getPassword().equals(password)) {
            throw new AuthenticationException("Incorrect login or password");
        }
        return userFromDB;
    }
}
