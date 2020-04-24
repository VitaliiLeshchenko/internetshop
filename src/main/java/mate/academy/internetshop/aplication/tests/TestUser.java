package mate.academy.internetshop.aplication.tests;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class TestUser {
    public static void test() {
        Injector injector = Injector.getInstance("mate.academy.internetshop");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User user1 = new User("user1", "login", "pass");
        User user2 = new User("user2", "login2", "pass2");

        user1 = userService.create(user1);
        user2 = userService.create(user2);
        System.out.println("* CREATE USERS ***** GET ALL ********");
        System.out.println(userService.getAll());
        System.out.println("*************************************");
        System.out.println("*** Get USER ** UPDATE USER *********");
        user1.setName("updated user1");
        userService.update(user1);
        System.out.print(userService.get(user1.getId()));
        System.out.println("*************************************");
        userService.delete(user1.getId());
        System.out.println("*************** DELETE USER *********");
        userService.delete(user1.getId());
        userService.delete(user2.getId());
        System.out.println(userService.getAll());
        System.out.println("*************************************");
    }
}
