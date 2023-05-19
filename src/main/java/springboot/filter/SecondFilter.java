package springboot.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;

/**
 * SecondFilter
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Order(2)
@WebFilter(filterName = "secondFilter", urlPatterns = {"/demo/*", "/persons/*"})
@Slf4j
public class SecondFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Second Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Second Filter doFilter start");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("Second Filter doFilter end");
    }

    @Override
    public void destroy() {
        log.info("Second Filter destroy");
    }
}
