package springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.domain.Resource;

/**
 * SpringBoot 读取资源文件
 * https://www.xttblog.com/?p=2834
 * https://www.cnblogs.com/duanxz/p/4520571.html
 * https://blog.csdn.net/michaelwubo/article/details/81289504
 */
@RestController
@RequestMapping("/resource")
@Slf4j
public class ResourceController {

    @javax.annotation.Resource
    private Resource resource;

    @RequestMapping("/test")
    public void test() {
        log.info(resource.toString());
    }
}
