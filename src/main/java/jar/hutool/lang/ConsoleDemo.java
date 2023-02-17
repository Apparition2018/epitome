package jar.hutool.lang;

import cn.hutool.core.lang.Console;

/**
 * <a href="https://hutool.cn/docs/#/core/语言特性/控制台打印封装-Console">Console</a> 控制台打印封装
 * <p>><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/lang/Console.html">Console api</a>
 * <pre>
 * static Scanner       scanner()                           创建从控制台读取内容的 Scanner
 * static String        input()                             读取用户输入的内容（在控制台敲回车前的内容）
 * static void          table(ConsoleTable consoleTable)    打印表格到控制台
 * </pre>
 *
 * @author ljh
 * @since 2020/11/19 21:10
 */
public class ConsoleDemo {

    public static void main(String[] args) {
        Console.log("{}来自{}", "我", "中国");       // 我来自中国
        Console.error("{}来自{}", "我", "中国");     // 程序出现错误
        Console.print("{}来自{}", "我", "中国\n");   // 程序出现错误

        // 当前行号
        System.out.println(Console.lineNumber());   // 29
        // 当前位置和行号
        System.out.println(Console.where());        // jar.hutool.lang.ConsoleDemo.testConsole(ConsoleDemo.java:30)
    }
}
