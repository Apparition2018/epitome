package knowledge.算法;

import org.junit.Test;

/**
 * 打印图形
 */
public class PrintShape {

    // 平行四边形
    @Test
    public void parallelogram() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 10; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // 菱形
    @Test
    public void diamond() {
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i + 1; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 4 * 2 - 1 - i * 2; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
