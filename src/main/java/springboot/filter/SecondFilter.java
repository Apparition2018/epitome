package springboot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Order(2)
@WebFilter(filterName = "secondFilter", urlPatterns = {"/demo/*", "/restful/*"})
@Slf4j
public class SecondFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("second filter 1");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("second filter 2");
    }

    @Override
    public void destroy() {
    }
}
