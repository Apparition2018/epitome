package jar.hutool.lang;

import cn.hutool.core.lang.Console;
import org.junit.Test;

/**
 * Console      控制台打印封装
 * <p>
 * static Scanner	    scanner()                           创建从控制台读取内容的 Scanner
 * static String	    input()                             读取用户输入的内容（在控制台敲回车前的内容）
 * static void	        table(ConsoleTable consoleTable)    打印表格到控制台
 * <p>
 * https://hutool.cn/docs/#/core/%E8%AF%AD%E8%A8%80%E7%89%B9%E6%80%A7/%E6%8E%A7%E5%88%B6%E5%8F%B0%E6%89%93%E5%8D%B0%E5%B0%81%E8%A3%85-Console
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/lang/Console.html
 *
 * @author Arsenal
 * created on 2020/11/19 21:10
 */
public class ConsoleDemo {

    @Test
    public void testConsole() {
        Console.log("{}来自{}", "我", "中国");       // 我来自中国
        Console.error("{}来自{}", "我", "中国");     // 程序出现错误
        Console.print("{}来自{}", "我", "中国\n");   // 程序出现错误

        // 当前行号
        System.out.println(Console.lineNumber());   // 29
        // 当前位置和行号
        System.out.println(Console.where());        // jar.hutool.lang.ConsoleDemo.testConsole(ConsoleDemo.java:30)
    }
}
