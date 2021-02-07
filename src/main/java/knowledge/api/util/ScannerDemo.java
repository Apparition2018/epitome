package knowledge.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Scanner
 * 一个可以使用正则表达式来解析基本类型和字符串的简单文本扫描器。
 * 使用分隔符模式将其输入分解为标记，默认情况下该分隔符模式与空白匹配。
 * 然后可以使用不同的 next 方法将得到的标记转换为不同类型的值。
 * https://www.runoob.com/manual/jdk1.6/java/util/Scanner.html
 *
 * @author ljh
 * created on 2020/10/23 20:09
 */
public class ScannerDemo extends Demo {

    public static void main(String[] args) {
        testConstructorByInputStream();
    }

    /**
     * 输出流
     */
    private static void testConstructorByInputStream() {
        // Scanner(InputStream source[, String charsetName])
        // 构造一个新的 Scanner，它生成的值是从指定的输入流扫描的
        Scanner scan = new Scanner(System.in);
        p("请输入 x =");
        // XXX	        nextXXX()
        // 将输入信息的下一个标记扫描为一个 XXX
        int x = scan.nextInt();
        p("请输入 y =");
        int y = scan.nextInt();
        p(String.format("%s * %s = %s", x, y, x * y));
        // void	        close()
        // 关闭此扫描器
        scan.close();
    }

    /**
     * 输入字符串
     */
    @Test
    public void testConstructorByString() {
        // Scanner(String source)
        // 构造一个新的 Scanner，它生成的值是从指定字符串扫描的
        Scanner scan = new Scanner("I come from China");
        // boolean	    hasNext()
        // 如果此扫描器的输入中有另一个标记，则返回 true
        // boolean	    hasNext([Pattern pattern / String pattern])
        // 如果下一个标记与 指定模式/字符串构造的模式 匹配，则返回 true
        while (scan.hasNext("[a-zA-Z].*")) {
            // String   next()
            // 查找并返回来自此扫描器的下一个完整标记
            // String	next([Pattern pattern / String pattern])
            // 如果下一个标记与 指定模式/字符串构造的模式 匹配，则返回下一个标记
            String s = scan.next();
            p(s);
            // I
            // come
            // from
            // China
        }
        scan.close();
    }

    /**
     * 输入文件
     */
    @Test
    public void testConstructorByFile() throws FileNotFoundException {
        // Scanner(File source, String charsetName)
        // 构造一个新的 Scanner，它生成的值是从指定文件扫描的
        Scanner scan = new Scanner(new File(DEMO_FILE_PATH));
        // boolean	    hasNextLine()
        // 如果在此扫描器的输入中存在另一行，则返回 true
        while (scan.hasNextLine()) {
            // String	nextLine()
            // 此扫描器执行当前行，并返回跳过的输入信息
            String s = scan.nextLine();
            p(s);
        }
        scan.close();
    }

}
