package springboot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter   过滤器
 * <p>
 * Listener → Filter → Interceptor → ControllerAdvice → Aspect → Controller
 * <p>
 * Listener, Servlet, Filter, Interceptor：https://juejin.cn/post/6844903624187854862
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
// @Order 数字越小代表越先被该 Filter 过滤
@Order(1)
@WebFilter(filterName = "firstFilter", urlPatterns = {"/demo/*", "/restful/*"})
@Slf4j
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("first filter 1");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("first filter 2");
    }

    @Override
    public void destroy() {
    }
}
