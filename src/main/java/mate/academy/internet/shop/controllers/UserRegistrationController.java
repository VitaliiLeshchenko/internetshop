package mate.academy.internet.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Role;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;

@WebServlet("/registration")
public class UserRegistrationController extends HttpServlet {
    private static final Injector INJECTOR
            = Injector.getInstance("mate.academy.internet.shop");
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordRepeat = req.getParameter("pwd-repeat");
        if (password.equals(passwordRepeat)) {
            User user = new User(name, login, password);
            user.addRole(new Role(1L, Role.RoleName.USER));
            userService.create(user);
            req.setAttribute("message", "Registration complete, now you can Login.");
            req.getRequestDispatcher("/WEB-INF/jsp/startPage.jsp").forward(req,resp);
        } else {
            req.setAttribute("message", "Your password "
                    + "and repeated password aren`t the same!");
            req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req,resp);
        }
    }
}
