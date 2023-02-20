package spring.api.util;

import l.demo.Demo;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * PathMatcher
 * <p><a href="https://www.cnblogs.com/leftthen/p/5212221.html">Spring 路径匹配</a>
 *
 * @author ljh
 * @since 2021/9/7 18:35
 */
public class PathMatcherDemo extends Demo {

    public static void main(String[] args) {
        PathMatcher pathMatcher = new AntPathMatcher();
        // 是否匹配
        p(pathMatcher.match("/x/**/xyz", "/x/x/"));         // false
        // 是否开头部分匹配
        p(pathMatcher.matchStart("/x/**/xyz", "/x/x/"));    // true

        // 提取匹配的部分
        p(pathMatcher.extractPathWithinPattern("/hotels/1", "/hotels/1"));  //
        p(pathMatcher.extractPathWithinPattern("/hotels/*", "/hotels/1"));  // 1
        p(pathMatcher.extractPathWithinPattern("/ho?els/s", "/hotels/1"));  // hotels/1

        // 提取 URI 模板变量 {}
        p(pathMatcher.extractUriTemplateVariables("/hotels/{hotel}", "/hotels/1")); // {hotel=1}

        // 合并
        p(pathMatcher.combine("/docs/", "cvs/commit"));     // /docs/cvs/commit
        p(pathMatcher.combine("/docs/*", "cvs/commit"));    // /docs/cvs/commit
        p(pathMatcher.combine("/docs/**", "cvs/commit"));   // /docs/**/cvs/commit
    }
}
