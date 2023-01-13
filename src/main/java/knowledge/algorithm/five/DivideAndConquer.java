package knowledge.algorithm.five;

/**
 * Divide and Conquer
 * 分治法
 *
 * @author ljh
 * @since 2020/9/29 10:42
 */
public class DivideAndConquer {

    /**
     * <a href="http://www.runoob.com/java/method-tower.html">汉诺塔</a>
     */
    static class TowerOfHanoi {

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

    /**
     * Binary Search
     * <p>二分法查找，当数据量很大适宜采用该方法采用二分法查找时，数据需是有序不重复的
     */
    static class BinarySearch {

        private static int idx = 1;

        public static void main(String[] args) {
            int[] arr = new int[]{100, 300, 500, 800, 1000, 2000, 3000};
            find(0, arr.length - 1, 1000, arr);
        }

        private static void find(int leftIndex, int rightIndex, int val, int[] arr) {
            int midIndex = (rightIndex + leftIndex) / 2;
            // 找到中间的数
            int midVal = arr[midIndex];
            if (rightIndex >= leftIndex) {
                // 如果要找的数比 midVal 大
                if (midVal > val) {
                    idx++;
                    // 在 arr 左边数中找
                    find(leftIndex, midIndex - 1, val, arr);
                } else if (midVal < val) {
                    idx++;
                    // 在 arr 的右边去查找
                    find(midIndex + 1, rightIndex, val, arr);
                } else {
                    System.out.println("共查找了" + idx + "次" + "，在下标" + midIndex + "找到");
                }
            } else {
                System.out.println("没有该数!");
            }
        }
    }
}
