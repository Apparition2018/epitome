package knowledge.算法;

/**
 * Divide and Conquer
 * 分治法
 *
 * @author ljh
 * created on 2020/9/29 10:42
 */
public class DivideAndConquer {

    /**
     * 汉诺塔
     * http://www.runoob.com/java/method-tower.html
     */
    private static class TowerOfHanoi {

        public static void main(String[] args) {
            int nDisks = 10;
            hanoi(nDisks, 'A', 'B', 'C');
        }

        private static void hanoi(int topN, char from, char inter, char to) {
            if (topN == 1) {
                System.out.println("Disk 1 from " + from + " to " + to);
            } else {
                hanoi(topN - 1, from, to, inter);
                System.out.println("Disk " + topN + " from " + from + " to " + to);
                hanoi(topN - 1, inter, from, to);
            }
        }

    }
}
