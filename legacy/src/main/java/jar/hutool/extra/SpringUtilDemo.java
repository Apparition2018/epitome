package jar.hutool.extra;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.annotation.Import;

/**
 * <a href="https://doc.hutool.cn/pages/SpringUtil/">SpringUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/spring/SpringUtil.html">SpringUtil api</a>
 *
 * @author ljh
 * @since 2020/11/21 15:03
 */
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class SpringUtilDemo {

    public static void main(String[] args) {
        SpringUtil.getBean("person"); // NullPointerException
    }
}
