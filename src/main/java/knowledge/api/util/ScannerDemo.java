package knowledge.api.util;

import org.junit.Test;

import java.util.Scanner;

/**
 * Scanner
 * 一个可以使用正则表达式来解析基本类型和字符串的简单文本扫描器。
 * 使用分隔符模式将其输入分解为标记，默认情况下该分隔符模式与空白匹配。
 * 然后可以使用不同的 next 方法将得到的标记转换为不同类型的值。
 * https://jdk6.net/util/Scanner.html
 * <p>
 * next()                   不能获得带有空格的字符串，前置空格会去掉，后置空格会作为分隔符或结束符
 * nextLine()               可以获得带有空格的字符串，
 * XXX	nextXXX()           将输入信息的下一个标记扫描为一个 XXX
 * boolean	hasNextXXX()    如果通过使用 nextXXX() 方法，此扫描器输入信息中的下一个标记可以解释为默认基数中的一个字节值，则返回 true
 * <p>
 */
public class ScannerDemo {

    /**
     * 控制台输入
     */
    @Test
    public void systemIn() {
        Scanner scan = new Scanner(System.in);
    }

    /**
     * String	next()
     * 查找并返回来自此扫描器的下一个完整标记
     */
    @Test
    public void next() {
        Scanner scan = new Scanner(" ' Hello World! ' ");

        while (scan.hasNext()) {
            String s = scan.next();
            System.out.println(s);
            // '
            // Hello
            // World!
            // '
        }

        scan.close();
    }

    /**
     * boolean	hasNext()
     * 如果此扫描器的输入中有另一个标记，则返回 true
     */
    @Test
    public void hasNext() {
        next();
    }

    /**
     * String	nextLine()
     * 此扫描器执行当前行，并返回跳过的输入信息
     */
    @Test
    private void nextLine() {
        Scanner scan = new Scanner(" ' Hello World! ' ");

        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            System.out.println(s); //  ' Hello World! '
        }

        scan.close();
    }

    /**
     * boolean	hasNextLine()
     * 如果在此扫描器的输入中存在另一行，则返回 true
     */
    @Test
    public void hasNextLine() {
        nextLine();
    }


}
