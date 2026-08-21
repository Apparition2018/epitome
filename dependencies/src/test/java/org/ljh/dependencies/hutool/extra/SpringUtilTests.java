package org.ljh.dependencies.hutool.extra;

import cn.hutool.extra.spring.SpringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

/**
 * <a href="https://doc.hutool.cn/pages/SpringUtil/">SpringUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/spring/SpringUtil.html">SpringUtil api</a>
 *
 * @author ljh
 * @since 2020/11/21 15:03
 */
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class SpringUtilTests {

    @Test
    public void test() {
        SpringUtil.getBean("person"); // NullPointerException
    }
}
