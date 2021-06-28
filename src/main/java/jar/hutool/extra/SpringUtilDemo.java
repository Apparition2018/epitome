package jar.hutool.extra;

import cn.hutool.extra.spring.SpringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

/**
 * SpringUtil
 * https://hutool.cn/docs/#/extra/Spring/Spring%E5%B7%A5%E5%85%B7-SpringUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/spring/SpringUtil.html
 *
 * @author Arsenal
 * created on 2020/11/21 15:03
 */
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class SpringUtilDemo {

    @Test
    public void testSpringUtil() {
        SpringUtil.getBean("person"); // NullPointerException
    }
}
