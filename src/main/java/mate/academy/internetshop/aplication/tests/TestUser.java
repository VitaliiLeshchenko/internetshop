package mate.academy.internetshop.aplication.tests;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class TestUser {
    public static void test() {
        Injector injector = Injector.getInstance("mate.academy.internetshop");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User user1 = new User("user1", "login", "pass");

        user1 = userService.create(user1);
        user1 = userService.get(user1.getId());
        System.out.println(userService.getAll());
        user1.setName("updated user1");
        userService.update(user1);
        userService.delete(user1.getId());
    }
}
