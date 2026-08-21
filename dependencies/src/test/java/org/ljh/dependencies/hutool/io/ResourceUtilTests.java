package org.ljh.dependencies.hutool.io;

import cn.hutool.core.io.resource.ResourceUtil;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static com.ljh.commons.Demo.JDBC_PROPS_FILENAME;
import static com.ljh.commons.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/ResourceUtil/">ResourceUtil</a> 资源工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/io/resource/ResourceUtil.html">ResourceUtil api</a>
 *
 * @author ljh
 * @since 2020/10/30 16:45
 */
public class ResourceUtilTests {

    @Test
    public void test() throws IOException {
        // static String	        readUtf8Str(String resource)
        // 读取 Classpath 下的资源为字符串，使用 UTF-8 编码
        p(ResourceUtil.readUtf8Str(JDBC_PROPS_FILENAME));

        // static BufferedReader	getUtf8Reader(String resource)
        // 从 ClassPath 资源中获取 BufferedReader
        BufferedReader br = ResourceUtil.getUtf8Reader(JDBC_PROPS_FILENAME);
        String line;
        StringBuilder brString = new StringBuilder();
        while (null != (line = br.readLine())) {
            brString.append(line);
        }
        p(brString);
        br.close();
    }
}
