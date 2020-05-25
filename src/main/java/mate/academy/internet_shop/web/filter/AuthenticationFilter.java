package mate.academy.internet_shop.web.filter;

import java.io.IOException;
import java.util.HashSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet_shop.lib.Injector;
import mate.academy.internet_shop.service.UserService;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet_shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private HashSet<String> freeAccessUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        freeAccessUrls.add("/");
        freeAccessUrls.add("/login");
        freeAccessUrls.add("/startPage");
        freeAccessUrls.add("/product/list");
        freeAccessUrls.add("/registration");
        freeAccessUrls.add("/inject/data");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Long userId = (Long) req.getSession().getAttribute("user_id");
        String url = req.getServletPath();
        if (freeAccessUrls.contains(url)) {
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
