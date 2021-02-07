package knowledge.algorithm;

import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * PrintGraphs
 *
 * @author ljh
 * created on 2020/11/23 10:13
 */
public class PrintGraphs {

    /**
     * 打印平行四边形
     */
    @Test
    public void testPrintParallelogram() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 10; k++) {
                System.out.print("*");
            }
            p();
        }
    }

    /**
     * 打印菱形
     */
    @Test
    public void testPrintDiamond() {
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i + 1; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            p();
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 4 * 2 - 1 - i * 2; j++) {
                System.out.print("*");
            }
            p();
        }
    }
}
