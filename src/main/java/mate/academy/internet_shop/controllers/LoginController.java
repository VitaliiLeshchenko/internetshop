package mate.academy.internet_shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internet_shop.exception.AuthenticationException;
import mate.academy.internet_shop.lib.Injector;
import mate.academy.internet_shop.model.User;
import mate.academy.internet_shop.securiti.AuthenticationService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet_shop");
    private final AuthenticationService authenticationService =
            (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        try {
            User user = authenticationService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("user_id", user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
