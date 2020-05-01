package mate.academy.internetshop.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.UserService;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final String USER_ID = "user_id";
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Long userId = (Long) req.getSession().getAttribute(USER_ID);

        String url = req.getServletPath();
        if (url.equals("/login") || url.equals("/startPage") || url.equals("/registration")) {
            chain.doFilter(req, resp);
            return;
        }

        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect("/startPage");
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}