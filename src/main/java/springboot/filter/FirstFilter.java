package springboot.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;

/**
 * Filter   过滤器
 * <p>Servlet2.4 的 Filter 默认情况下只过滤外部提交的请求，forward 和 include 这些内部转发不会被过滤，因此建议继承 OncePreRequestFilter，而不是实现 Filter
 * <p>Listener → Filter → Interceptor → ControllerAdvice → Aspect → Controller
 * <p><a href="https://juejin.cn/post/6844903624187854862">Listener, Servlet, Filter, Interceptor</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
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
