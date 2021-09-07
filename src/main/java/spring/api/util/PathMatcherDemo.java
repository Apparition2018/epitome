package spring.api.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * PathMatcher
 * <p>
 * https://www.cnblogs.com/canger/p/9960703.html
 *
 * @author ljh
 * created on 2021/9/7 18:35
 */
public class PathMatcherDemo {

    @Test
    public void testPathMatcher() {
        /*
         * Ant Wildcard Characters：
         * ?        匹配任何单字符
         * *        匹配0或者任意数量的字符
         * **       匹配0或者更多的目录
         */
        PathMatcher pathMatcher = new AntPathMatcher();
        String requestPath = "";
    }
}
