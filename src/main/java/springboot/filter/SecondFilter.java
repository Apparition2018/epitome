package springboot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(2)
@WebFilter(filterName = "secondFilter", urlPatterns = {"/jackson/*", "/resource/*"})
@Slf4j
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("second filter 1");
        log.info("before: " + servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("after: " + servletResponse);
        log.info("second filter 2");

    }

    @Override
    public void destroy() {

    }
}
