package mate.academy.internetshop.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthorizationFilter implements Filter {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/product/create", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/product/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/product/list/admin", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/user/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/order/delete", Set.of(Role.RoleName.ADMIN));

        protectedUrls.put("/user/order", Set.of(Role.RoleName.USER, Role.RoleName.ADMIN));
        protectedUrls.put("/user/orders", Set.of(Role.RoleName.USER, Role.RoleName.ADMIN));

        protectedUrls.put("/card/product/add", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/product/delete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/order/complete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/user/card", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String requestedUrl = req.getServletPath();
        Long userId = (Long) req.getSession().getAttribute("user_id");
        if (protectedUrls.get(requestedUrl) == null) {
            chain.doFilter(req, resp);
            return;
        }
        if (userId == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
        }
        if (isAuthorized(userService.get(userId), protectedUrls.get(requestedUrl))) {
            chain.doFilter(req, resp);
        } else {
            LOGGER.warn("user : " + userId + " tried to join admin page.");
            req.getRequestDispatcher("/WEB-INF/jsp/accessDenied.jsp").forward(req,resp);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, Set<Role.RoleName> roleNames) {
        for (Role.RoleName authorizedRole : roleNames) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
