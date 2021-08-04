package springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.domain.Resource;

/**
 * 读取资源文件
 *
 * @author ljh
 * created on 2019/8/8 19:39
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
