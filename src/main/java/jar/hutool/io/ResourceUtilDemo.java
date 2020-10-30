package jar.hutool.io;

import cn.hutool.core.io.resource.ResourceUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * ResourceUtil
 * https://hutool.cn/docs/#/core/IO/%E8%B5%84%E6%BA%90/%E8%B5%84%E6%BA%90%E5%B7%A5%E5%85%B7-ResourceUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/io/resource/ResourceUtil.html
 *
 * @author ljh
 * created on 2020/10/30 16:45
 */
public class ResourceUtilDemo extends Demo {
    
    @Test
    public void testResourceUtil() {
        p(ResourceUtil.readUtf8Str(JDBC_PROP_FILENAME));
        // jdbc.driver=com.mysql.cj.jdbc.Driver
        // jdbc.url=jdbc:mysql://127.0.0.1:3306/epitome?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
        // jdbc.username=root
        // jdbc.password=root
    }
}
