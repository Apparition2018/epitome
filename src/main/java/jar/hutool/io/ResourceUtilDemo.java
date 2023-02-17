package jar.hutool.io;

import cn.hutool.core.io.resource.ResourceUtil;
import l.demo.Demo;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * <a href="https://hutool.cn/docs/#/core/IO/资源/资源工具-ResourceUtil-ResourceUtil">ResourceUtil</a>    资源工具
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/io/resource/ResourceUtil.html">ResourceUtil api</a>
 *
 * @author ljh
 * @since 2020/10/30 16:45
 */
public class ResourceUtilDemo extends Demo {

    public static void main(String[] args) throws IOException {
        // static String	        readUtf8Str(String resource)
        // 读取 Classpath 下的资源为字符串，使用 UTF-8 编码
        p(ResourceUtil.readUtf8Str(JDBC_PROPS_FILENAME));
        // jdbc.driver=com.mysql.cj.jdbc.Driver
        // jdbc.url=jdbc:mysql://127.0.0.1:3306/epitome?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
        // jdbc.username=root
        // jdbc.password=root

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
