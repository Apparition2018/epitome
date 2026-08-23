package org.ljh.dependencies.group.hutool.lang;

import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://doc.hutool.cn/pages/Console/">Console</a> 控制台打印封装
 * <p>><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/lang/Console.html">Console api</a>
 *
 * @author ljh
 * @since 2020/11/19 21:10
 */
public class ConsoleTests {

    @Test
    public void test() {
        Console.log("{}来自{}", "我", "中国");       // 我来自中国
        Console.error("{}来自{}", "我", "中国");     // 程序出现错误
        Console.print("{}来自{}", "我", "中国\n");   // 程序出现错误

        // 当前行号
        System.out.println(Console.lineNumber());   // 29
        // 当前位置和行号
        System.out.println(Console.where());        // jar.hutool.lang.ConsoleDemo.testConsole(ConsoleDemo.java:30)
    }
}
