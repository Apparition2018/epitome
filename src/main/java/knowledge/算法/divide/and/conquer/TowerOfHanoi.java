package knowledge.算法.divide.and.conquer;

/**
 * 汉诺塔
 *
 * http://www.runoob.com/java/method-tower.html
 */
public class TowerOfHanoi {

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
