package springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springboot.domain.Resource;
import springboot.util.SpringContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author ljh
 * created on 2021/6/23 10:08
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * Spring WebApplicationContextUtils 工具类：https://www.cnblogs.com/jpfss/p/9447915.html
     */
    @GetMapping("webApplicationContextUtils")
    public void testWebApplicationContextUtils(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(Objects.requireNonNull(servletContext));
        Assertions.assertSame(SpringContextUtils.getBean(Resource.class), webApplicationContext.getBean(Resource.class));
    }
}
