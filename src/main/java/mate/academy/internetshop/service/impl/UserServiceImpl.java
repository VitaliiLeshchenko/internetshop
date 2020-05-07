package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.Service;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User create(User user) {
        User userNew = userDao.create(user);
        shoppingCartService.create(new ShoppingCart(userNew.getId()));
        return userNew;
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
