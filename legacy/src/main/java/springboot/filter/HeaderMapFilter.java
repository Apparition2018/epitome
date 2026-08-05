package springboot.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import springboot.util.wrapper.HeaderMapRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * OpenAPIHeaderMapFilter
 *
 * @author HP
 * @since 2023/5/21 15:15
 */
@WebFilter(urlPatterns = {"/demo/header"})
public class HeaderMapFilter implements Filter {

    private static final String HEAD_NAME = "name";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
        if (StringUtils.isNotBlank(request.getParameter(HEAD_NAME))) {
            requestWrapper.addHeader(HEAD_NAME, request.getHeader(HEAD_NAME));
        } else {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (StringUtils.equalsIgnoreCase(cookie.getName(), HEAD_NAME)) {
                    requestWrapper.addHeader(HEAD_NAME, cookie.getValue());
                }
            }
        }
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
