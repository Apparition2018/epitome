package springboot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * SpringBoot 过滤器
 * SpringBoot 中 Filter 的使用详解及原理：https://blog.csdn.net/u014627099/article/details/84565603
 * Springboot 中 Filter, Listener的用法：https://blog.csdn.net/liu455042806/article/details/79875999
 * filter listener interceptor 的区别：https://www.cnblogs.com/heyanan/p/9591670.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
// @Order 数字越小代表越先被该 Filter 过滤
@Order(1)
@WebFilter(filterName = "firstFilter", urlPatterns = {"/jackson/*", "/resource/*"})
@Slf4j
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

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
