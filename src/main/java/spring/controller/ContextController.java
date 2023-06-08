package spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Arrays;
import java.util.Objects;

import static l.demo.Demo.pe;

/**
 * 上下文
 *
 * @author ljh
 * @since 2022/4/5 15:03
 */
@RestController
@RequestMapping("context")
public class ContextController {

    @GetMapping
    public void get(HttpServletRequest request) {
        // Spring 上下文
        // configLocations: applicationContext.xml
        WebApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        pe(Objects.requireNonNull(ac1).getDisplayName());   // Root WebApplicationContext
        pe(Arrays.toString(ac1.getBeanDefinitionNames()));

        // SpringMVC 上下文
        // configLocations: spring-servlet.xml
        WebApplicationContext ac2 = RequestContextUtils.findWebApplicationContext(request);
        pe(Objects.requireNonNull(ac2).getDisplayName());   // WebApplicationContext for namespace 'spring-mvc-servlet'
        pe(Arrays.toString(ac2.getBeanDefinitionNames()));
    }
}
