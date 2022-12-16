package jar.hutool.io;

import cn.hutool.core.io.resource.ResourceUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * ResourceUtil     资源工具
 * https://hutool.cn/docs/#/core/IO/%E8%B5%84%E6%BA%90/%E8%B5%84%E6%BA%90%E5%B7%A5%E5%85%B7-ResourceUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/io/resource/ResourceUtil.html
 *
 * @author ljh
 * @since 2020/10/30 16:45
 */
public class ResourceUtilDemo extends Demo {

    @Test
    public void testResourceUtil() throws IOException {
        // static String	        readUtf8Str(String resource)
        // 读取 Classpath 下的资源为字符串，使用 UTF-8 编码
        p(ResourceUtil.readUtf8Str(JDBC_PROP_FILENAME));
        // jdbc.driver=com.mysql.cj.jdbc.Driver
        // jdbc.url=jdbc:mysql://127.0.0.1:3306/epitome?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
        // jdbc.username=root
        // jdbc.password=root

        // static BufferedReader	getUtf8Reader(String resource)
        // 从 ClassPath 资源中获取 BufferedReader
        BufferedReader br = ResourceUtil.getUtf8Reader(JDBC_PROP_FILENAME);
        String line;
        StringBuilder brString = new StringBuilder();
        while (null != (line = br.readLine())) {
            brString.append(line);
        }
        p(brString);
        br.close();
    }
}
